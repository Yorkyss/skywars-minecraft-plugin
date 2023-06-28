package com.yorkys.commands;

import com.yorkys.managers.GameManager;
import com.yorkys.map.gameMap;
import com.yorkys.util.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reset {
    private GameManager gameManager;

    public Reset(GameManager gameManager, gameMap map) {

        new CommandBase("reset",true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.kickPlayer("Server in riavvio");
                }
                Bukkit.spigot().restart();
                return true;
            }

            @Override
            public String getUsage() {
                return "/reset";
            }
        }.enableDelay(2).setPermission("reset.reset");
    }
}
