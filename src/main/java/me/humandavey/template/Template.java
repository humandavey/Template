package me.humandavey.template;

import me.humandavey.template.command.commands.ExampleCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Template extends JavaPlugin {

	private static Template instance;

	@Override
	public void onEnable() {
		instance = this;

		setupConfigs();
		setupManagers();
		registerListeners();
		registerCommands();
	}

	@Override
	public void onDisable() {

	}

	private void setupConfigs() {
		getConfig().options().copyDefaults();
		saveDefaultConfig();
	}

	private void setupManagers() {

	}

	private void registerListeners() {

	}

	private void registerCommands() {
		new ExampleCommand();
	}

	public static Template getInstance() {
		return instance;
	}
}
