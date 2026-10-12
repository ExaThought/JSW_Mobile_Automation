package com.qapitol.jsw.utils;

import com.qapitol.sauron.core.Grid;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.JavascriptExecutor;
import org.yaml.snakeyaml.Yaml;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chromium.HasCdp;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.qapitol.sauron.web.WebActionListener.log;

/**
 * Captures the MoEngage events the web SDK reports and validates their attributes.
 *
 * <p>HOW IT WORKS. The SDK logs every event to the browser console as
 * {@code "Event tracked successfully: plp_visit"} followed by the attributes object. This
 * installs a shim on {@code console.log/info/debug} that, when it sees that message, snapshots
 * the event name and a JSON copy of the attributes into a buffer at {@code window.__jswMoeEvents}.
 * Java reads the buffer back with {@code executeScript}. Reading {@code LogType.BROWSER} instead
 * would not work: Chrome flattens the attributes object to the string "[object Object]".
 * Like {@link NetworkCapture} it drives the browser Sauron already gave you ({@code Grid.driver()}),
 * not a second one the way {@link MoEngageUtil} does.
 *
 * <p>THE ONE RULE: {@link #install()} before the action that fires the event, and again after
 * any full page load (the shim lives on {@code window}). Installing twice is free. Call
 * {@link #clear()} right before the action so an earlier event of the same name is not mistaken
 * for the new one.
 *
 * <pre>
 *   MoEngageEventCapture.install();
 *   MoEngageEventCapture.clear();
 *   // ... open the PLP ...
 *   MoEngageEventCapture.assertEvent("plp_visit", Map.of("category_name", "Mild steel"),
 *           "category_id", "total_items", "user_role");
 * </pre>
 */
public final class MoEngageEventCapture {

    public static final int DEFAULT_TIMEOUT_SECONDS = 15;

    /** Prefix on every event line in the run log, so they can be found with one search. */
    private static final String MARKER = "--- PR-3039 ---";

    private static final int BUFFER_SIZE = 200;

    /** How long to wait for page text (breadcrumb, product heading) to render; pre-prod can be slow. */
    private static final long PAGE_TEXT_WAIT_MILLIS = 20_000L;
    private static final long POLL_MILLIS = 250;

    private MoEngageEventCapture() {
    }

    /** A captured event: its name and attributes as the SDK reported them. */
    public static final class Event {
        public final String name;
        public final JSONObject attributes;

        Event(String name, JSONObject attributes) {
            this.name = name;
            this.attributes = attributes;
        }

        @Override
        public String toString() {
            return name + " " + attributes;
        }
    }

    private static final String INSTALL_SCRIPT =
            "if (window.__jswMoeEvents) { return 'already-installed'; }\n"
          + "var KEY = '__jswMoeEvents';\n"
          + "try { window.__jswMoeEvents = JSON.parse(sessionStorage.getItem(KEY) || '[]'); }\n"
          + "catch (e) { window.__jswMoeEvents = []; }\n"
          + "var MAX = arguments[0];\n"
          + "var re = /Event tracked successfully:\\s*(\\S+)/;\n"
          + "['log', 'info', 'debug'].forEach(function (level) {\n"
          + "  var orig = console[level];\n"
          + "  console[level] = function () {\n"
          + "    try {\n"
          + "      var m = null, attrs = {};\n"
          + "      for (var i = 0; i < arguments.length; i++) {\n"
          + "        var a = arguments[i];\n"
          + "        if (typeof a === 'string') { if (!m) { m = re.exec(a); } }\n"
          + "        else if (a && typeof a === 'object' && m) {\n"
          + "          try { attrs = JSON.parse(JSON.stringify(a)); } catch (e) {}\n"
          + "          break;\n"
          + "        }\n"
          + "      }\n"
          + "      if (m) {\n"
          + "        window.__jswMoeEvents.push({name: m[1], attrs: attrs, at: Date.now()});\n"
          + "        while (window.__jswMoeEvents.length > MAX) { window.__jswMoeEvents.shift(); }\n"
          + "        try { sessionStorage.setItem(KEY, JSON.stringify(window.__jswMoeEvents)); } catch (e) {}\n"
          + "      }\n"
          + "    } catch (e) {}\n"
          + "    return orig.apply(this, arguments);\n"
          + "  };\n"
          + "});\n"
          + "return 'installed';";

    /** Browser sessions whose new-document hook is already registered with Chrome. */
    private static final Set<String> PERSISTED_SESSIONS = ConcurrentHashMap.newKeySet();

    /**
     * Puts the shim in place. Safe to call repeatedly.
     *
     * <p>It is also registered with Chrome ({@code Page.addScriptToEvaluateOnNewDocument}, once
     * per browser session), so it runs at the start of EVERY later page load - a full reload no
     * longer wipes it, and events fired while the page loads are not missed. If that registration
     * is unavailable (a non-Chrome browser, a grid that does not pass the command through) this
     * falls back to a per-page install, and then the rule is the old one: install BEFORE the
     * action, and again after any full page load.
     */
    public static void install() {
        persistAcrossPageLoads();
        log.info("MoEngageEventCapture: " + js().executeScript(INSTALL_SCRIPT, BUFFER_SIZE));
    }

    private static void persistAcrossPageLoads() {
        try {
            WebDriver driver = Grid.driver();
            String sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
            if (PERSISTED_SESSIONS.contains(sessionId)) {
                return;
            }
            String source = "(function () {\n"
                    + INSTALL_SCRIPT.replace("var MAX = arguments[0];", "var MAX = " + BUFFER_SIZE + ";")
                    + "\n})();";
            HasCdp cdp = (HasCdp) new Augmenter().augment(driver);
            cdp.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", Map.of("source", source));
            PERSISTED_SESSIONS.add(sessionId);
            log.info("MoEngageEventCapture: registered for every new page load");
        } catch (RuntimeException | LinkageError e) {
            log.info("MoEngageEventCapture: could not register for new page loads ("
                    + e.getClass().getSimpleName() + ") - installing per page only");
        }
    }

    /** Empties the buffer without removing the shim. */
    public static void clear() {
        js().executeScript("if (window.__jswMoeEvents) { window.__jswMoeEvents.length = 0; }"
                + " try { sessionStorage.removeItem('__jswMoeEvents'); } catch (e) {}");
    }

    /** Every event captured so far, oldest first. */
    public static List<Event> events() {
        Object raw = js().executeScript("return JSON.stringify(window.__jswMoeEvents || []);");
        List<Event> out = new ArrayList<>();
        JSONArray arr = new JSONArray(String.valueOf(raw));
        for (int i = 0; i < arr.length(); i++) {
            JSONObject o = arr.getJSONObject(i);
            out.add(new Event(o.getString("name"), o.optJSONObject("attrs") == null
                    ? new JSONObject() : o.getJSONObject("attrs")));
        }
        return out;
    }

    /** Captured events with the given name, oldest first. */
    public static List<Event> events(String eventName) {
        List<Event> out = new ArrayList<>();
        for (Event e : events()) {
            if (e.name.equals(eventName)) {
                out.add(e);
            }
        }
        return out;
    }

    /**
     * Waits for an event with this name and returns the NEWEST one.
     *
     * @throws IllegalStateException if none arrives, naming the events that did
     */
    public static Event awaitEvent(String eventName, int timeoutSeconds) {
        long deadline = System.currentTimeMillis() + timeoutSeconds * 1000L;
        while (true) {
            List<Event> found = events(eventName);
            if (!found.isEmpty()) {
                return found.get(found.size() - 1);
            }
            if (System.currentTimeMillis() >= deadline) {
                throw new IllegalStateException("MoEngage event \"" + eventName + "\" was not tracked within "
                        + timeoutSeconds + "s. " + diagnose());
            }
            sleepQuietly();
        }
    }

    public static Event awaitEvent(String eventName) {
        return awaitEvent(eventName, DEFAULT_TIMEOUT_SECONDS);
    }

    /**
     * Waits for the event, then checks its attributes. Every problem is collected and reported
     * together, so one run shows all mismatches rather than the first.
     *
     * @param expected     attribute name to exact expected value (compared as text, so
     *                     {@code 28}, {@code "28"} and {@code false}/{@code "false"} all match)
     * @param requiredKeys attributes that must be present and non-null, whatever their value
     * @return the event, for any further checks
     * @throws AssertionError listing every missing or mismatched attribute
     */
    public static Event assertEvent(String eventName, Map<String, ?> expected, String... requiredKeys) {
        return assertEvent(eventName, expected, Collections.emptySet(), requiredKeys);
    }

    /**
     * {@link #assertEvent(String, Map, String...)} where the attributes in {@code emptyAllowed}
     * may hold no value (empty, blank, null, "undefined"). Every other attribute still must.
     */
    public static Event assertEvent(String eventName, Map<String, ?> expected,
                                    Collection<String> emptyAllowed, String... requiredKeys) {
        return checkEvent(awaitEvent(eventName), expected, Collections.emptyMap(), emptyAllowed, requiredKeys);
    }

    /** The checks of {@link #assertEvent}, on an event that has already been captured. */
    private static Event checkEvent(Event event, Map<String, ?> expected, Map<String, ?> startsWith,
                                    Collection<String> emptyAllowed, String... requiredKeys) {
        String eventName = event.name;
        List<String> problems = new ArrayList<>();

        for (String key : requiredKeys) {
            if (!event.attributes.has(key) || event.attributes.isNull(key)) {
                problems.add("missing attribute \"" + key + "\"");
            }
        }
        if (expected != null) {
            for (Map.Entry<String, ?> e : expected.entrySet()) {
                if (!event.attributes.has(e.getKey())) {
                    problems.add("missing attribute \"" + e.getKey() + "\"");
                    continue;
                }
                String actual = String.valueOf(event.attributes.get(e.getKey()));
                String want = String.valueOf(e.getValue());
                if (!actual.equals(want)) {
                    problems.add("\"" + e.getKey() + "\" expected <" + want + "> but was <" + actual + ">");
                }
            }
        }
        for (Map.Entry<String, ?> e : startsWith.entrySet()) {
            if (!event.attributes.has(e.getKey())) {
                problems.add("missing attribute \"" + e.getKey() + "\"");
                continue;
            }
            // The value is the prefix on its own or the prefix then a space and more ("20" matches "20 sheets").
            String actual = String.valueOf(event.attributes.get(e.getKey()));
            String prefix = String.valueOf(e.getValue());
            if (!actual.equals(prefix) && !actual.startsWith(prefix + " ")) {
                problems.add("\"" + e.getKey() + "\" expected to start with <" + prefix + "> but was <" + actual + ">");
            }
        }
        log.info(MARKER + " MoEngage event captured: " + event);
        for (String key : event.attributes.keySet()) {
            String reason = emptyAllowed.contains(key) ? null : emptyReason(event.attributes.get(key));
            if (reason != null) {
                problems.add("attribute \"" + key + "\" has no value (" + reason + ")");
            }
        }
        if (!problems.isEmpty()) {
            throw new AssertionError("MoEngage event \"" + eventName + "\" failed validation: "
                    + String.join("; ", problems) + ". Attributes seen: " + event.attributes);
        }
        log.info(MARKER + " MoEngage event \"" + eventName + "\" validated: " + event);
        return event;
    }

    /**
     * Why a value counts as "no value", or null when it is a real one. {@code false} and {@code 0}
     * are real values. An attribute the page set to {@code undefined} never arrives as a key,
     * because JSON drops it - that case shows up as a missing attribute instead.
     */
    private static String emptyReason(Object value) {
        if (value == null || value == JSONObject.NULL) {
            return "null";
        }
        if (value instanceof String) {
            String text = ((String) value).trim();
            if (text.isEmpty()) {
                return "empty or blank";
            }
            if (text.equalsIgnoreCase("null") || text.equalsIgnoreCase("undefined")
                    || text.equalsIgnoreCase("nan")) {
                return "the text \"" + text + "\"";
            }
        }
        if (value instanceof JSONArray && ((JSONArray) value).length() == 0) {
            return "an empty list";
        }
        if (value instanceof JSONObject && ((JSONObject) value).length() == 0) {
            return "an empty object";
        }
        return null;
    }

    /** Attributes only checked for presence. */
    public static Event assertEvent(String eventName, String... requiredKeys) {
        return assertEvent(eventName, new LinkedHashMap<String, Object>(), requiredKeys);
    }

    /**
     * Step-level check for any event ({@code plp_visit}, {@code pdp_visit}, {@code add_to_cart}, ...).
     * Makes sure the shim is in place, waits for the event and checks its attributes - but reports
     * through the scenario's soft assert instead of throwing, so a missing event is recorded
     * without stopping the steps after it.
     *
     * @param expected     attribute name to exact expected value; may be null or empty
     * @param requiredKeys attributes that must be present and non-null
     */
    public static void validateEvent(String eventName, Map<String, ?> expected, String... requiredKeys) {
        validateEvent(eventName, expected, Collections.emptySet(), requiredKeys);
    }

    /**
     * {@link #validateEvent(String, Map, String...)} where the attributes in {@code emptyAllowed}
     * may hold no value - for an attribute the app is known to send blank. Name each one here
     * rather than loosening the rule for the whole event.
     */
    public static void validateEvent(String eventName, Map<String, ?> expected,
                                     Collection<String> emptyAllowed, String... requiredKeys) {
        try {
            install();
            assertEvent(eventName, expected, emptyAllowed, requiredKeys);
        } catch (AssertionError | RuntimeException e) {
            log.info(MARKER + " MoEngage event \"" + eventName + "\" FAILED validation: " + e.getMessage());
            Hooks.softAssert.assertTrue(false, e.getMessage());
        }
    }

    /** {@link #validateEvent(String, Map, String...)} checking only that the attributes exist. */
    public static void validateEvent(String eventName, String... requiredKeys) {
        validateEvent(eventName, (Map<String, ?>) null, requiredKeys);
    }

    /**
     * {@link #validateEvent} driven by a YAML file, so the expected values live in test data
     * rather than in code: {@code data/moengage/<eventName>.yaml}, with the keys
     * {@code expected} (attribute to exact value), {@code startsWith} (attribute whose value must be
     * this followed by an optional space and unit, e.g. "20" for "20 sheets"), {@code required} (attributes that must be
     * present) and {@code emptyAllowed} (attributes allowed to be blank). The files are shared by
     * every environment, so anything that differs per environment, such as the URL, can only be
     * listed under {@code required}.
     *
     * <p>A missing or unreadable file is reported through the soft assert, because an event
     * checked against nothing would pass for the wrong reason.
     */
    public static void validateEventFromYaml(String eventName) {
        validateEventFromYaml(eventName, Collections.emptyMap());
    }

    /**
     * {@link #validateEventFromYaml(String)} with values the step knows at runtime, which the YAML
     * refers to as {@code "${runtime.<name>}"} - for example the product, thickness and URL a
     * scenario selected. A placeholder with no entry in {@code runtime} is reported as a failure.
     */
    public static void validateEventFromYaml(String eventName, Map<String, String> runtime) {
        validateEventFromYaml(eventName, eventName, runtime);
    }

    /**
     * {@link #validateEventFromYaml(String, Map)} reading {@code data/moengage/<specName>.yaml}
     * instead of the file named after the event - for one event whose expected values depend on the
     * situation, such as {@code home_visit} for a guest and for a logged-in user.
     */
    public static void validateEventFromYaml(String eventName, String specName, Map<String, String> runtime) {
        String path = "data/moengage/" + specName + ".yaml";
        // Capture first: resolving the expected values can call an API, and a page reload while that
        // runs would wipe the in-page buffer.
        Event event;
        try {
            install();
            event = awaitEvent(eventName);
        } catch (RuntimeException e) {
            reportFailure(eventName, e.getMessage());
            return;
        }
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            if (in == null) {
                throw new IllegalStateException("not found on the classpath");
            }
            Object loaded = new Yaml().load(in);
            if (!(loaded instanceof Map)) {
                throw new IllegalStateException("it is empty or not a YAML mapping");
            }
            Map<String, Object> spec = castMap(loaded);
            Map<String, Object> expected = resolvePlaceholders(spec.get("expected") == null
                    ? new LinkedHashMap<>() : castMap(spec.get("expected")), runtime);
            log.info(MARKER + " MoEngage event \"" + eventName + "\" checked against " + path);
            Map<String, Object> startsWith = resolvePlaceholders(spec.get("startsWith") == null
                    ? new LinkedHashMap<>() : castMap(spec.get("startsWith")), runtime);
            checkEvent(event, expected, startsWith, stringList(spec.get("emptyAllowed")),
                    stringList(spec.get("required")).toArray(new String[0]));
        } catch (IOException | RuntimeException | AssertionError e) {
            reportFailure(eventName, e.getMessage());
        }
    }

    private static void reportFailure(String eventName, String message) {
        log.info(MARKER + " MoEngage event \"" + eventName + "\" FAILED validation: " + message);
        Hooks.softAssert.assertTrue(false, message);
    }

    /**
     * Replaces values written as {@code "${name}"} with a value worked out at runtime. Known:
     * {@code ${customerProfile.userRole}} (from the roles in the customer-profile API
     * response), {@code ${customerProfile.userContactId}} (its {@code sfAccountId}) and
     * {@code ${customerProfile.userType}} (its {@code contactType}, lower case) and {@code ${page.categoryName}} (the breadcrumb
     * category link on the current page) and {@code ${page.currentUrl}} (the browser's current URL) and {@code ${page.productName}} (the PDP's {@code h1}) and {@code ${runtime.<name>}} (a value the step supplied).
     */
    private static Map<String, Object> resolvePlaceholders(Map<String, Object> expected,
                                                           Map<String, String> runtime) {
        Map<String, Object> resolved = new LinkedHashMap<>();
        for (Map.Entry<String, Object> e : expected.entrySet()) {
            Object value = e.getValue();
            if (value instanceof String && ((String) value).matches("\\$\\{[^}]+}")) {
                String name = ((String) value).substring(2, ((String) value).length() - 1);
                if (name.startsWith("runtime.")) {
                    String key = name.substring("runtime.".length());
                    if (!runtime.containsKey(key) || runtime.get(key) == null) {
                        throw new IllegalStateException("no runtime value supplied for " + value
                                + " (\"" + e.getKey() + "\") - the step did not pass '" + key + "'");
                    }
                    value = runtime.get(key);
                } else if (name.equals("customerProfile.userRole")) {
                    value = CustomerProfileUtil.moEngageUserRole();
                } else if (name.equals("customerProfile.userContactId")) {
                    value = CustomerProfileUtil.profileValue("sfAccountId");
                } else if (name.equals("page.currentUrl")) {
                    value = Grid.driver().getCurrentUrl();
                } else if (name.equals("page.productName")) {
                    value = productNameFromHeading();
                } else if (name.equals("page.categoryName")) {
                    value = categoryNameFromBreadcrumb();
                } else if (name.equals("customerProfile.userType")) {
                    value = CustomerProfileUtil.moEngageUserType();
                } else {
                    throw new IllegalStateException("unknown placeholder " + value + " for \"" + e.getKey() + "\"");
                }
            }
            resolved.put(e.getKey(), value);
        }
        return resolved;
    }

    private static final By PRODUCT_HEADING = By.xpath("//h1");

    /** The product name on the PDP: the text of its {@code h1}. Waits up to 20s for it to render. */
    private static String productNameFromHeading() {
        WebDriver driver = Grid.driver();
        long deadline = System.currentTimeMillis() + PAGE_TEXT_WAIT_MILLIS;
        while (true) {
            for (WebElement heading : driver.findElements(PRODUCT_HEADING)) {
                try {
                    String text = heading.getText().trim();
                    if (text.isEmpty()) {
                        text = String.valueOf(heading.getAttribute("textContent")).trim();
                    }
                    if (!text.isEmpty()) {
                        log.info(MARKER + " PDP heading " + PRODUCT_HEADING + " -> product_name \"" + text + "\"");
                        return text;
                    }
                } catch (StaleElementReferenceException ignored) {
                }
            }
            if (System.currentTimeMillis() >= deadline) {
                throw new IllegalStateException("no text in " + PRODUCT_HEADING + " on " + driver.getCurrentUrl());
            }
            sleepQuietly();
        }
    }

    /** The category links of the breadcrumb, on both the PLP and the PDP. */
    private static final By CATEGORY_BREADCRUMB = By.xpath(
            "//a[contains(@class, 'gray-title') and @data-slot='breadcrumb-link']");

    /**
     * The breadcrumb's current-page label. On a PLP the category being viewed is this plain span,
     * not a link, so {@link #CATEGORY_BREADCRUMB} finds nothing there.
     */
    private static final By CATEGORY_BREADCRUMB_PAGE = By.xpath("//span[@data-slot='breadcrumb-page']");

    /**
     * The text of the breadcrumb category link, which is what the {@code category_name} attribute
     * should equal. When the breadcrumb has several such links the LAST one is used - the
     * category nearest the page. Waits up to 20s for the breadcrumb to render.
     * Where the page has no such link (a PLP), the current-page breadcrumb label is used instead.
     */
    private static String categoryNameFromBreadcrumb() {
        WebDriver driver = Grid.driver();
        long deadline = System.currentTimeMillis() + PAGE_TEXT_WAIT_MILLIS;
        List<String> texts = new ArrayList<>();
        while (true) {
            texts.clear();
            List<WebElement> candidates = driver.findElements(CATEGORY_BREADCRUMB);
            if (candidates.isEmpty()) {
                candidates = driver.findElements(CATEGORY_BREADCRUMB_PAGE);
            }
            for (WebElement link : candidates) {
                try {
                    String text = link.getText().trim();
                    if (text.isEmpty()) {
                        text = String.valueOf(link.getAttribute("textContent")).trim();
                    }
                    if (!text.isEmpty()) {
                        texts.add(text);
                    }
                } catch (StaleElementReferenceException ignored) {
                }
            }
            if (!texts.isEmpty() || System.currentTimeMillis() >= deadline) {
                break;
            }
            sleepQuietly();
        }
        if (texts.isEmpty()) {
            throw new IllegalStateException("no breadcrumb category link found on " + driver.getCurrentUrl()
                    + " with " + CATEGORY_BREADCRUMB + " or " + CATEGORY_BREADCRUMB_PAGE);
        }
        return texts.get(texts.size() - 1);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> castMap(Object o) {
        return (Map<String, Object>) o;
    }

    private static List<String> stringList(Object o) {
        List<String> out = new ArrayList<>();
        if (o instanceof Collection) {
            for (Object item : (Collection<?>) o) {
                out.add(String.valueOf(item));
            }
        }
        return out;
    }

    /** Fails if the event WAS tracked - for flows that must not fire it (e.g. a guest user). */
    public static void assertNotTracked(String eventName) {
        List<Event> found = events(eventName);
        if (!found.isEmpty()) {
            throw new AssertionError("MoEngage event \"" + eventName + "\" was tracked " + found.size()
                    + " time(s) but should not have been: " + found.get(0));
        }
    }

    private static String diagnose() {
        try {
            if (!Boolean.TRUE.equals(js().executeScript("return !!window.__jswMoeEvents;"))) {
                return "The capture shim is NOT installed on this page - call MoEngageEventCapture.install()"
                        + " before the action, and again after any full page load.";
            }
            List<String> names = new ArrayList<>();
            for (Event e : events()) {
                names.add(e.name);
            }
            return names.isEmpty()
                    ? "The shim is installed but captured NO events - it may have been installed after the"
                      + " event fired, or the page reloaded and wiped it."
                    : "Events seen: " + names;
        } catch (RuntimeException e) {
            return "(could not read capture state: " + e.getMessage() + ")";
        }
    }

    private static void sleepQuietly() {
        try {
            Thread.sleep(POLL_MILLIS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for a MoEngage event", e);
        }
    }

    private static JavascriptExecutor js() {
        return (JavascriptExecutor) Grid.driver();
    }
}
