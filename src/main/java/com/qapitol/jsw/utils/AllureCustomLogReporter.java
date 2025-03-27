package com.qapitol.jsw.utils;

import io.qameta.allure.Allure;

import static java.util.Objects.isNull;

public class AllureCustomLogReporter {
    private static AllureCustomLogReporter allureCustomLogReporter;

    private AllureCustomLogReporter(){

    }

    public static AllureCustomLogReporter getInstance(){
        if(isNull(allureCustomLogReporter)){
            allureCustomLogReporter = new AllureCustomLogReporter();
        }
        return allureCustomLogReporter;
    }

    public void addTestStepLog(String msg){
        Allure.addAttachment("Jsw_Log", msg);
    }
}
