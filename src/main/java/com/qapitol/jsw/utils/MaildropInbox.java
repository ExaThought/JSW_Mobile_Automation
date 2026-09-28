package com.qapitol.jsw.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.qapitol.sauron.core.Grid;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qapitol.sauron.web.WebActionListener.log;

/**
 * Reads a maildrop.cc inbox through its GraphQL API.
 *
 * <p>WHY AN API AND NOT A BROWSER: this replaced a Yopmail client that drove the inbox web UI.
 * Yopmail started serving a Cloudflare "Verify you are human" challenge in front of the inbox,
 * which cannot and must not be automated around. Maildrop's API is plain HTTP with no key, so
 * reading mail never loads a page that could show a challenge.
 *
 * <p>The one browser step left is {@link #openLatestMailInCurrentWindow}, which renders the mail's
 * own HTML from a data: URL so the Accept invite call-to-action can still be clicked as a person
 * would. That page is the email, not maildrop.cc.
 *
 * <p>KNOW THE LIMITS: maildrop inboxes are public (anyone who guesses the name can read them, so
 * {@link #newAddress()} mints unguessable ones), mail is deleted after about a day, each inbox
 * keeps only a handful of messages, and the service has no uptime guarantee. A red run with "no
 * mail" and an API error in the log is Maildrop, not CCP.
 */
public final class MaildropInbox {

    private static final String API_URL = "https://api.maildrop.cc/graphql";
    private static final String DOMAIN = "maildrop.cc";

    private static final String INBOX_QUERY =
            "query Inbox($m: String!) { inbox(mailbox: $m) { id date subject } }";
    private static final String MESSAGE_QUERY =
            "query Message($m: String!, $id: String!) { message(mailbox: $m, id: $id) { html subject } }";

    private static final Duration POLL_INTERVAL = Duration.ofSeconds(5);
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(20);

    private static final Pattern HREF = Pattern.compile("href\\s*=\\s*[\"']([^\"']+)[\"']",
            Pattern.CASE_INSENSITIVE);

    private static final HttpClient HTTP = HttpClient.newBuilder()
            .connectTimeout(REQUEST_TIMEOUT)
            .build();
    private static final ObjectMapper JSON = new ObjectMapper();

    private MaildropInbox() {
        // utility class
    }

    /**
     * A fresh, unguessable invitee address. Timestamped for traceability in the CCP user list, and
     * random-suffixed because maildrop inboxes are readable by anyone who knows the name.
     */
    public static String newAddress() {
        String suffix = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return "jswqa" + System.currentTimeMillis() + suffix + "@" + DOMAIN;
    }

    /** How many messages the inbox currently holds; 0 when it is empty or the API fails. */
    public static int messageCount(String address) {
        JsonNode inbox = inbox(inboxName(address));
        return inbox == null ? 0 : inbox.size();
    }

    /**
     * Polls the inbox until at least one message arrives.
     *
     * @return true if a message arrived within the timeout.
     */
    public static boolean waitForMail(String address, Duration timeout) {
        String inbox = inboxName(address);
        Instant deadline = Instant.now().plus(timeout);
        int polls = 0;
        while (Instant.now().isBefore(deadline)) {
            JsonNode messages = inbox(inbox);
            if (messages != null && messages.size() > 0) {
                log.info("Maildrop inbox '" + inbox + "' has mail after " + polls + " poll(s)");
                return true;
            }
            sleep(POLL_INTERVAL.toMillis());
            polls++;
        }
        log.warning("No mail in Maildrop inbox '" + inbox + "' within " + timeout.toSeconds() + "s");
        return false;
    }

    /** The newest message's text with markup stripped, or an empty string when there is none. */
    public static String latestMailText(String address) {
        String html = latestMailHtml(address);
        return html == null ? "" : htmlToText(html);
    }

    /**
     * The first absolute link in the newest message, or null when there is none.
     *
     * <p>"First link" rather than matching link text: the accept call-to-action is the primary
     * link in an invitation mail, and matching on wording would break the moment the template
     * says "Get started" instead of "Accept invitation".
     */
    public static String latestMailFirstLink(String address) {
        String html = latestMailHtml(address);
        if (html == null) {
            return null;
        }
        Matcher m = HREF.matcher(html);
        while (m.find()) {
            String href = m.group(1).replace("&amp;", "&").trim();
            if (href.startsWith("http") && !href.contains(DOMAIN)) {
                return href;
            }
        }
        return null;
    }

    /**
     * Renders the newest message's HTML in the current window, so its elements can be located and
     * clicked directly.
     *
     * @return false when there was no message to open.
     */
    public static boolean openLatestMailInCurrentWindow(String address) {
        String html = latestMailHtml(address);
        if (html == null) {
            return false;
        }
        String encoded = Base64.getEncoder().encodeToString(html.getBytes(StandardCharsets.UTF_8));
        Grid.driver().get("data:text/html;charset=utf-8;base64," + encoded);
        sleep(1000);
        return true;
    }

    /** The newest message's HTML, or null when the inbox is empty or the API fails. */
    private static String latestMailHtml(String address) {
        String inbox = inboxName(address);
        JsonNode messages = inbox(inbox);
        if (messages == null || messages.size() == 0) {
            log.warning("Maildrop inbox '" + inbox + "' is empty - nothing to read");
            return null;
        }
        JsonNode newest = messages.get(0);
        for (JsonNode message : messages) {
            // ISO-8601 dates compare correctly as strings.
            if (message.path("date").asText("").compareTo(newest.path("date").asText("")) > 0) {
                newest = message;
            }
        }
        ObjectNode variables = JSON.createObjectNode()
                .put("m", inbox)
                .put("id", newest.path("id").asText());
        JsonNode message = query(MESSAGE_QUERY, variables);
        if (message == null || message.path("message").isNull()) {
            log.warning("Maildrop returned no body for the newest message in '" + inbox + "'");
            return null;
        }
        String html = message.path("message").path("html").asText(null);
        return (html == null || html.isBlank()) ? null : html;
    }

    private static JsonNode inbox(String inbox) {
        JsonNode data = query(INBOX_QUERY, JSON.createObjectNode().put("m", inbox));
        if (data == null) {
            return null;
        }
        JsonNode messages = data.path("inbox");
        return messages.isArray() ? messages : null;
    }

    /** Runs a GraphQL query and returns its {@code data} node, or null on any failure. */
    private static JsonNode query(String query, ObjectNode variables) {
        try {
            ObjectNode body = JSON.createObjectNode().put("query", query);
            body.set("variables", variables);
            HttpRequest request = HttpRequest.newBuilder(URI.create(API_URL))
                    .timeout(REQUEST_TIMEOUT)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(JSON.writeValueAsString(body)))
                    .build();
            HttpResponse<String> response = HTTP.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                log.warning("Maildrop API returned HTTP " + response.statusCode());
                return null;
            }
            JsonNode json = JSON.readTree(response.body());
            if (json.hasNonNull("errors")) {
                log.warning("Maildrop API error: " + json.get("errors"));
                return null;
            }
            return json.path("data");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } catch (Exception e) {
            log.warning("Maildrop API call failed: " + e.getClass().getSimpleName() + ": " + e.getMessage());
            return null;
        }
    }

    private static String htmlToText(String html) {
        return html.replaceAll("(?is)<(script|style)[^>]*>.*?</\\1>", " ")
                .replaceAll("<[^>]+>", " ")
                .replace("&nbsp;", " ")
                .replace("&amp;", "&")
                .replaceAll("\\s+", " ")
                .trim();
    }

    /** The inbox name Maildrop uses - the local part, with any domain stripped. */
    private static String inboxName(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalStateException("No address to check: the scenario asked for an"
                    + " invitation mailbox before an invitation had been sent, so there is no"
                    + " invited address in ScenarioContext.");
        }
        int at = address.indexOf('@');
        return at < 0 ? address.trim() : address.substring(0, at).trim();
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
