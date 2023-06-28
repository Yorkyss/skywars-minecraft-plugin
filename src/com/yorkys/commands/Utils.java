package com.yorkys.commands;

import com.yorkys.util.CommandBase;
import com.yorkys.util.Msg;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.EventListener;
import java.util.Vector;

public class Utils {
    public Utils() {

        // feed
        new CommandBase("feed",true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                player.setFoodLevel(20);
                Msg.send(player, "&aYou have been feed.");
                return true;
            }

            @Override
            public String getUsage() {
                return "/feed";
            }
        }.enableDelay(2).setPermission("feed.feed");

        // heal
        new CommandBase("heal",true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                player.setHealth(20);
                Msg.send(player, "&aYou have been healed.");
                return true;
            }

            @Override
            public String getUsage() {
                return "/heal";
            }
        }.enableDelay(2).setPermission("heal.heal");

        // fly
        new CommandBase("fly",true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                if (player.getAllowFlight()) {
                    Msg.send(player, "&aFly disabled.");
                    player.setAllowFlight(false);
                } else {
                    Msg.send(player, "&aFly enabled.");
                    player.setAllowFlight(true);
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/fly";
            }
        }.enableDelay(2).setPermission("fly.fly");

        // intelligent gamemode
        new CommandBase("gm",true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                if (player.getGameMode() == GameMode.CREATIVE) {
                    Msg.send(player, "&aGamemode set on survival.");
                    player.setGameMode(GameMode.SURVIVAL);
                } else {
                    Msg.send(player, "&aGamemode set on creative.");
                    player.setGameMode(GameMode.CREATIVE);
                }
                return true;
            }

            @Override
            public String getUsage() {
                return "/gm";
            }
        }.enableDelay(2).setPermission("gm.gm");

        // gamemode creative
        new CommandBase("gmc",true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                Msg.send(player, "&aGamemode set on creative.");
                player.setGameMode(GameMode.CREATIVE);

                return true;
            }

            @Override
            public String getUsage() {
                return "/gmc";
            }
        }.enableDelay(2).setPermission("gm.c");

        // gamemode survival
        new CommandBase("gms",true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                Msg.send(player, "&aGamemode set on survival.");
                player.setGameMode(GameMode.SURVIVAL);

                return true;
            }

            @Override
            public String getUsage() {
                return "/gms";
            }
        }.enableDelay(2).setPermission("gm.s");

        // gamemode spectator
        new CommandBase("gmsp",true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                Msg.send(player, "&aGamemode set on spectator.");
                player.setGameMode(GameMode.SPECTATOR);

                return true;
            }

            @Override
            public String getUsage() {
                return "/gmsp";
            }
        }.enableDelay(2).setPermission("gm.sp");

        // gamemode adventure
        new CommandBase("gma",true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                Msg.send(player, "&aGamemode set on adventure.");
                player.setGameMode(GameMode.ADVENTURE);

                return true;
            }

            @Override
            public String getUsage() {
                return "/gma";
            }
        }.enableDelay(2).setPermission("gm.a");

        // teleport
        new CommandBase("tp", 1, 2, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                Player target = player.getServer().getPlayer(arguments[0]);

                if (arguments.length == 2) {
                    Player targetDestination = player.getServer().getPlayer(arguments[1]);

                    if (target.isOnline() && targetDestination.isOnline()) {
                        target.teleport(targetDestination.getLocation());
                        Msg.send(player,  "&a" + target.getName() + " teleported to " + targetDestination.getName());
                        Msg.send(target, "&bTeleported to " + targetDestination.getName());
                    } else {
                        Msg.send(player, "&cPlayer offline.");
                    }
                } else {
                    if (target.isOnline()) {
                        player.teleport(target.getLocation());
                        Msg.send(player, "&aTeleported to " + target.getName());
                    } else {
                        Msg.send(player, "&cPlayer offline.");
                    }
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/tp";
            }
        }.enableDelay(2).setPermission("tp.tp");

        // teleport
        new CommandBase("tphere", 1, true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;

                Player target = player.getServer().getPlayer(arguments[0]);

                if (target.isOnline()) {
                    target.teleport(player.getLocation());
                    Msg.send(player, "&a" + target.getName() + " teleported to you.");
                    Msg.send(target, "&bTeleported to " + player.getName());
                } else {
                    Msg.send(player, "&cPlayer offline.");
                }

                return true;
            }

            @Override
            public String getUsage() {
                return "/tphere";
            }
        }.enableDelay(2).setPermission("tp.tphere");
    }
}
