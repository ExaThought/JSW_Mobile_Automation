package com.qapitol.jsw.config;

import org.testng.ITestContext;
import org.testng.ITestListener;

public class TestGroupListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        String group = context.getCurrentXmlTest().getParameter("test.group");
        if (group != null && !group.isBlank()) {
            TestGroupContext.set(group);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        TestGroupContext.clear();
    }
}
