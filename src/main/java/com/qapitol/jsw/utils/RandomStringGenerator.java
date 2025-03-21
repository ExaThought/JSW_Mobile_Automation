package com.qapitol.jsw.utils;
import java.security.SecureRandom;
import java.util.Random;

public class RandomStringGenerator {

    // Define the character set to include uppercase letters and numbers
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String getSalesforceOpportunityId(){
        // Generate a random string with a fixed prefix and random sequence
        String randomString = generateRandomString("006Hz00000", 8);
        System.out.println("Generated Random String for salesforce opportunity id is: " + randomString);
        return randomString;
    }

    public static String RandomInteger() {
        Random random = new Random();
        int min = 10000000; // 8 digit minimum value
        int max = 99999999; // 8 digit maximum value
        // Generate random 8 digit number
        int randomNumber = random.nextInt((max - min) + 1) + min;
        System.out.println("Random 8-digit number: " + randomNumber);
        return String.valueOf(randomNumber);
    }

    /**
     * Generates a random string with a given prefix and specified length of random characters.
     * @param prefix The fixed prefix for the random string.
     * @param length The length of the random part of the string.
     * @return A random string.
     */
    public static String generateRandomString(String prefix, int length) {
        StringBuilder sb = new StringBuilder(prefix);

        for (int i = 0; i < length; i++) {
            // Append a random character from the defined character set
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }

        return sb.toString();
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        new Random().ints(length, 0, characters.length())
                .forEach(i -> randomString.append(characters.charAt(i)));
        return randomString.toString();
    }
}

