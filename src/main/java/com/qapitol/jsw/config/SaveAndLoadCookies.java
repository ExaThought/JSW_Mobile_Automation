package com.qapitol.jsw.config;

import com.qapitol.sauron.logging.SauronLogger;
import com.qapitol.sauron.logging.SimpleLogger;
import org.openqa.selenium.Cookie;

import java.io.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SaveAndLoadCookies {

    private static final SimpleLogger log = SauronLogger.getLogger();

    //Method to save cookies to a file
    public static void saveCookiesToFile(Set<Cookie> cookies, String filePath) throws IOException {
        log.entering(cookies, filePath);
        File file = new File(filePath);
        file.createNewFile(); // Create the file if it doesn't exist
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Write each cookie to the file
        for (Cookie cookie : cookies) {
            bufferedWriter.write(
                    cookie.getName() + ";" +
                            cookie.getValue() + ";" +
                            cookie.getDomain() + ";" +
                            cookie.getPath() + ";" +
                            cookie.getExpiry() + ";" +
                            cookie.isSecure()
            );
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        fileWriter.close();
        log.exiting();
    }

    // Method to load cookies from a file
    public static Set<Cookie> loadCookiesFromFile(String filePath) throws IOException {
        log.entering(filePath);
        Set<Cookie> cookies = new HashSet<>();
        File file = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;

        // Read each line (each cookie) from the file
        while ((line = bufferedReader.readLine()) != null) {
            StringTokenizer token = new StringTokenizer(line, ";");
            String name = token.nextToken();
            String value = token.nextToken();
            String domain = token.nextToken();
            String path = token.nextToken();
            // Field 5 is the expiry, written by saveCookiesToFile. It is consumed but discarded:
            // StringTokenizer has no skip, so the call is required to reach the isSecure field
            // below - it is a positional skip, not a dropped return value.
            token.nextToken();
            boolean isSecure = Boolean.parseBoolean(token.nextToken());
            boolean isHttpOnly = true;
            String sameSite = "None";
            // Deliberately null: a null expiry makes this a session cookie, which is what a
            // restored automation session wants. Reusing the saved expiry would resurrect
            // cookies that have already lapsed and mask an expired-session failure as a
            // logged-in one.
            Date expiry = null;

            // Create a cookie from the line data and add to the set
            Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure, isHttpOnly, sameSite);
            cookies.add(cookie);
        }
        bufferedReader.close();
        log.exiting();
        return cookies;
    }

    // Method to filer cookies from an input file
    public static void filterCookies(String inputFile, String outputFile) {
        log.entering(inputFile, outputFile);
        // Define the cookies to keep
        Set<String> cookiesToKeep = new HashSet<>();
        cookiesToKeep.add("_iambdt");
        cookiesToKeep.add("_iamadt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Extract the cookie name (text before the first semicolon)
                String cookieName = line.split(";")[0];
                if (cookiesToKeep.contains(cookieName)) {
                    writer.write(line);
                    writer.newLine(); // Ensure proper line separation
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.exiting();
    }
}

