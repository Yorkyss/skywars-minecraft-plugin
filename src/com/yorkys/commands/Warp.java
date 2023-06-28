package com.yorkys.commands;

import com.yorkys.map.gameMap;
import com.yorkys.util.CommandBase;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warp {
    private gameMap map;

    public Warp(gameMap map) {
        this.map = map;
        new CommandBase("warp",true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                map.load();
                Location loc = new Location(map.getWorld(), 0.5, 130.0, 0.5);
                player.teleport(loc);
                return true;
            }

            @Override
            public String getUsage() {
                return "/warp";
            }
        }.enableDelay(2).setPermission("warp.warp");
    }
}