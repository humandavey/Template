package me.humandavey.template.command.commands;

import me.humandavey.template.command.Command;
import org.bukkit.entity.Player;

public class ExampleCommand extends Command {

	public ExampleCommand() {
		super("example", new String[]{"e", "ex"}, "Shows the usage of an example command!");
	}

	@Override
	public void execute(Player player, String[] args) {
		player.sendMessage("You ran the example command!");
	}
}
