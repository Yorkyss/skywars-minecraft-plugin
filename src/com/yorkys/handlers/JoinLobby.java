package com.yorkys.handlers;

import com.yorkys.managers.GameManager;
import com.yorkys.managers.GameState;
import com.yorkys.util.Msg;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class JoinLobby implements Listener {

    private GameManager gameManager;

    public JoinLobby(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void w(PlayerJoinEvent event) {
        System.out.println(gameManager.getTeamsManager().getTeamsList());
    }
    @EventHandler
    public void a(PlayerQuitEvent event) {
        System.out.println(gameManager.getTeamsManager().getTeamsList());
    }

    @EventHandler
    public void tpToLobby(PlayerJoinEvent event) {
        if (gameManager.getGameState() != GameState.LOBBY && gameManager.getGameState() != GameState.STARTING) return;
        Player player = event.getPlayer();

        gameManager.getPlugin().getMap().load();

        Location lobbyXYZ = gameManager.getLobbyLoc();
        player.teleport(lobbyXYZ);
        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(20);
        player.setFoodLevel(20);
        lobbyXYZ.getWorld().setGameRuleValue("doDaylightCycle", "false");
        lobbyXYZ.getWorld().setGameRuleValue("doMobSpawning", "false");
        lobbyXYZ.getWorld().setGameRuleValue("doWeatherCycle", "false");
        lobbyXYZ.getWorld().setStorm(false);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (gameManager.getGameState() != GameState.LOBBY && gameManager.getGameState() != GameState.STARTING) return;
        Player player = event.getPlayer();

        event.setJoinMessage(ChatColor.DARK_AQUA + "+ " + ChatColor.BLUE + player.getName());

    }

    @EventHandler
    public void clearInvOnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (gameManager.getGameState() != GameState.LOBBY && gameManager.getGameState() != GameState.STARTING) return;
        Player player = event.getPlayer();

        event.setQuitMessage(ChatColor.RED + "- " + ChatColor.BLUE + player.getName());
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (gameManager.getGameState() != GameState.LOBBY && gameManager.getGameState() != GameState.STARTING) return;
        Material type = event.getBlock().getType();
        Player player = event.getPlayer();

        if(!player.hasPermission("block.place")) {
            event.setCancelled(true);
            Msg.send(player, ("&cNon puoi piazzare blocchi qui!"));
        }
    }

    @EventHandler
    public void onBlockPlace(BlockBreakEvent event) {
        if (gameManager.getGameState() != GameState.LOBBY && gameManager.getGameState() != GameState.STARTING) return;
        Material type = event.getBlock().getType();
        Player player = event.getPlayer();

        if(!player.hasPermission("block.place")) {
            event.setCancelled(true);
            Msg.send(player, ("&cNon puoi rompere blocchi qui!"));
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (gameManager.getGameState() != GameState.LOBBY && gameManager.getGameState() != GameState.STARTING) return;
        Player player = event.getPlayer();

        Location lobbyXYZ = gameManager.getLobbyLoc();
        event.setRespawnLocation(lobbyXYZ);
    }

    @EventHandler
    public void disablePvP(EntityDamageEvent e) {
        if (gameManager.getGameState() != GameState.LOBBY && gameManager.getGameState() != GameState.STARTING) return;
        Player p = (Player) e.getEntity();
        e.setCancelled(true);
    }
}