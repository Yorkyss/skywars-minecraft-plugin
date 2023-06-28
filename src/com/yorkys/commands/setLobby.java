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

public class setLobby {
    private Location lobby = null;

    public setLobby(mainSkywars plugin) {
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

        float x = (float) config.getDouble("lobby.x");
        float y = (float) config.getDouble("lobby.y");
        float z = (float) config.getDouble("lobby.z");
        float yaw = (float) config.getDouble("lobby.yaw");
        float pitch = (float) config.getDouble("lobby.pitch");
        lobby = new Location(world, x, y, z, yaw, pitch);

        new CommandBase("setlobby",true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                Location location = player.getLocation();

                config.set("lobby.world", location.getWorld().getName());
                config.set("lobby.x", location.getX());
                config.set("lobby.y", location.getY());
                config.set("lobby.z", location.getZ());
                config.set("lobby.yaw", location.getYaw());
                config.set("lobby.pitch", location.getPitch());

                plugin.saveConfig();

                lobby = location;

                Msg.send(player, "&aLobby impostata.");
                return true;
            }

            @Override
            public String getUsage() {
                return "/setlobby";
            }
        }.enableDelay(2).setPermission("lobby.set");
    }
}