package com.yorkys.commands;

import com.yorkys.managers.GameManager;
import com.yorkys.managers.GameState;
import com.yorkys.util.CommandBase;
import com.yorkys.util.Msg;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand {
    private GameManager gameManager;

    public StartCommand(GameManager gameManager) {
        this.gameManager = gameManager;
        new CommandBase("start", true) {
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                Player player = (Player) sender;
                gameManager.setGameState(GameState.STARTING);

                return true;
            }

            @Override
            public String getUsage() {
                return "/start";
            }
        }.enableDelay(2).setPermission("start.start");
    }
}
