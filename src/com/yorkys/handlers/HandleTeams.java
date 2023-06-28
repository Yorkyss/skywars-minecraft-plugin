package com.yorkys.handlers;

import com.yorkys.mainSkywars;
import com.yorkys.managers.GameManager;
import com.yorkys.managers.GameState;
import com.yorkys.managers.Team;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HandleTeams implements Listener {
    private GameManager gameManager;
    private mainSkywars plugin;

    private final Map<UUID, UUID> lastDamagers = new HashMap<>();

    public HandleTeams(GameManager gameManager, mainSkywars plugin) {
        this.gameManager = gameManager;
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        p.spigot().respawn();
        e.setDeathMessage("");

        if (p.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent dmgEvent = (EntityDamageByEntityEvent) p.getLastDamageCause();
            if (dmgEvent.getDamager() instanceof Player) {
                Player killer = (Player) dmgEvent.getDamager();
                if (gameManager.getGameState() == GameState.ACTIVE
                        || gameManager.getGameState() == GameState.PREDEATHMATCH
                        || gameManager.getGameState() == GameState.DEATHMATCH) {
                    if (killer != null) {
                        e.setDeathMessage(ChatColor.RED + p.getName() + ChatColor.AQUA + " è stato ucciso da " + ChatColor.GREEN + killer.getName());
                    } else {
                        e.setDeathMessage(ChatColor.RED + p.getName() + ChatColor.AQUA + " è morto");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (gameManager.getGameState() != GameState.ACTIVE && gameManager.getGameState() != GameState.PREDEATHMATCH  && gameManager.getGameState() != GameState.DEATHMATCH) return;
        Player player = event.getPlayer();

        FileConfiguration config = plugin.getConfig();

        List<Team> players = gameManager.getTeamsManager().getTeamsList();

        int cont = 1;
        for (Team t : players) {
            Player p = t.getPlayer();
            if (p == player) {
                float x = (float) config.getDouble("teams.t" + cont + ".spawn.x");
                float y = (float) config.getInt("teams.t" + cont + ".spawn.y");
                float z = (float) config.getInt("teams.t" + cont + ".spawn.z");
                float yaw = (float) config.getDouble("teams.t" + cont + ".spawn.yaw");
                float pitch = (float) config.getDouble("teams.t" + cont + ".spawn.pitch");
                Location location = new Location(player.getWorld(), x, y, z, yaw, pitch);
                event.setRespawnLocation(location);

                gameManager.getTeamsManager().setTeamIsAlive(t, false);
            }
            cont++;
        }
    }

    @EventHandler
    public void onSpectRespawn(PlayerRespawnEvent event) {
        if (gameManager.getGameState() != GameState.ACTIVE && gameManager.getGameState() != GameState.PREDEATHMATCH  && gameManager.getGameState() != GameState.DEATHMATCH) return;
        Player player = event.getPlayer();
        player.setGameMode(GameMode.SPECTATOR);
    }

    @EventHandler
    public void onGetKilled(PlayerDeathEvent e) {
        if (gameManager.getGameState() != GameState.ACTIVE && gameManager.getGameState() != GameState.PREDEATHMATCH  && gameManager.getGameState() != GameState.DEATHMATCH) return;
        Player p = e.getEntity();
        p.spigot().respawn();

        if (p.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent dmgEvent = (EntityDamageByEntityEvent) p.getLastDamageCause();
            if (dmgEvent.getDamager() instanceof Player) {
                Player killer = (Player) dmgEvent.getDamager();
                if (gameManager.getGameState() == GameState.ACTIVE
                        || gameManager.getGameState() == GameState.PREDEATHMATCH
                        || gameManager.getGameState() == GameState.DEATHMATCH) {
                    if (killer != null) {
                        if (p.getName().equals(killer.getName())) return;

                        List<Team> players = gameManager.getTeamsManager().getTeamsList();

                        for (Team t : players) {
                            Player player = t.getPlayer();
                            if (player == killer) {
                                gameManager.getTeamsManager().addKill(t);
                            }
                        }
                    }
                }
            }
        }

    }

    @EventHandler
    public void onDamageTaken(EntityDamageEvent e) {
        Player p = (Player) e.getEntity();
        if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
            p.setHealth(0);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (gameManager.getGameState() != GameState.ACTIVE && gameManager.getGameState() != GameState.PREDEATHMATCH  && gameManager.getGameState() != GameState.DEATHMATCH) return;
        Player player = event.getPlayer();

        event.setJoinMessage(ChatColor.GREEN + player.getName() + ChatColor.BLUE + " è entrato come spettatore");
        player.setGameMode(GameMode.SPECTATOR);

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (gameManager.getGameState() != GameState.ACTIVE && gameManager.getGameState() != GameState.PREDEATHMATCH  && gameManager.getGameState() != GameState.DEATHMATCH) return;
        Player player = event.getPlayer();

        event.setQuitMessage(ChatColor.RED + player.getName() + ChatColor.BLUE + " è uscito dalla partita");
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
}