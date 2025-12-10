package com.qapitol.jsw.utils;

import com.google.gson.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MoEngageUtil {

    // Helper class to return both driver and proxy
    public static class ProxyDriverPair {
        public WebDriver driver;
        public BrowserMobProxy proxy;

        public ProxyDriverPair(WebDriver driver, BrowserMobProxy proxy) {
            this.driver = driver;
            this.proxy = proxy;
        }
    }

    public static ProxyDriverPair startProxy() {
        // Start BrowserMob Proxy
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true);
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        proxy.start(0);

        // Configure Selenium to use the proxy
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        ChromeOptions options = new ChromeOptions();
        options.setProxy(seleniumProxy);
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-insecure-localhost");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);

        proxy.newHar("moengage-events");
        return new ProxyDriverPair(driver, proxy);
    }

    public static void captureMoEngageEvents(BrowserMobProxy proxy) {
        System.out.println("MoEngage Event Audit:");
        for (HarEntry entry : proxy.getHar().getLog().getEntries()) {
            String url = entry.getRequest().getUrl();
            if (url.contains("moengage") && entry.getRequest().getMethod().equalsIgnoreCase("POST")) {
                if (entry.getRequest().getPostData() != null) {
                    String postData = entry.getRequest().getPostData().getText();
                    try {
                        JsonObject root = JsonParser.parseString(postData).getAsJsonObject();
                        if (root.has("viewsInfo")) {
                            JsonArray views = root.getAsJsonArray("viewsInfo");
                            for (JsonElement view : views) {
                                JsonObject event = view.getAsJsonObject();
                                String action = event.has("EVENT_ACTION") ? event.get("EVENT_ACTION").getAsString() : "Unknown";
                                JsonObject attrs = event.has("EVENT_ATTRS") ? event.getAsJsonObject("EVENT_ATTRS") : new JsonObject();
                                System.out.println("EVENT_ACTION: " + action);
                                System.out.println("EVENT_ATTRS: " + attrs);
                                System.out.println("--------------------------------------------------------------------------");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Failed to parse payload: " + postData);
                    }
                } else {
                    System.out.println("Skipped request with no POST data: " + url);
                }
            }
        }
    }
}
