package com.yorkys.managers;

import com.yorkys.activity.CheckWin;
import com.yorkys.activity.RestartAfterSeconds;
import com.yorkys.activity.StartCountdown;
import com.yorkys.handlers.ChestGenerator;
import com.yorkys.handlers.GiveScoreboard;
import com.yorkys.mainSkywars;
import com.yorkys.map.gameMap;
import com.yorkys.util.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import java.util.ArrayList;

public class GameManager {
    public final mainSkywars plugin;
    private GameState gameState = GameState.LOBBY;

    private BlockManager blockManager;
    private PlayerManager playerManager;
    private TeamsManager teamsManager;
    private ChestGenerator chestGenerator;
    private gameMap map;

    private StartCountdown startCountdown;
    private CheckWin checkWin;
    private RestartAfterSeconds restartAfterSeconds;

    public GameManager(mainSkywars plugin) {
        this.plugin = plugin;
        this.blockManager = new BlockManager(this);
        this.playerManager = new PlayerManager(this);
        this.teamsManager = new TeamsManager(this, plugin);
        this.chestGenerator = new ChestGenerator(this);
        this.map = plugin.getMap();
    }

    public void setGameState(GameState gameState) {
        if (this.gameState == GameState.ACTIVE && gameState == GameState.STARTING) return;
        if (this.gameState == gameState) return;

        this.gameState = gameState;
        switch(gameState) {
            case ACTIVE:
                if (this.startCountdown != null) this.startCountdown.cancel();

                chestGenerator.spawnChests();
                getTeamsManager().giveTeams();
                getTeamsManager().giveScorB();
                this.checkWin = new CheckWin(this);
                this.checkWin.runTaskTimer(plugin, 0, 20);
                break;
            case STARTING:
                this.startCountdown = new StartCountdown(this);
                this.startCountdown.runTaskTimer(plugin, 0, 20);
                break;
            case WON:
                this.restartAfterSeconds = new RestartAfterSeconds(this);
                this.restartAfterSeconds.runTaskTimer(plugin, 0, 20);
                cleanUp();
                break;
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void cleanUp() {
        FileUtil.deleteDirectory(plugin.getMap().getActiveFile());
        plugin.getMap().unload();
        getPlayerManager().clearAllInv();
        getTeamsManager().reset();
    }
    public mainSkywars getPlugin() {
        return plugin;
    }

    public BlockManager getBlockManager() {return blockManager;}

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public TeamsManager getTeamsManager() {
        return teamsManager;
    }

    public ChestGenerator getChestGenerator() {
        return chestGenerator;
    }

    public Location getLobbyLoc() {
        FileConfiguration config = plugin.getConfig();

        float x = (float) config.getDouble("lobby.x");
        float y = (float) config.getDouble("lobby.y");
        float z = (float) config.getDouble("lobby.z");
        float yaw = (float) config.getDouble("lobby.yaw");
        float pitch = (float) config.getDouble("lobby.pitch");

        Location location = new Location(plugin.getMap().getWorld(), x, y, z, yaw, pitch);
        return location;
    }
}
