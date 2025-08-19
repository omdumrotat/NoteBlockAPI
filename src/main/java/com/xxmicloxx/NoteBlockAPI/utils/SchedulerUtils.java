package com.xxmicloxx.NoteBlockAPI.utils;

import com.tcoded.folialib.FoliaLib;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.CompletableFuture;

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
            foliaLib.getScheduler().runNextTick(task -> runnable.run());
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
            foliaLib.getScheduler().runAtEntity(entity, task -> runnable.run());
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
            foliaLib.getScheduler().runAtLocation(location, task -> runnable.run());
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
            foliaLib.getScheduler().runAsync(task -> runnable.run());
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
            foliaLib.getScheduler().runLater(task -> runnable.run(), delay);
        } else {
            // Fallback for if FoliaLib isn't initialized
            Bukkit.getScheduler().runTaskLater(getPlugin(), runnable, delay);
        }
    }
    
    /**
     * Run a repeating task synchronously
     * @param runnable The task to run
     * @param delay Initial delay in ticks
     * @param period Period between runs in ticks
     */
    public static void runTaskTimer(Runnable runnable, long delay, long period) {
        if (foliaLib != null) {
            foliaLib.getScheduler().runTimer(task -> runnable.run(), delay, period);
        } else {
            // Fallback for if FoliaLib isn't initialized
            Bukkit.getScheduler().runTaskTimer(getPlugin(), runnable, delay, period);
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
            foliaLib.getScheduler().runTimerAsync(task -> runnable.run(), delay, period);
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
            foliaLib.getScheduler().cancelAllTasks();
        } else {
            // Fallback for if FoliaLib isn't initialized
            Bukkit.getScheduler().cancelTasks(getPlugin());
        }
    }
    
    /**
     * Check if the server is running Folia
     * @return true if running Folia, false otherwise
     */
    public static boolean isFolia() {
        return foliaLib != null && foliaLib.isFolia();
    }
    
    /**
     * Check if the server is running Paper
     * @return true if running Paper, false otherwise
     */
    public static boolean isPaper() {
        return foliaLib != null && foliaLib.isPaper();
    }
    
    /**
     * Check if the server is running Spigot
     * @return true if running Spigot, false otherwise
     */
    public static boolean isSpigot() {
        return foliaLib != null && foliaLib.isSpigot();
    }
    
    /**
     * Teleport an entity asynchronously (Folia-compatible)
     * @param entity The entity to teleport
     * @param location The location to teleport to
     * @return CompletableFuture indicating success/failure
     */
    public static CompletableFuture<Boolean> teleportAsync(Entity entity, Location location) {
        if (foliaLib != null) {
            return foliaLib.getScheduler().teleportAsync(entity, location);
        } else {
            // Fallback for Bukkit/Paper - run on next tick
            CompletableFuture<Boolean> future = new CompletableFuture<>();
            runTask(() -> {
                try {
                    boolean result = entity.teleport(location);
                    future.complete(result);
                } catch (Exception e) {
                    future.completeExceptionally(e);
                }
            });
            return future;
        }
    }
    
    /**
     * Teleport an entity asynchronously with teleport cause (Folia-compatible)
     * @param entity The entity to teleport
     * @param location The location to teleport to
     * @param cause The teleport cause
     * @return CompletableFuture indicating success/failure
     */
    public static CompletableFuture<Boolean> teleportAsync(Entity entity, Location location, PlayerTeleportEvent.TeleportCause cause) {
        if (foliaLib != null) {
            return foliaLib.getScheduler().teleportAsync(entity, location, cause);
        } else {
            // Fallback for Bukkit/Paper - run on next tick
            CompletableFuture<Boolean> future = new CompletableFuture<>();
            runTask(() -> {
                try {
                    boolean result = entity.teleport(location, cause);
                    future.complete(result);
                } catch (Exception e) {
                    future.completeExceptionally(e);
                }
            });
            return future;
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