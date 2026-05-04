package com.qapitol.jsw.config;

public final class TestGroupContext {
    private static final ThreadLocal<String> CURRENT = new ThreadLocal<>();

    private TestGroupContext() {}

    public static void set(String group) {
        CURRENT.set(group);
    }

    public static String get() {
        return CURRENT.get();
    }

    public static void clear() {
        CURRENT.remove();
    }
}
