package com.yorkys;

import com.yorkys.commands.*;
import com.yorkys.handlers.BlockBreak;
import com.yorkys.handlers.HandleTeams;
import com.yorkys.handlers.JoinLobby;
import com.yorkys.managers.GameManager;
import com.yorkys.map.Map;
import com.yorkys.map.gameMap;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class mainSkywars extends JavaPlugin {

    private static mainSkywars istance;
    private GameManager gameManager;
    private static gameMap map;

    @Override
    public void onEnable() {
        getDataFolder().mkdirs();

        File gameMapsFolder = new File(getDataFolder(), "maps");
        if (!gameMapsFolder.exists()) {
            gameMapsFolder.mkdirs();
        }

        map = new Map(gameMapsFolder, "map1", true);

        new Reset(gameManager, map);
        new Warp(map);

        istance = this;
        this.gameManager = new GameManager(this);

        Bukkit.getPluginManager().registerEvents(new BlockBreak(gameManager), this);
        Bukkit.getPluginManager().registerEvents(new JoinLobby(gameManager), this);
        Bukkit.getPluginManager().registerEvents(new HandleTeams(gameManager, this), this);

        saveDefaultConfig();

        new Utils();
        new setSpawns(this);
        new setLobby(this);
        new setChests(this);
        new WorldTp(this);
        new StartCommand(gameManager);
    }

    @Override
    public void onDisable() {
        gameManager.cleanUp();
    }

    public static mainSkywars getInstance() {
        return istance;
    }
    public static gameMap getMap() { return map; }
}
