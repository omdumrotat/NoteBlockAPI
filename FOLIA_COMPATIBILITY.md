# Folia Compatibility

This version of NoteBlockAPI has been updated to support Folia, a high-performance Minecraft server software that uses region-based threading.

## What Changed

- Added FoliaLib dependency for cross-platform compatibility
- Created `SchedulerUtils` abstraction layer for scheduling tasks
- Updated all scheduler calls to use region-aware scheduling where appropriate
- Updated all event firing to happen on the correct region thread

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

2. **Event Handling**: Location and entity-based events are now fired on the appropriate region thread for Folia compatibility.

3. **Dependency Management**: FoliaLib is shaded into the plugin to provide the compatibility layer.

## For Developers

If you're extending this plugin or using its API, the public interface remains the same. All scheduling is handled internally through the abstraction layer.