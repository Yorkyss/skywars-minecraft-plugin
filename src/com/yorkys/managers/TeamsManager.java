package com.yorkys.managers;

import com.yorkys.handlers.GiveScoreboard;
import com.yorkys.mainSkywars;
import com.yorkys.util.Msg;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamsManager {
    private GameManager gameManager;
    private mainSkywars plugin;
    private List<Player> playersList;
    private List<Team> teamsList = new ArrayList<Team>();
    private GiveScoreboard giveScoreboard;

    public TeamsManager(GameManager gameManager, mainSkywars plugin) {
        this.gameManager = gameManager;
        this.plugin = plugin;
    }

    public void giveTeams() {
        FileConfiguration config = plugin.getConfig();

        playersList = new ArrayList<>(Bukkit.getOnlinePlayers());
        if (playersList.size() > 8) {
            Player kicking = playersList.remove(playersList.size() - 1);
            kicking.kickPlayer("Server pieno!");
        }

        for (Player player : playersList) {
            teamsList.add(new Team(player, gameManager));
        }

        int cont = 1;
        for (Team team : teamsList) {
            Player player = team.getPlayer();
            float x = (float) config.getDouble("teams.t" + cont + ".spawn.x");
            float y = (float) config.getDouble("teams.t" + cont + ".spawn.y");
            float z = (float) config.getDouble("teams.t" + cont + ".spawn.z");
            float yaw = (float) config.getDouble("teams.t" + cont + ".spawn.yaw");
            float pitch = (float) config.getDouble("teams.t" + cont + ".spawn.pitch");

            Location location = new Location(player.getWorld(), x, y, z, yaw, pitch);
            player.teleport(location);
            player.setGameMode(GameMode.SURVIVAL);
            player.setHealth(20);
            player.setFoodLevel(20);
            gameManager.getPlayerManager().giveKit(player);

            cont++;
        }
    }

    public List<Team> getTeamsList() {
        return teamsList;
    }

    public void setTeamIsAlive(Team t, boolean alive) {
        for (Team team : teamsList) {
            if (team == t) {
                t.setIsAlive(alive);
            }
        }
    }

    public void addKill(Team t) {
        for (Team team : teamsList) {
            if (team == t) {
                t.addKill();
            }
        }
    }

    public int getKills(Team t) {
        for (Team team : teamsList) {
            if (team == t) {
                return t.getKills();
            }
        }
        return 0;
    }

    public void giveScorB() {
        for (Team team : teamsList) {
            this.giveScoreboard = new GiveScoreboard(gameManager, team);
            this.giveScoreboard.runTaskTimer(plugin, 0, 20);
        }
    }

    public void reset() {
        playersList  = new ArrayList<Player>();
        teamsList = new ArrayList<Team>();
    }
}