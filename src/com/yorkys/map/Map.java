package com.yorkys.map;

import com.yorkys.util.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;
import java.io.IOException;

public class Map implements gameMap {
    private final File sourceWorldFolder;
    private File activeWorldFolder;

    private World bukkitWorld;

    public Map(File worldFolder, String worldName, boolean loadOnIt) {
        this.sourceWorldFolder = new File(worldFolder, worldName);
    }

    public boolean load() {
        if (isLoaded()) {
            return true;
        }

        this.activeWorldFolder = new File(Bukkit.getWorldContainer().getParentFile(), "tempMap");

        try {
            FileUtil.copy(sourceWorldFolder, activeWorldFolder);
        } catch (IOException e) {
            Bukkit.getLogger().severe("Caricamento della mappa fallito");
            e.printStackTrace();
            return false;
        }

        this.bukkitWorld = Bukkit.createWorld(new WorldCreator(activeWorldFolder.getName()));

        if (bukkitWorld != null) this.bukkitWorld.setAutoSave(false);
        return isLoaded();
    }

    public void unload() {
        FileUtil.deleteDirectory(activeWorldFolder);
        if (bukkitWorld != null) Bukkit.unloadWorld(bukkitWorld, false);
        if (activeWorldFolder != null) FileUtil.delete(activeWorldFolder);

        bukkitWorld = null;
        activeWorldFolder = null;
    }

    public boolean restoreFromSource() {
        unload();
        return load();
    }

    public boolean isLoaded() {
        if (getWorld() != null) {
            return true;
        } else {
            return false;
        }
    }

    public World getWorld() {
        return bukkitWorld;
    }

    public File getActiveFile() {
        return activeWorldFolder;
    }
}