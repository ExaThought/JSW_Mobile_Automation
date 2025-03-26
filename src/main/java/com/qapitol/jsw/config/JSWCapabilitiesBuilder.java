package com.qapitol.jsw.config;

import com.qapitol.sauron.common.capabilities.DefaultCapabilitiesBuilder;
import com.qapitol.sauron.web.sessions.WebTestSession;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

public class JSWCapabilitiesBuilder extends DefaultCapabilitiesBuilder {
    @Override
    public DesiredCapabilities getCapabilities(DesiredCapabilities desiredCapabilities) {
        String userProfilePath = null;
        if (getTestSession() instanceof WebTestSession) {
            boolean isHeadless = ((WebTestSession) getTestSession()).isBrowserRunHeadless();
            if (isHeadless) {
                Path userProfileDirPath = Paths.get(System.getProperty("user.dir"), "profiles", getTestSession().getSessionName().trim());
                File userProfileDir = userProfileDirPath.toFile();
                if (!userProfileDir.exists()) {
                    try {
                        Files.createDirectories(userProfileDirPath);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                userProfilePath = userProfileDir.getAbsolutePath();
            }
        }
        Object chromeOptionCapsObj = desiredCapabilities.getCapability(ChromeOptions.CAPABILITY);
        if (chromeOptionCapsObj instanceof Map) {
            ChromeOptions customChromeOptions = getChromeOptions((Map<String, Object>) chromeOptionCapsObj, userProfilePath);
            desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, customChromeOptions);
        }
        return desiredCapabilities;
    }

    private ChromeOptions getChromeOptions(Map<String, Object> chromeOptionCapsObj, String userProfilePath) {
        ChromeOptions customChromeOptions = new ChromeOptions();

        Map<String, Object> existingOptionsMap = chromeOptionCapsObj;
        if (existingOptionsMap.containsKey("args")) {
            List<String> existingArgs = (List<String>) existingOptionsMap.get("args");
            List<String> newArgs = new ArrayList<>(existingArgs);
            if (nonNull(userProfilePath)) {
                newArgs.add("user-data-dir=" + userProfilePath);
            }
            customChromeOptions.addArguments(newArgs);
        }
        return customChromeOptions;
    }
}
