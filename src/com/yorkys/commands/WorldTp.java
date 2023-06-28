package com.yorkys.commands;

import com.yorkys.mainSkywars;
import com.yorkys.util.CommandBase;
import com.yorkys.util.Msg;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldTp {

    public WorldTp(mainSkywars plugin) {


        new CommandBase("world", 1, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                if (arguments[0].equals("list")) {
                    for (World world : Bukkit.getWorlds()) {
                        Msg.send(player, "World: " + world.getName());
                    }
                } else {
                    Location location = new Location(Bukkit.getWorld(arguments[0]), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
                    player.teleport(location);
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/world name";
            }
        }.enableDelay(2).setPermission("world.tp");
    }
}
