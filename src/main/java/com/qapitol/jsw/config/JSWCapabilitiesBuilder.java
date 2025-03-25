package com.qapitol.jsw.config;

import com.qapitol.sauron.common.capabilities.DefaultCapabilitiesBuilder;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class JSWCapabilitiesBuilder extends DefaultCapabilitiesBuilder {
    @Override
    public DesiredCapabilities getCapabilities(DesiredCapabilities desiredCapabilities) {
        Object chromeOptionCapsObj = desiredCapabilities.getCapability(ChromeOptions.CAPABILITY);
        if(chromeOptionCapsObj instanceof ChromeOptions) {
            ChromeOptions existingChromeOptions = (ChromeOptions) chromeOptionCapsObj;
            ChromeOptions customChromeOptions = new ChromeOptions();
            customChromeOptions.addArguments("--remote-allow-origins=*");
            existingChromeOptions.merge(customChromeOptions);

            desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, existingChromeOptions);
        }
        return desiredCapabilities;
    }
}
