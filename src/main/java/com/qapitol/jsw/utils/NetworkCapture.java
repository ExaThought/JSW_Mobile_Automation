package com.qapitol.jsw.utils;

import com.qapitol.sauron.core.Grid;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;

import static com.qapitol.sauron.web.WebActionListener.log;

/**
 * Reads values out of the application's own API responses at runtime, so a test can type a
 * server-generated value (an OTP, a reference number, a generated id) into the UI instead of
 * hard-coding one.
 *
 * <p>HOW IT WORKS - and why it is not a proxy.
 *
 * <p>This installs a small shim in the page that wraps {@code window.fetch} and
 * {@code XMLHttpRequest}, so every response the application receives is also pushed onto a ring
 * buffer at {@code window.__jswCapture}. Java then reads that buffer back with
 * {@code executeScript}. The browser is the one Sauron already gave you - {@code Grid.driver()} -
 * so what is captured is exactly what the steps are driving.
 *
 * <p>The two alternatives were both rejected for concrete reasons:
 *
 * <ul>
 *   <li><b>BrowserMob</b> ({@link MoEngageUtil}) starts its own {@code ChromeDriver}, so it
 *       captures a DIFFERENT browser than the steps use. It also MITMs HTTPS with its own CA -
 *       which is why that class needs {@code --ignore-certificate-errors} and
 *       {@code --disable-web-security} - and a proxy bound on the test machine is unreachable
 *       from a grid node or a Docker container. BMP 2.1.5 was last released in 2017.
 *   <li><b>CDP</b> ({@code Network.getResponseBody}) needs {@code Augmenter} to reach through a
 *       {@code RemoteWebDriver}, needs the grid to pass the websocket through, and pins you to a
 *       {@code selenium-devtools-vNNN} artifact that must track the agent's Chrome build.
 * </ul>
 *
 * <p>THE ONE RULE: {@link #install()} must run BEFORE the call you want to capture is made.
 * The shim can only see calls that happen after it is in place. A full page load wipes it
 * (it lives on {@code window}), so re-install after any navigation. Calling install twice is
 * free - it detects itself and returns immediately. SPA route changes do NOT wipe it.
 *
 * <p>Response bodies are filtered by URL inside the browser, so only the entries you asked for
 * cross the wire to Java.
 *
 * <p>Thread-safety needs no attention here: the buffer lives in the browser, and each test
 * thread has its own browser session, so two threads cannot see each other's traffic.
 */
public final class NetworkCapture {

    /** How many responses the in-page ring buffer holds before dropping the oldest. */
    private static final int BUFFER_SIZE = 200;

    /** Gap between polls while waiting for a response to arrive. */
    private static final long POLL_MILLIS = 250;

    /** Default wait for a response, in seconds. Generous: an OTP send can be slow. */
    public static final int DEFAULT_TIMEOUT_SECONDS = 30;

    private NetworkCapture() {
    }

    /*
     * The shim. Written defensively because it runs inside the application's own page - anything
     * that throws here breaks the app under test, not just the capture.
     *
     * fetch: res.clone() is load-bearing. A Response body is a single-use stream, so reading the
     * original would consume it and the application would receive an empty body.
     *
     * XHR: responseText throws on a binary responseType, hence the guard.
     */
    private static final String INSTALL_SCRIPT =
            "if (window.__jswCapture) { return 'already-installed'; }\n"
          + "window.__jswCapture = [];\n"
          + "var MAX = arguments[0];\n"
          + "function record(url, status, body) {\n"
          + "  try {\n"
          + "    window.__jswCapture.push({url: String(url), status: status, body: body, at: Date.now()});\n"
          + "    while (window.__jswCapture.length > MAX) { window.__jswCapture.shift(); }\n"
          + "  } catch (e) {}\n"
          + "}\n"
          + "var origFetch = window.fetch;\n"
          + "if (origFetch) {\n"
          + "  window.fetch = function () {\n"
          + "    var a = arguments;\n"
          + "    var url = (a[0] && a[0].url) ? a[0].url : a[0];\n"
          + "    return origFetch.apply(this, a).then(function (res) {\n"
          + "      try {\n"
          + "        res.clone().text().then(function (t) { record(url, res.status, t); }, function () {});\n"
          + "      } catch (e) {}\n"
          + "      return res;\n"
          + "    });\n"
          + "  };\n"
          + "}\n"
          + "var origOpen = XMLHttpRequest.prototype.open;\n"
          + "var origSend = XMLHttpRequest.prototype.send;\n"
          + "XMLHttpRequest.prototype.open = function (method, url) {\n"
          + "  this.__jswUrl = url;\n"
          + "  return origOpen.apply(this, arguments);\n"
          + "};\n"
          + "XMLHttpRequest.prototype.send = function () {\n"
          + "  var xhr = this;\n"
          + "  xhr.addEventListener('load', function () {\n"
          + "    var body = '';\n"
          + "    try {\n"
          + "      var t = xhr.responseType;\n"
          + "      if (t === '' || t === 'text') { body = xhr.responseText; }\n"
          + "      else if (t === 'json') { body = JSON.stringify(xhr.response); }\n"
          + "    } catch (e) {}\n"
          + "    record(xhr.__jswUrl, xhr.status, body);\n"
          + "  });\n"
          + "  return origSend.apply(this, arguments);\n"
          + "};\n"
          + "return 'installed';";

    /** Returns the bodies of captured responses whose URL contains arguments[0], oldest first. */
    private static final String READ_SCRIPT =
            "var needle = arguments[0];\n"
          + "var out = [];\n"
          + "var all = window.__jswCapture || [];\n"
          + "for (var i = 0; i < all.length; i++) {\n"
          + "  if (all[i].url.indexOf(needle) !== -1) { out.push(all[i].body); }\n"
          + "}\n"
          + "return out;";

    /**
     * Puts the shim in place. Call this BEFORE triggering the call you want to read, and again
     * after any full page load. Safe to call repeatedly.
     */
    public static void install() {
        Object result = js().executeScript(INSTALL_SCRIPT, BUFFER_SIZE);
        log.info("NetworkCapture: " + result);
    }

    /**
     * Empties the buffer without removing the shim. Use this immediately before the action that
     * triggers the call, so a matching response from earlier in the scenario cannot be mistaken
     * for the new one - the classic way a second OTP request reads back the first OTP.
     */
    public static void clear() {
        js().executeScript("if (window.__jswCapture) { window.__jswCapture.length = 0; }");
    }

    /**
     * Waits for a response whose URL contains {@code urlContains} and returns its raw body.
     * When several match, the NEWEST wins - a retried request should beat the attempt it replaced.
     *
     * @throws IllegalStateException if nothing matches before the timeout
     */
    public static String awaitResponseBody(String urlContains, int timeoutSeconds) {
        long deadline = System.currentTimeMillis() + (timeoutSeconds * 1000L);
        List<String> seen = new ArrayList<>();
        while (System.currentTimeMillis() < deadline) {
            seen = bodiesMatching(urlContains);
            if (!seen.isEmpty()) {
                return seen.get(seen.size() - 1);
            }
            sleepQuietly();
        }
        throw new IllegalStateException(
                "No API response matching \"" + urlContains + "\" arrived within " + timeoutSeconds
                        + "s. " + diagnose(urlContains));
    }

    /** {@link #awaitResponseBody(String, int)} with the default timeout. */
    public static String awaitResponseBody(String urlContains) {
        return awaitResponseBody(urlContains, DEFAULT_TIMEOUT_SECONDS);
    }

    /**
     * Waits for a matching response and pulls one value out of its JSON body.
     *
     * <p>{@code jsonPath} is a dotted path and may index arrays:
     * {@code "data.otp"}, {@code "result.items[0].id"}.
     *
     * @throws IllegalStateException if no response arrives, or the path is not in the body
     */
    public static String awaitJsonValue(String urlContains, String jsonPath, int timeoutSeconds) {
        long deadline = System.currentTimeMillis() + (timeoutSeconds * 1000L);
        String lastBody = null;
        while (System.currentTimeMillis() < deadline) {
            for (String body : bodiesMatching(urlContains)) {
                lastBody = body;
                String value = readPath(body, jsonPath);
                if (value != null) {
                    // Deliberately not logged at INFO with its value - an OTP in the run log is
                    // an OTP in the Allure report attached to it. Length only.
                    log.info("NetworkCapture: read \"" + jsonPath + "\" from " + urlContains
                            + " (" + value.length() + " chars)");
                    return value;
                }
            }
            sleepQuietly();
        }
        if (lastBody != null) {
            throw new IllegalStateException(
                    "A response matching \"" + urlContains + "\" arrived, but it has no value at \""
                            + jsonPath + "\". Body starts: " + preview(lastBody));
        }
        throw new IllegalStateException(
                "No API response matching \"" + urlContains + "\" arrived within " + timeoutSeconds
                        + "s. " + diagnose(urlContains));
    }

    /** {@link #awaitJsonValue(String, String, int)} with the default timeout. */
    public static String awaitJsonValue(String urlContains, String jsonPath) {
        return awaitJsonValue(urlContains, jsonPath, DEFAULT_TIMEOUT_SECONDS);
    }

    /** True when the shim is in place on the current page. */
    public static boolean isInstalled() {
        return Boolean.TRUE.equals(js().executeScript("return !!window.__jswCapture;"));
    }

    @SuppressWarnings("unchecked")
    private static List<String> bodiesMatching(String urlContains) {
        Object raw = js().executeScript(READ_SCRIPT, urlContains);
        if (raw instanceof List) {
            List<String> out = new ArrayList<>();
            for (Object o : (List<Object>) raw) {
                out.add(o == null ? "" : String.valueOf(o));
            }
            return out;
        }
        return new ArrayList<>();
    }

    /**
     * Walks a dotted path through a JSON body. Returns null - rather than throwing - when the
     * path is absent, because callers poll: the first response back may be a different call that
     * happens to share the URL fragment.
     */
    private static String readPath(String body, String jsonPath) {
        if (body == null || body.isEmpty()) {
            return null;
        }
        try {
            Object node = body.trim().startsWith("[")
                    ? new JSONArray(body)
                    : new JSONObject(body);
            for (String rawSegment : jsonPath.split("\\.")) {
                String segment = rawSegment;
                // Split "items[0]" into the key "items" and the index 0.
                while (segment.contains("[")) {
                    int bracket = segment.indexOf('[');
                    String key = segment.substring(0, bracket);
                    if (!key.isEmpty()) {
                        node = child(node, key);
                        if (node == null) {
                            return null;
                        }
                    }
                    int close = segment.indexOf(']', bracket);
                    if (close < 0) {
                        return null;
                    }
                    int index = Integer.parseInt(segment.substring(bracket + 1, close).trim());
                    if (!(node instanceof JSONArray) || ((JSONArray) node).length() <= index) {
                        return null;
                    }
                    node = ((JSONArray) node).get(index);
                    segment = segment.substring(close + 1);
                }
                if (!segment.isEmpty()) {
                    node = child(node, segment);
                }
                if (node == null) {
                    return null;
                }
            }
            return (node == JSONObject.NULL) ? null : String.valueOf(node);
        } catch (RuntimeException e) {
            // Not JSON, or a malformed path. Caller keeps polling.
            return null;
        }
    }

    private static Object child(Object node, String key) {
        if (node instanceof JSONObject && ((JSONObject) node).has(key)) {
            return ((JSONObject) node).get(key);
        }
        return null;
    }

    /**
     * Turns "nothing matched" into something actionable: whether the shim was even installed, and
     * what URLs it DID see. A typo in the URL fragment and a missing install look identical
     * without this.
     */
    private static String diagnose(String urlContains) {
        try {
            if (!isInstalled()) {
                return "The capture shim is NOT installed on this page - call NetworkCapture.install()"
                        + " before the action that triggers the call, and again after any full page load.";
            }
            Object urls = js().executeScript(
                    "return (window.__jswCapture || []).map(function (e) { return e.url; });");
            List<?> list = (urls instanceof List) ? (List<?>) urls : new ArrayList<>();
            if (list.isEmpty()) {
                return "The shim is installed but captured NOTHING - the call may have fired before"
                        + " install(), or the page reloaded and wiped the shim.";
            }
            return "Captured " + list.size() + " response(s), none containing \"" + urlContains
                    + "\". URLs seen: " + list;
        } catch (RuntimeException e) {
            return "(could not read capture state: " + e.getMessage() + ")";
        }
    }

    private static String preview(String body) {
        String flat = body.replaceAll("\\s+", " ").trim();
        return flat.length() <= 300 ? flat : flat.substring(0, 300) + "...";
    }

    private static void sleepQuietly() {
        try {
            Thread.sleep(POLL_MILLIS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for an API response", e);
        }
    }

    private static JavascriptExecutor js() {
        return (JavascriptExecutor) Grid.driver();
    }
}
