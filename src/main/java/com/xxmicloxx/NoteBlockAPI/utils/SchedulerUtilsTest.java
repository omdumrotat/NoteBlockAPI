package com.xxmicloxx.NoteBlockAPI.utils;

import org.bukkit.plugin.Plugin;

/**
 * Simple test class to verify SchedulerUtils functionality
 */
public class SchedulerUtilsTest {
    
    /**
     * Basic test to ensure SchedulerUtils initializes properly
     * @param plugin The plugin instance to test with
     * @return true if initialization was successful
     */
    public static boolean testInitialization(Plugin plugin) {
        try {
            SchedulerUtils.initialize(plugin);
            
            // Test a simple task to see if it doesn't throw exceptions
            SchedulerUtils.runTask(() -> {
                System.out.println("SchedulerUtils test: Basic task executed successfully");
            });
            
            return true;
        } catch (Exception e) {
            System.err.println("SchedulerUtils test failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Test async scheduling
     * @return true if async task scheduling works
     */
    public static boolean testAsyncTask() {
        try {
            SchedulerUtils.runTaskAsync(() -> {
                System.out.println("SchedulerUtils test: Async task executed successfully");
            });
            return true;
        } catch (Exception e) {
            System.err.println("SchedulerUtils async test failed: " + e.getMessage());
            return false;
        }
    }
}