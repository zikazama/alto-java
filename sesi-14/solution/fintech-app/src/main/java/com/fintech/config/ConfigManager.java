package com.fintech.config;

public class ConfigManager {
    private static ConfigManager instance;

    private ConfigManager() {}

    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    public String getAppConfig() {
        return "Fintech Application Configuration";
    }
}
