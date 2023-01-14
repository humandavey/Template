package me.humandavey.template.nametag;

import me.humandavey.template.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NametagManager {

	private static Team createNametag(Player player) {
		return Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(player.getName());
	}

	private static Team getTeam(Player player) {
		Team team = Bukkit.getScoreboardManager().getMainScoreboard().getEntryTeam(player.getName());
		if (team == null) {
			return createNametag(player);
		}
		return team;
	}

	public static void setPrefix(Player player, String prefix) {
		getTeam(player).setPrefix(Util.colorize(prefix));
	}

	public static void setSuffix(Player player, String suffix) {
		getTeam(player).setSuffix(Util.colorize(suffix));
	}

	public static void setNameColor(Player player, ChatColor color) {
		getTeam(player).setColor(color);
	}
}
