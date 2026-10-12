package com.qapitol.jsw.utils;

import com.qapitol.sauron.logging.SauronLogger;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;

import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Keeps access tokens out of the console, the log files and the reports.
 *
 * <p>The tokens are not printed by this repo's own code. Sauron's API client registers its
 * {@code JaxRsFilter} through a {@code META-INF/services} file inside the Sauron jar, and that
 * filter logs every request's headers - including the {@code access_token} that LoginApiImpl
 * adds to every cart call - through Sauron's java.util.logging logger. The jar cannot be changed
 * from here, so the values are masked on the way out instead: a logging filter rewrites each
 * record before any handler formats it.
 *
 * <p>RestAssured is covered separately: its {@code log().ifValidationFails()} prints headers to
 * stdout, not through java.util.logging, so the secret headers are blacklisted in its config.
 */
public final class LogRedaction {

    private static final String MASK = "***redacted***";

    /** A JWT anywhere in a message: three base64url parts, the first starting with eyJ. */
    private static final Pattern JWT =
            Pattern.compile("eyJ[A-Za-z0-9_-]{5,}\\.[A-Za-z0-9_-]{5,}\\.[A-Za-z0-9_-]*");

    /**
     * The value after a secret header or field name, in the shapes the logs use:
     * {@code access_token : [value]}, {@code "jwtToken":"value"}, {@code Authorization=Bearer value}.
     */
    private static final Pattern SECRET_VALUE = Pattern.compile(
            "(?i)((?:access_token|accessToken|authorization|x-api-key|jwtToken|id_token)\"?\\s*[:=]\\s*\\[?\"?"
                    + "(?:Bearer\\s+)?)([^\\s\\],\"]+)");

    private static final String[] SECRET_HEADERS = {"access_token", "Authorization", "X-API-KEY"};

    private LogRedaction() {
    }

    /** The text with every token and secret header value replaced. */
    public static String redact(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        String masked = JWT.matcher(text).replaceAll(MASK);
        Matcher m = SECRET_VALUE.matcher(masked);
        return m.replaceAll("$1" + Matcher.quoteReplacement(MASK));
    }

    /**
     * Installs the masking. Safe to call repeatedly - it is called before every scenario, which
     * also catches handlers added after the first call.
     */
    public static synchronized void install() {
        Logger sauron = SauronLogger.getLogger();
        guard(sauron);
        for (Logger l = sauron.getParent(); l != null; l = l.getParent()) {
            guard(l);
        }
        guard(Logger.getLogger(""));

        LogConfig logConfig = RestAssured.config().getLogConfig();
        RestAssured.config = RestAssured.config().logConfig(logConfig.blacklistHeaders(
                java.util.Arrays.asList(SECRET_HEADERS)));
    }

    /** Masks on the logger and on each of its handlers, so no path to the output is missed. */
    private static void guard(Logger logger) {
        if (!(logger.getFilter() instanceof RedactingFilter)) {
            logger.setFilter(new RedactingFilter(logger.getFilter()));
        }
        for (Handler handler : logger.getHandlers()) {
            if (!(handler.getFilter() instanceof RedactingFilter)) {
                handler.setFilter(new RedactingFilter(handler.getFilter()));
            }
        }
    }

    /** Rewrites the record's message, then defers to whatever filter was there before. */
    private static final class RedactingFilter implements Filter {
        private final Filter previous;

        RedactingFilter(Filter previous) {
            this.previous = previous;
        }

        @Override
        public boolean isLoggable(LogRecord record) {
            String message = record.getMessage();
            if (message != null) {
                Object[] params = record.getParameters();
                if (params != null) {
                    for (int i = 0; i < params.length; i++) {
                        if (params[i] instanceof String) {
                            params[i] = redact((String) params[i]);
                        }
                    }
                }
                String redacted = redact(message);
                if (!redacted.equals(message)) {
                    record.setMessage(redacted);
                }
            }
            return previous == null || previous.isLoggable(record);
        }
    }
}
