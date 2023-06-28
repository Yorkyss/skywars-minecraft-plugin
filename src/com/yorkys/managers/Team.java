package com.yorkys.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class Team {
    private GameManager gameManager;
    private Player player;
    private int kills;
    private boolean isAlive;


    public Team(Player p, GameManager gm) {
        this.gameManager = gm;
        this.player = p;
        isAlive = true;
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(gameManager.getPlugin(), new Runnable() {
            public void run() {
                ScoreboardManager manager = Bukkit.getScoreboardManager();
                final Scoreboard board = manager.getNewScoreboard();
                final Objective objective = board.registerNewObjective("test", "dummy");
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                objective.setDisplayName(ChatColor.AQUA + "SkyWars");
                Score score = objective.getScore("");
                score.setScore(10);
                Score killS = objective.getScore("kills: " + kills);
                killS.setScore(9);
                p.setScoreboard(board);
            }
        },0, 20 * 10);
    }

    public Player getPlayer() {
        return player;
    }

    public void setIsAlive(boolean alive) {
        isAlive = alive;
    }

    public void addKill() {
        kills++;
    }

    public int getKills() {
        return kills;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
