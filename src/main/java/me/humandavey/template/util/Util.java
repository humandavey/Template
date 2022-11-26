package me.humandavey.template.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	public static List<String> colorizeList(List<String> list) {
		List<String> strings = new ArrayList<>();
		for (String s : list) {
			strings.add(colorize(s));
		}
		return strings;
	}

	public static String colorize(String m) {
		return ChatColor.translateAlternateColorCodes('&', translateHexColors(m));
	}

	public static String translateHexColors(String message) {
		char COLOR_CHAR = ChatColor.COLOR_CHAR;
		final Pattern hexPattern = Pattern.compile("&#([A-Fa-f0-9]{6})");
		Matcher matcher = hexPattern.matcher(message);
		StringBuilder buffer = new StringBuilder(message.length() + 4 * 8);
		while (matcher.find()) {
			String group = matcher.group(1);
			matcher.appendReplacement(buffer, COLOR_CHAR + "x"
					+ COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
					+ COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
					+ COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
			);
		}
		return matcher.appendTail(buffer).toString();
	}

	public static Location configToLocation(FileConfiguration fc, String path) {
		if (fc.get(path) != null) {
			if (fc.get(path + ".world") != null && fc.get(path + ".x") != null && fc.get(path + ".y") != null && fc.get(path + ".z") != null && fc.get(path + ".yaw") != null && fc.get(path + ".pitch") != null) {
				return new Location(Bukkit.getWorld(Objects.requireNonNull(fc.getString(path + ".world"))), fc.getDouble(path + ".x"), fc.getDouble(path + ".y"), fc.getDouble(path + ".z"), fc.getInt(path + ".yaw"), fc.getInt(path + ".pitch"));
			} else if (fc.get(path + ".world") != null && fc.get(path + ".x") != null && fc.get(path + ".y") != null && fc.get(path + ".z") != null) {
				return new Location(Bukkit.getWorld(Objects.requireNonNull(fc.getString(path + ".world"))), fc.getDouble(path + ".x"), fc.getDouble(path + ".y"), fc.getDouble(path + ".z"));
			}
		}
		return null;
	}

	public static Location stringToLocation(String location, String split) {
		String[] parts = location.split(split);
		if (parts.length == 3) {
			return new Location(Bukkit.getWorlds().get(0), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
		} else if (parts.length == 4) {
			return new Location(Bukkit.getWorld(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
		} else if (parts.length == 6) {
			return new Location(Bukkit.getWorld(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
		}
		return null;
	}
}