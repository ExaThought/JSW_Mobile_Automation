package com.qapitol.jsw.config;

import com.qapitol.sauron.common.configuration.AbstractConfigInitializer;
import com.qapitol.sauron.common.configuration.Config;
import org.testng.ISuite;
import org.testng.ITestContext;

public class GlobalConfigProperties extends AbstractConfigInitializer {
    private static final String PROP_FILENAME = "global_qa.properties";

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void initialize(ITestContext iTestContext) {

    }

    @Override
    public void initialize(ISuite iSuite) {
        Config.loadProperties(PROP_FILENAME);
    }
}
