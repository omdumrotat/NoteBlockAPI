package com.xxmicloxx.NoteBlockAPI.utils;

import com.tcoded.folialib.FoliaLib;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

/**
 * Scheduler utility class that provides compatibility between Bukkit/Paper and Folia
 */
public class SchedulerUtils {
    
    private static FoliaLib foliaLib;
    
    /**
     * Initialize the scheduler with FoliaLib
     * @param plugin The plugin instance
     */
    public static void initialize(Plugin plugin) {
        foliaLib = new FoliaLib(plugin);
    }
    
    /**
     * Run a task synchronously
     * @param runnable The task to run
     */
    public static void runTask(Runnable runnable) {
        if (foliaLib != null) {
            foliaLib.getImpl().runNextTick(runnable);
        } else {
            // Fallback for if FoliaLib isn't initialized
            Bukkit.getScheduler().runTask(getPlugin(), runnable);
        }
    }
    
    /**
     * Run a task synchronously on the entity's region thread (Folia-specific)
     * @param entity The entity to run the task for
     * @param runnable The task to run
     */
    public static void runTask(Entity entity, Runnable runnable) {
        if (foliaLib != null) {
            foliaLib.getImpl().runAtEntity(entity, runnable);
        } else {
            // Fallback for Bukkit/Paper
            runTask(runnable);
        }
    }
    
    /**
     * Run a task synchronously on the location's region thread (Folia-specific)
     * @param location The location to run the task for
     * @param runnable The task to run
     */
    public static void runTask(Location location, Runnable runnable) {
        if (foliaLib != null) {
            foliaLib.getImpl().runAtLocation(location, runnable);
        } else {
            // Fallback for Bukkit/Paper
            runTask(runnable);
        }
    }
    
    /**
     * Run a task asynchronously
     * @param runnable The task to run
     */
    public static void runTaskAsync(Runnable runnable) {
        if (foliaLib != null) {
            foliaLib.getImpl().runAsync(runnable);
        } else {
            // Fallback for if FoliaLib isn't initialized
            Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), runnable);
        }
    }
    
    /**
     * Run a task after a delay
     * @param runnable The task to run
     * @param delay Delay in ticks
     */
    public static void runTaskLater(Runnable runnable, long delay) {
        if (foliaLib != null) {
            foliaLib.getImpl().runLater(runnable, delay);
        } else {
            // Fallback for if FoliaLib isn't initialized
            Bukkit.getScheduler().runTaskLater(getPlugin(), runnable, delay);
        }
    }
    
    /**
     * Run a repeating task asynchronously
     * @param runnable The task to run
     * @param delay Initial delay in ticks
     * @param period Period between runs in ticks
     */
    public static void runTaskTimerAsync(Runnable runnable, long delay, long period) {
        if (foliaLib != null) {
            foliaLib.getImpl().runTimerAsync(runnable, delay, period);
        } else {
            // Fallback for if FoliaLib isn't initialized
            Bukkit.getScheduler().runTaskTimerAsynchronously(getPlugin(), runnable, delay, period);
        }
    }
    
    /**
     * Cancel all tasks for the plugin
     */
    public static void cancelTasks() {
        if (foliaLib != null) {
            foliaLib.getImpl().cancelAllTasks();
        } else {
            // Fallback for if FoliaLib isn't initialized
            Bukkit.getScheduler().cancelTasks(getPlugin());
        }
    }
    
    /**
     * Get the plugin instance
     * @return Plugin instance
     */
    private static Plugin getPlugin() {
        // This will be set by the main plugin class
        return com.xxmicloxx.NoteBlockAPI.NoteBlockAPI.getAPI();
    }
}