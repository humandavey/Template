package me.humandavey.template.scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {

	public static void setScoreboard(Player player, String title, String... lines) {
		Scoreboard sb = player.getScoreboard();

		for (Objective obj : sb.getObjectives()) {
			obj.unregister();
		}

		Objective obj = sb.registerNewObjective(player.getName(), "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(title);

		for (int i = 0; i < lines.length; i++) {
			Score score = obj.getScore(lines[i]);
			score.setScore(i);
		}
	}
}
