package com.qapitol.jsw.config;

import com.qapitol.sauron.common.capabilities.DefaultCapabilitiesBuilder;
import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.web.sessions.WebTestSession;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
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
                String testGroup = TestGroupContext.get();
                if (testGroup == null || testGroup.isBlank()) {
                    testGroup = Config.getConfigProperty("test.group");
                }
                String profileSuffix = (testGroup != null && !testGroup.isBlank())
                        ? testGroup
                        : String.valueOf(Thread.currentThread().getId());
                Path userProfileDirPath = Paths.get(System.getProperty("user.dir"), "profiles",
                        getTestSession().getSessionName().trim() + "_" + profileSuffix);
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
            boolean isHeadless = getTestSession() instanceof WebTestSession
                    && ((WebTestSession) getTestSession()).isBrowserRunHeadless();
            ChromeOptions customChromeOptions = getChromeOptions(
                    (Map<String, Object>) chromeOptionCapsObj, userProfilePath, isHeadless);
            desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, customChromeOptions);
        }
        return desiredCapabilities;
    }

    private ChromeOptions getChromeOptions(Map<String, Object> chromeOptionCapsObj, String userProfilePath,
                                           boolean isHeadless) {
        ChromeOptions customChromeOptions = new ChromeOptions();

        List<String> args = new ArrayList<>();
        if (chromeOptionCapsObj.containsKey("args")) {
            args.addAll((List<String>) chromeOptionCapsObj.get("args"));
        }

        if (nonNull(userProfilePath)) {
           // args.add("--user-data-dir=" + userProfilePath);
        } else {
            addArgIfMissing(args, "--incognito");
        }

        if (isHeadless) {
            addArgIfMissing(args, "--headless=new");
            addArgIfMissing(args, "--disable-gpu");
        }

        addArgIfMissing(args, "--no-sandbox");
        addArgIfMissing(args, "--disable-dev-shm-usage");
        addArgIfMissing(args, "--disable-blink-features=AutomationControlled");
        addArgIfMissing(args, "--window-size=1920,1080");
        addArgIfMissing(args, "--disable-extensions");
        addArgIfMissing(args, "--remote-allow-origins=*");
        // Auto-dismiss native permission prompts (location, camera, mic). These are browser
        // chrome, not DOM, so Selenium cannot close them from a step definition. The flag
        // works even in --incognito, where profile prefs are not always honoured.
        addArgIfMissing(args, "--deny-permission-prompts");

        customChromeOptions.addArguments(args);

        Map<String, Object> prefs = new HashMap<>();
        if (chromeOptionCapsObj.get("prefs") instanceof Map) {
            prefs.putAll((Map<String, Object>) chromeOptionCapsObj.get("prefs"));
        }
        prefs.put("profile.default_content_setting_values.notifications", 2);
        // 2 = block. Stops the "<site> wants to know your location" prompt from appearing.
        prefs.put("profile.default_content_setting_values.geolocation", 2);
        customChromeOptions.setExperimentalOption("prefs", prefs);

        return customChromeOptions;
    }

    private static void addArgIfMissing(List<String> args, String flag) {
        boolean present = args.stream().anyMatch(arg ->
                arg.equals(flag) || arg.startsWith(flag + "="));
        if (!present) {
            args.add(flag);
        }
    }
}
