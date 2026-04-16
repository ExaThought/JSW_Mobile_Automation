package com.qapitol.jsw.stepdefinition;

import com.qapitol.jsw.utils.ConfigUtil;
import com.qapitol.sauron.core.Grid;
import com.qapitol.sauron.web.AbstractWebPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import static com.qapitol.jsw.utils.Hooks.softAssert;

public class SeoSteps extends AbstractWebPage {

    @And("open sitemap page")
    public void openSitemapPage() {
        String url = ConfigUtil.getSecretValue("ccpPortalUrl")+"sitemap";
        Grid.driver().manage().window().maximize();
        Grid.driver().get(url);
    }

    @Then("Read SEO and validate all the data")
    public void readSEOAndValidateAllTheData() throws IOException {
        //Read environment value
        String env = ConfigUtil.getSecretValue("ccpPortalUrl");
        // Find all links on the sitemap page
        List<WebElement> links=Grid.driver().findElements(By.tagName("a"));
        log.info("Total links found on sitemap page: " + links.size());
        // Loop through each link and validate SEO tags
        for (WebElement link : links) {
            try {
                // Get the URL from the link
                String url = link.getAttribute("href");
                if (url == null || url.isEmpty()) {
                    softAssert.fail("Empty url found for link text: " + link.getText());
                    log.info("Skipping empty url");
                    continue;
                }
               if(url.contains("category")||url.contains("blogs")||url.contains("help-support")||url.contains("about-us")||url.contains("terms-conditions")||url.contains("return-policy")||url.contains("contact-us")||url.contains("privacy_policy")){
                    // Skip if URL is empty or null
                    HttpsURLConnection con = (HttpsURLConnection) new URL(url).openConnection();
                    int responseStatus = con.getResponseCode();

                    BufferedReader reader = null;

                    // Read response body based on status code
                    if (responseStatus >= 200 && responseStatus < 300) {
                        if (con.getInputStream() != null) {
                            reader = new BufferedReader(
                                    new InputStreamReader(con.getInputStream()));
                        }
                    } else {
                        if (con.getErrorStream() != null) {
                            reader = new BufferedReader(
                                    new InputStreamReader(con.getErrorStream()));
                        }
                    }

                    //check if reader is null before proceeding
                    if (reader == null) {
                        softAssert.fail("No response body for URL: " + url + " Status: " + responseStatus);
                        log.info("No response body for URL: " + url + " Status: " + responseStatus);
                        continue;
                    }

                    StringBuilder responseBody = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        responseBody.append(line);
                    }

                    reader.close();

                    Document doc = Jsoup.parse(responseBody.toString());

                    // Extract SEO tags
                    Element canonicalTag = doc.selectFirst("link[rel=canonical]");
                    boolean isCanonicalPresent = canonicalTag != null;
                    Element robotsTag = doc.selectFirst("meta[name=robots]");
                    Element titleTag = doc.selectFirst("title");
                    Element metaDescriptionTag = doc.selectFirst("meta[name=description]");

                    // Assert that all tags are present
                    softAssert.assertNotNull(canonicalTag, "Canonical tag missing: " + url);
                    softAssert.assertNotNull(robotsTag, "Robots tag missing: " + url);
                    softAssert.assertNotNull(titleTag, "Title missing: " + url);
                    softAssert.assertNotNull(metaDescriptionTag, "Meta description missing: " + url);

                    //Check robots content
                    if (isCanonicalPresent) {
                        String robotsContent = robotsTag.attr("content");
                        if (robotsContent.isEmpty()) {
                            softAssert.fail("Robots meta tag content attribute is missing or empty for URL: " + url);
                            log.info("Robots meta tag content attribute is missing or empty for URL: " + url);
                        } else {
                            if (Objects.requireNonNull(env).equalsIgnoreCase("https://www.jswonemsme.com/")) {
                                if (robotsContent.equalsIgnoreCase("index, follow")) {
                                    log.info("Robots meta tag is correctly set to 'index, follow' for production environment for URL: " + url);
                                } else {
                                    softAssert.fail("Robots meta tag content is not as expected for production environment for URL: " + url + ". Found: " + robotsContent);
                                    log.info("Robots meta tag content is not as expected for production environment for URL: " + url + ". Found: " + robotsContent);
                                }
                            } else if (env.equalsIgnoreCase("https://preprod.jswonemsme.com/") || env.equalsIgnoreCase("https://qa-ssr.msme.jswone.in/")) {
                                if (robotsContent.equalsIgnoreCase("noindex, nofollow")) {
                                    log.info("Robots meta tag is correctly set to 'noindex, nofollow' for pre-production environment for URL: " + url);
                                } else {
                                    softAssert.fail("Robots meta tag content is not as expected for pre-production environment for URL: " + url + ". Found: " + robotsContent);
                                    log.info("Robots meta tag content is not as expected for pre-production environment for URL: " + url + ". Found: " + robotsContent);
                                }

                            }
                        }
                    } else {
                        softAssert.fail("Robots meta tag not found for URL: " + url);
                        log.info("Robots meta tag not found for URL: " + url);
                    }
                    log.info("SEO validated for: " + url);

                }
            }
            catch(Exception e){
                softAssert.fail("Error processing URL: " + link.getAttribute("href") + " - " + e.getMessage());
                log.info("Error processing URL: " + link.getAttribute("href"));
            }
        }
    }
}
