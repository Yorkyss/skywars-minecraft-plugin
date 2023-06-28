package com.yorkys.map;

import org.bukkit.World;

import java.io.File;

public interface gameMap {
    boolean load();
    void unload();
    boolean restoreFromSource();

    boolean isLoaded();
    World getWorld();
    File getActiveFile();
}