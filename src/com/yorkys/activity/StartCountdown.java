package com.yorkys.activity;

import com.yorkys.managers.GameManager;
import com.yorkys.managers.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class StartCountdown extends BukkitRunnable {
    private GameManager gameManager;

    public StartCountdown(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    private int timeLeft = 10;


    @Override
    public void run() {
        timeLeft--;
        if (timeLeft <= 0) {
            cancel();
            gameManager.setGameState(GameState.ACTIVE);
            return;
        }

        Bukkit.broadcastMessage(ChatColor.AQUA + Integer.toString(timeLeft) + ChatColor.GREEN + " secondi all'inizio!");
    }
}