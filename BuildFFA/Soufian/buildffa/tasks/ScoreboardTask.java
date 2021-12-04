package Soufian.buildffa.tasks;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.Task;
import de.theamychan.scoreboard.api.ScoreboardAPI;
import de.theamychan.scoreboard.network.DisplaySlot;
import de.theamychan.scoreboard.network.Scoreboard;
import de.theamychan.scoreboard.network.ScoreboardDisplay;
import Soufian.buildffa.BuildFFA;

public class ScoreboardTask
extends Task {
    public void onRun(int i) {
        for (Player player : Server.getInstance().getOnlinePlayers().values()) {
            Scoreboard scoreboard = ScoreboardAPI.createScoreboard();
            ScoreboardDisplay scoreboardDisplay = scoreboard.addDisplay(DisplaySlot.SIDEBAR, "dumy", " \u00a76\u00a7lBUILDFFA ");
            try {
                BuildFFA.scoreboards.get((Object)player).hideFor(player);
            }
            catch (Exception exception) {
                // empty catch block
            }
            scoreboard.hideFor(player);
            scoreboardDisplay.addLine(" ", 1);
            scoreboardDisplay.addLine("\u00a7aKills         ", 2);
            scoreboardDisplay.addLine("\u00a77 " + BuildFFA.getKills(player.getName()), 3);
            scoreboardDisplay.addLine("  ", 4);
            scoreboardDisplay.addLine("\u00a7cTode", 4);
            scoreboardDisplay.addLine("\u00a77 " + BuildFFA.getDeaths(player.getName()) + " ", 5);
            scoreboardDisplay.addLine("   ", 6);
            scoreboard.showFor(player);
            BuildFFA.scoreboards.put(player, scoreboard);
        }
    }
}

