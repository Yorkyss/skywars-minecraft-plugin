package com.yorkys.managers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerManager {
    private GameManager gameManager;

    public PlayerManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void giveKits() {
        Bukkit.getOnlinePlayers().stream().filter(player -> player.getGameMode() == GameMode.SURVIVAL).forEach(this::giveKit);
    }

    public void giveKit(Player player) {
        FileConfiguration config = gameManager.getPlugin().getConfig();

        int itemsNum = config.getInt("kits.default.items");

        for (int i = 1; i <= itemsNum; i++) {
            String name = config.getString("kits.default." + i + ".name");
            int amount = config.getInt("kits.default." + i + ".amount");
            player.getInventory().addItem(new ItemStack(Material.getMaterial(name), amount));
        }
    }

    public void clearAllInv() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Inventory inv = player.getInventory();
            inv.clear();
        }
    }
}