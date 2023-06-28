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

public class setSpawns {
    private Location t1 = null;

    private Location t2 = null;
    private Location t3 = null;
    private Location t4 = null;
    private Location t5 = null;
    private Location t6 = null;
    private Location t7 = null;
    private Location t8 = null;

    public setSpawns(mainSkywars plugin) {
        FileConfiguration config = plugin.getConfig();

        String worldName = config.getString("teams.world");
        if (worldName == null) {
            Bukkit.getLogger().warning("teams.world does not exist within config.yml");
            return;
        }

        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            Bukkit.getLogger().severe("World \"" + worldName + "\" does not exist");
            return;
        }

        float t1x = (float) config.getDouble("teams.t1.spawn.x");
        float t1y = (float) config.getDouble("teams.t1.spawn.y");
        float t1z = (float) config.getDouble("teams.t1.spawn.z");
        float t1yaw = (float) config.getDouble("teams.t1.spawn.yaw");
        float t1pitch = (float) config.getDouble("teams.t1.spawn.pitch");
        t1 = new Location(world, t1x, t1y, t1z, t1yaw, t1pitch);

        float t2x = (float) config.getDouble("teams.t2.spawn.x");
        float t2y = (float) config.getDouble("teams.t2.spawn.y");
        float t2z = (float) config.getDouble("teams.t2.spawn.z");
        float t2yaw = (float) config.getDouble("teams.t2.spawn.yaw");
        float t2pitch = (float) config.getDouble("teams.t2.spawn.pitch");
        t2 = new Location(world, t2x, t2y, t2z, t2yaw, t2pitch);

        float t3x = (float) config.getDouble("teams.t3.spawn.x");
        float t3y = (float) config.getDouble("teams.t3.spawn.y");
        float t3z = (float) config.getDouble("teams.t3.spawn.z");
        float t3yaw = (float) config.getDouble("teams.t3.spawn.yaw");
        float t3pitch = (float) config.getDouble("teams.t3.spawn.pitch");
        t3 = new Location(world, t3x, t3y, t3z, t3yaw, t3pitch);

        float t4x = (float) config.getDouble("teams.t4.spawn.x");
        float t4y = (float) config.getDouble("teams.t4.spawn.y");
        float t4z = (float) config.getDouble("teams.t4.spawn.z");
        float t4yaw = (float) config.getDouble("teams.t4.spawn.yaw");
        float t4pitch = (float) config.getDouble("teams.t4.spawn.pitch");
        t4 = new Location(world, t4x, t4y, t4z, t4yaw, t4pitch);

        float t5x = (float) config.getDouble("teams.t5.spawn.x");
        float t5y = (float) config.getDouble("teams.t5.spawn.y");
        float t5z = (float) config.getDouble("teams.t5.spawn.z");
        float t5yaw = (float) config.getDouble("teams.t5.spawn.yaw");
        float t5pitch = (float) config.getDouble("teams.t5.spawn.pitch");
        t5 = new Location(world, t5x, t5y, t5z, t5yaw, t5pitch);

        float t6x = (float) config.getDouble("teams.t6.spawn.x");
        float t6y = (float) config.getDouble("teams.t6.spawn.y");
        float t6z = (float) config.getDouble("teams.t6.spawn.z");
        float t6yaw = (float) config.getDouble("teams.t6.spawn.yaw");
        float t6pitch = (float) config.getDouble("teams.t6.spawn.pitch");
        t6 = new Location(world, t6x, t6y, t6z, t6yaw, t6pitch);

        float t7x = (float) config.getDouble("teams.t7.spawn.x");
        float t7y = (float) config.getDouble("teams.t7.spawn.y");
        float t7z = (float) config.getDouble("teams.t7.spawn.z");
        float t7yaw = (float) config.getDouble("teams.t7.spawn.yaw");
        float t7pitch = (float) config.getDouble("teams.t7.spawn.pitch");
        t7 = new Location(world, t7x, t7y, t7z, t7yaw, t7pitch);

        float t8x = (float) config.getDouble("teams.t8.spawn.x");
        float t8y = (float) config.getDouble("teams.t8.spawn.y");
        float t8z = (float) config.getDouble("teams.t8.spawn.z");
        float t8yaw = (float) config.getDouble("teams.t8.spawn.yaw");
        float t8pitch = (float) config.getDouble("teams.t8.spawn.pitch");
        t8 = new Location(world, t8x, t8y, t8z, t8yaw, t8pitch);

        new CommandBase("setspawn", 1,true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                Location location = player.getLocation();

                switch(arguments[0]) {
                    case "t1":
                        break;
                    case "t2":
                        break;
                    case "t3":
                        break;
                    case "t4":
                        break;
                    case "t5":
                        break;
                    case "t6":
                        break;
                    case "t7":
                        break;
                    case "t8":
                        break;
                    case "see":
                        Msg.send(player, "&ateam 1: " + t1);
                        Msg.send(player, "&ateam 2: " + t2);
                        Msg.send(player, "&ateam 3: " + t3);
                        Msg.send(player, "&ateam 4: " + t4);
                        Msg.send(player, "&ateam 5: " + t5);
                        Msg.send(player, "&ateam 6: " + t6);
                        Msg.send(player, "&ateam 7: " + t7);
                        Msg.send(player, "&ateam 8: " + t8);
                        return true;
                    default:
                        Msg.send(player, "&cPuoi impostare gli spawn solamente per i team da t1 a t8!");
                        return true;
                }

                config.set("teams.world", location.getWorld().getName());
                config.set("teams." + arguments[0] + ".spawn.x", location.getX());
                config.set("teams." + arguments[0] + ".spawn.y", location.getY());
                config.set("teams." + arguments[0] + ".spawn.z", location.getZ());
                config.set("teams." + arguments[0] + ".spawn.yaw", location.getYaw());
                config.set("teams." + arguments[0] + ".spawn.pitch", location.getPitch());

                plugin.saveConfig();

                switch(arguments[0]) {
                    case "t1":
                        t1 = location;
                        break;
                    case "t2":
                        t2 = location;
                        break;
                    case "t3":
                        t3 = location;
                        break;
                    case "t4":
                        t4 = location;
                        break;
                    case "t5":
                        t5 = location;
                        break;
                    case "t6":
                        t6 = location;
                        break;
                    case "t7":
                        t7 = location;
                        break;
                    case "t8":
                        t8 = location;
                        break;
                }

                Msg.send(player, "&aSpawnpoint per il team " + arguments[0] + " impostato.");
                return true;
            }

            @Override
            public String getUsage() {
                return "/setspawn t1/t2/t3.. || /setspawn see ";
            }
        }.enableDelay(2).setPermission("spawn.set");
    }
}