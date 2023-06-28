package com.yorkys.handlers;

import com.yorkys.managers.GameManager;
import com.yorkys.managers.GameState;
import com.yorkys.managers.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.List;

public class GiveScoreboard extends BukkitRunnable {
    private GameManager gameManager;
    private Team team;
    private Player player;

    public GiveScoreboard(GameManager gameManager, Team team) {
        this.gameManager = gameManager;
        this.team = team;

        List<Team> players = gameManager.getTeamsManager().getTeamsList();
        for (Team t : players) {
            if (t == team) {
                this.player = t.getPlayer();
            }
        }
    }

    @Override
    public void run() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        final Scoreboard board = manager.getNewScoreboard();
        final Objective objective = board.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.AQUA + "SkyWars");
        Score score = objective.getScore("");
        score.setScore(10);
        Score killS = objective.getScore("kills: " + gameManager.getTeamsManager().getKills(team));
        killS.setScore(9);
        player.setScoreboard(board);
    }
}