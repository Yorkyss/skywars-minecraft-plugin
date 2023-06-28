package com.yorkys.handlers;

import com.yorkys.managers.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChestGenerator {
    private GameManager gameManager;
    private List<ChestItem> items = new ArrayList<>();

    public ChestGenerator(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void spawnChests() {
        FileConfiguration config = gameManager.getPlugin().getConfig();

        int itemNum = config.getInt("chests.itemsXChest");
        for (int i = 1; i <= itemNum; i++) {
            String name = config.getString("chests.items." + i + ".name");
            int amount = config.getInt("chests.items." + i + ".amount");
            ChestItem item = new ChestItem(name, amount);
            items.add(item);
        }

        int chestNum = config.getInt("chests.chestXteam");

        for (int k = 1; k <= 9; k++) {
            for (int i = 1; i <= chestNum; i++) {
                int x = config.getInt("chests.team" + k + "." + i + ".x");
                int y = config.getInt("chests.team" + k + "." + i + ".y");
                int z = config.getInt("chests.team" + k + "." + i + ".z");

                Location chestLoc = new Location(Bukkit.getWorld("tempMap"), x, y, z);
                chestLoc.getBlock().setType(Material.CHEST);
                Chest chest = (Chest) chestLoc.getBlock().getState();
                Inventory inv = chest.getInventory();

                for (int j = 0; j < 4; j++) {
                    Random rand = new Random();
                    ChestItem chestItem = items.get(rand.nextInt(items.size()));
                    inv.addItem(new ItemStack(Material.getMaterial(chestItem.getName()), chestItem.getAmount()));
                }
            }
        }
    }
}