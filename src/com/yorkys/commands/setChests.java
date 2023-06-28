package com.yorkys.commands;

import com.yorkys.util.CommandBase;
import com.yorkys.util.Msg;
import com.yorkys.mainSkywars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class setChests {

    public setChests(mainSkywars plugin) {
        FileConfiguration config = plugin.getConfig();

        new CommandBase("setchest", 2,true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                Location location = player.getLocation();

                switch(arguments[0]) {
                    case "team1":
                        break;
                    case "team2":
                        break;
                    case "team3":
                        break;
                    case "team4":
                        break;
                    case "team5":
                        break;
                    case "team6":
                        break;
                    case "team7":
                        break;
                    case "team8":
                        break;
                    case "team9":
                        break;
                    default:
                        Msg.send(player, "&cPuoi impostare solo le chest dal team1 al team9 (isola centrale)!");
                        return true;
                }

                int chestNum = config.getInt("chests.chestXteam");

                config.set("chests." + arguments[0] + "." + arguments[1] + ".x", location.getX());
                config.set("chests." + arguments[0] + "." + arguments[1] + ".y", location.getY());
                config.set("chests." + arguments[0] + "." + arguments[1] + ".z", location.getZ());

                plugin.saveConfig();

                Msg.send(player, "&aChest " + arguments[1] + " impostata per il team " + arguments[0]);
                return true;
            }

            @Override
            public String getUsage() {
                return "/setchest team1/2/3.. 1/2/3..";
            }
        }.enableDelay(2).setPermission("chest.set");
    }
}