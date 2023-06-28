package com.yorkys.activity;

import com.yorkys.managers.GameManager;
import com.yorkys.managers.GameState;
import com.yorkys.managers.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class CheckWin extends BukkitRunnable {
    private GameManager gameManager;

    public CheckWin(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void run() {
        int alivesPlayers = 0;
        Team lastAlive = null;

        for (Team team : gameManager.getTeamsManager().getTeamsList()) {
            if (team.isAlive()) {
                alivesPlayers++;
                lastAlive = team;
            }
        }

        if (alivesPlayers <= 1) {
            cancel();
            if (lastAlive != null) {
                Bukkit.broadcastMessage(ChatColor.AQUA + lastAlive.getPlayer().getName() + ChatColor.GOLD + " ha vinto con " + ChatColor.RED + lastAlive.getKills() + ChatColor.GOLD + " uccisioni!");
            }
            gameManager.setGameState(GameState.WON);
            return;
        }
    }
}