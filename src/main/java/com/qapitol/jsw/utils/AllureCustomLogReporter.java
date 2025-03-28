package com.qapitol.jsw.utils;

import io.qameta.allure.Allure;

import static java.util.Objects.isNull;

public class AllureCustomLogReporter {
    private static AllureCustomLogReporter allureCustomLogReporter;

    private AllureCustomLogReporter() {

    }

    public static AllureCustomLogReporter getInstance() {
        if (isNull(allureCustomLogReporter)) {
            allureCustomLogReporter = new AllureCustomLogReporter();
        }
        return allureCustomLogReporter;
    }

    public static void addTestStepLog(String msg) {
        getInstance().callAllureAttachment(msg);
    }

    private void callAllureAttachment(String msg) {
        Allure.addAttachment("Jsw_Log", msg);
    }
}
