# Folia Compatibility

This version of NoteBlockAPI has been updated to support Folia, a high-performance Minecraft server software that uses region-based threading.

## What Changed

- Added FoliaLib 0.5.1 dependency for cross-platform compatibility
- Added TCoded repository for proper FoliaLib dependency resolution
- Created `SchedulerUtils` abstraction layer for scheduling tasks
- Updated all scheduler calls to use region-aware scheduling where appropriate
- Updated all event firing to happen on the correct region thread
- Added `folia-supported: true` flag to plugin.yml for explicit Folia compatibility
- Updated API calls to use FoliaLib 0.5.1's Consumer-based scheduling interface

## Compatibility

This version maintains full backward compatibility with:
- Bukkit
- Spigot  
- Paper
- Folia (new)

The plugin automatically detects the server type and uses the appropriate scheduling mechanism.

## Technical Details

The main changes include:

1. **Scheduler Abstraction**: All scheduler calls now go through `SchedulerUtils` which provides region-aware scheduling for Folia while maintaining compatibility with other server types.

2. **API Updates**: Updated to FoliaLib 0.5.1 which uses a Consumer-based API instead of Runnable-based scheduling for better task management.

3. **Event Handling**: Location and entity-based events are now fired on the appropriate region thread for Folia compatibility.

4. **Dependency Management**: FoliaLib is shaded into the plugin to provide the compatibility layer with proper repository configuration.

5. **Platform Detection**: Added utility methods to detect if running on Folia, Paper, or Spigot for conditional behavior.

## For Developers

If you're extending this plugin or using its API, the public interface remains the same. All scheduling is handled internally through the abstraction layer. The plugin provides additional utility methods for:

- Platform detection (`SchedulerUtils.isFolia()`, `SchedulerUtils.isPaper()`, `SchedulerUtils.isSpigot()`)
- Async teleportation (`SchedulerUtils.teleportAsync()`)
- Region-aware task scheduling

## Repository Configuration

For projects depending on this plugin or FoliaLib directly, ensure you have the TCoded repository configured:

```xml
<repository>
    <id>tcoded-releases</id>
    <url>https://repo.tcoded.com/releases</url>
</repository>
```