package com.qapitol.jsw.config;

import com.qapitol.sauron.common.capabilities.DefaultCapabilitiesBuilder;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class JSWCapabilitiesBuilder extends DefaultCapabilitiesBuilder {
    @Override
    public DesiredCapabilities getCapabilities(DesiredCapabilities desiredCapabilities) {
//        if (getTestSession().getSessionName().contains("zoho")){
//            if(getTestSession().getPlatform().getPlatform().toLowerCase().contains("chrome")) {
//                Map<String, Object> existingOptions = (Map<String, Object>) desiredCapabilities.getCapability(ChromeOptions.CAPABILITY);
//                Map<String, Object> expChromeOptions = new HashMap<>(existingOptions);
////                expChromeOptions.put("args", expChromeOptions.get("args"));
//
////                ChromeOptions chromeOptions_new = new ChromeOptions();
////                chromeOptions_new.setEnableDownloads(true)
////
////
////                expOptions.put("excludeSwitches","disable-popup-blocking");
////                // Construct the path to the DownloadDir file
////                Path path = Paths.get(System.getProperty("user.dir"), "download_dir");
////                expOptions.put("download.default_directory", path.toFile().getAbsolutePath());
////                options.setExperimentalOption("prefs", expOptions);
////                options.addArguments("--remote-allow-origins=*");
//                desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, expChromeOptions);
//            }
//        }
        return desiredCapabilities;
    }
}
