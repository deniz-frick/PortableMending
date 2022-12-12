# Portable Mending
A Spigot Plugin that enables the use of stored XP for mending



## Usage

This Plugin only adds a single command:

**/mend [group]** (aliases: mnd, md)

When no grouping is given, the currently held item gets mended.


Groupings:
+ **all**

   Entire inventory
+ **hands**

  Hand and off-hand
+ **armor**
  
  All four armor slots
+ **equip**

  Both hands, armor, and hotbar


## Configuration

The `DurabilityPerXp` can be changed in the config file.
Per default this value is set to 2 Durability point per 1 XP added (same as Vanilla Mending).

This can also be a decimal. E.g. 0.5 would mean that 4 XP is used to mend 1 durability.

## Compatibility

This Plugin is built with Bukkit 1.13 and will work with any subsequent version (confirmed to work with 1.19.3).
If you encounter errors, please create an issue on this repo.

## Permissions

The Permission `portableMend.mend` allows players to use the mend command and is given to all players as default.
