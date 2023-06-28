package com.yorkys.activity;

import com.yorkys.managers.GameManager;
import com.yorkys.managers.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RestartAfterSeconds extends BukkitRunnable {
    private GameManager gameManager;

    public RestartAfterSeconds(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    private int timeLeft = 6;


    @Override
    public void run() {
        timeLeft--;
        if (timeLeft <= 0) {
            cancel();
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.kickPlayer("Server in riavvio");
            }
            Bukkit.spigot().restart();
            return;
        }

        Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.RED + "SERVER IN RIAVVIO TRA " + ChatColor.DARK_RED + timeLeft + ChatColor.RED + " SECONDI!");
    }
}