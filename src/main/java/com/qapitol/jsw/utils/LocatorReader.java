package com.qapitol.jsw.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class LocatorReader {

    private final Map<String, Object> locators;


    public LocatorReader(String yamlFilePath) {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(yamlFilePath)) {
            if (in == null) {
                throw new RuntimeException(yamlFilePath + " not found in classpath!");
            }
            Yaml yaml = new Yaml();
            locators = yaml.load(in);
            System.out.println("Locators loaded from " + yamlFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load locators YAML: " + e.getMessage(), e);
        }
    }


    @SuppressWarnings("unchecked")
    public String getXpath(String key) {
        String[] parts = key.split("\\.");
        Object current = locators;

        for (String part : parts) {
            if (current instanceof Map) {
                current = ((Map<String, Object>) current).get(part);
                if (current == null) {
                    System.err.println("Key not found in YAML: " + key);
                    return null;
                }
            } else {
                System.err.println("Unexpected type while traversing YAML for key: " + key);
                return null;
            }
        }

        // If final object is string, treat as XPath
        if (current instanceof String) {
            String value = (String) current;
            if (value.startsWith("xpath=")) value = value.substring("xpath=".length());
            return value;
        }

        // If final object is map, get "web"
        if (current instanceof Map) {
            Object web = ((Map<String, Object>) current).get("web");
            if (web instanceof String) {
                String value = (String) web;
                if (value.startsWith("xpath=")) value = value.substring("xpath=".length());
                return value;
            } else {
                System.err.println("'web' key not found or not a string for: " + key);
            }
        }

        return null;
    }

    /** Debug method to print all loaded locators */
    public void printAllLocators() {
        if (locators != null) {
            System.out.println("Loaded locators: " + locators.keySet());
        } else {
            System.err.println("No locators loaded");
        }
    }
}
