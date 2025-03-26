package com.qapitol.jsw.webbdd.stepdefinition;

import com.qapitol.jsw.config.SaveAndLoadCookies;
import com.qapitol.sauron.common.configuration.Config;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;
import com.qapitol.sauron.web.sessions.WebTestSession;
import io.cucumber.java.en.Given;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Set;
import java.util.logging.Level;

public class ZohoBase extends BaseSteps {

    private final SimpleLogger log = SauronLogger.getLogger();

    private static String loginId = "loginId";
    private static String nextButton = "nextButton";
    private static String password = "password";

    @Given("Setup the environment for zoho login")
    public void setupTheEnvironmentForZohoLogin() throws Exception {
        log.entering();
        String platform = System.getProperty("platform");
        if (StringUtils.isEmpty(platform)) {
            platform = Config.getConfigProperty("platform");
        }

        if (platform.equalsIgnoreCase("zoho")) {
            // Switching to "zoho" session present in the sessions.yaml
            Grid.setSessionName("zoho");
            initZohoSession(Config.getConfigProperty("creatorUrl"));
            // Switching to "zoho_books" session present in the sessions.yaml
            Grid.setSessionName("zoho_books");
            initZohoSession(Config.getConfigProperty("booksUrl"));
            Grid.setSessionName("zoho");
        } else if ("erp".equalsIgnoreCase(platform)) {
            // Switching to "erp" session present in the sessions.yaml
            Grid.setSessionName("erp");
            initERPSession();
        }

        log.info("============== Login done during base set up===============");
        log.info("==============Execution Started for one flow============");
        log.exiting();
    }

    @Given("Login to zoho books")
    public void zohoBooksLogin() {
        navigateToZohoBooksSession();
        log.info("Navigated and Logged in to Zoho books ");
//        ExtentCucumberAdapter.addTestStepLog("Navigated and Logged in to Zoho books ");
        closeAlert();
    }

    public void initERPSession() throws Exception {
        log.entering();
        try {
            Grid.driver().manage().window().maximize();
            JavascriptExecutor js = Grid.driver();
            js.executeScript("window.location.href='https://qa-oms.msme.jswone.in/oauth2/redirect?token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2X2FiaW5hc2guYmVoZXJhQGpzdy5pbiIsImF1dGgiOiJST0xFX1VTRVIiLCJ1c2VyX2tleSI6IjFhMTAxM2EzLTY0ZTktNDE1NS1iZjJhLWM3Njk3M2E5NjlkMiIsImlhdCI6MTc0MjAyMTY1MSwiZXhwIjoxNzQ0NjEzNjUxfQ.Y605IuGhliU0ZORoNuLpMAaT2_PYjzYtb6hddYjLUGA1a36JDyexgCLUultl0fw3FDnqS0lx9nWtxBAOVnxrHg';");
            js.executeScript("return window.stop();");
            Grid.driver().navigate().refresh();
        } catch (Exception e) {
            log.log(Level.FINE, "initERPSession failed.", e);
            throw e;
        }
        log.exiting();
    }


    public void initZohoSession(String url) throws Exception {
        log.entering();
        try {
            createAndManageZohoSession(url);
            Grid.driver().get(url);
        } catch (Exception e) {
            log.log(Level.FINE, "initZohoSession failed.", e);
            throw e;
        }
        log.exiting();
    }

    public void createAndManageZohoSession(String url) throws IOException {
        log.entering();
        Path cookiesFilePath = null;
        Path cookiesFilteredFilePath = null;
        if (Grid.getTestSession() instanceof WebTestSession) {
            boolean isHeadless = ((WebTestSession) Grid.getTestSession()).isBrowserRunHeadless();
            if (isHeadless) {
                cookiesFilePath = Paths.get(System.getProperty("user.dir"), "cookies_headless.txt");
                cookiesFilteredFilePath = Paths.get(System.getProperty("user.dir"), "filtered_cookies_headless.txt");
            } else {
                cookiesFilePath = Paths.get(System.getProperty("user.dir"), "cookies.txt");
                cookiesFilteredFilePath = Paths.get(System.getProperty("user.dir"), "filtered_cookies.txt");
            }
        }
        File file = cookiesFilteredFilePath.toFile();
        if (file.exists() && file.isFile()) {
            // Check if the file size is 0
            if (file.length() == 0) {

                log.info("Cookies File is empty, Perform login or actions to establish a session");
                try {
//                    Grid.driver().navigate().refresh();
                    Grid.driver().manage().window().maximize();
                    Grid.driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                    Grid.driver().get(url);
                    loginZohoCreator(Config.getConfigProperty("creatorLoginId"), Config.getConfigProperty("creatorPassword"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                log.info("Step : Save cookies to file");
                Set<Cookie> cookies = Grid.driver().manage().getCookies();
                log.info("Retrieved cookies from input file :" + cookies);

                SaveAndLoadCookies.saveCookiesToFile(cookies, cookiesFilePath.toFile().getAbsolutePath());
                log.info("Close the first driver");
                Grid.driver().manage().deleteAllCookies();
                Grid.driver().get("https://www.google.com/");

                log.info("Filter the usable cookies only");
                SaveAndLoadCookies.filterCookies(cookiesFilePath.toFile().getAbsolutePath(), file.getAbsolutePath());
            } else {
//                Grid.driver().navigate().refresh();
                Grid.driver().manage().window().maximize();
                Grid.driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            }
            log.info("====== Using already created session ======");
            Grid.driver().get(url);

            log.info("Step : Load cookies from file");
            Set<Cookie> loadedCookies = SaveAndLoadCookies.loadCookiesFromFile(cookiesFilteredFilePath.toFile().getAbsolutePath());

            log.info("Step : Add loaded cookies to the new session");
            for (Cookie cookie : loadedCookies) {
                try {
                    Grid.driver().manage().addCookie(cookie);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
//            waitForLoaderToDisappear();
        }
        log.exiting();
    }

    public void loginZohoCreator(String emailId, String pass) {
        log.entering(emailId, pass);
        waitAndCheckIsElementClickable(2, loginId);
        waitAndSetText(loginId, emailId);
        waitAndClickOn(nextButton);
        waitAndCheckIsElementClickable(2, password);
        waitAndSetText(password, pass);
        waitAndClickOn(nextButton);
        waitForLoaderToDisappear();
        log.exiting();
    }
}
