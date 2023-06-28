package com.yorkys.handlers;

import com.yorkys.managers.GameManager;
import com.yorkys.util.Msg;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {
    private GameManager gameManager;

    public BlockBreak(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    private void onBlockBreak(BlockBreakEvent event) {
        if (!gameManager.getBlockManager().canBreak(event.getBlock())) {
            if(!event.getPlayer().hasPermission("block.place")) {
                event.setCancelled(true);
                Msg.send(event.getPlayer(), ("&cNon puoi rompere blocchi qui!"));
            }
        }
    }
}
