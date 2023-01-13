package me.humandavey.template.command.commands;

import me.humandavey.template.command.Command;
import me.humandavey.template.menu.menus.PagedMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ExampleCommand extends Command {

	public ExampleCommand() {
		super("example", new String[]{"e", "ex"}, "Shows the usage of an example command!");
	}

	@Override
	public void execute(Player player, String[] args) {
		player.sendMessage("You ran the example command and opened an example paged menu!");

		ArrayList<ItemStack> items = new ArrayList<>();
		for (int i = 0; i < 90; i++) {
			items.add(new ItemStack(Material.DIAMOND_SWORD));
		}

		PagedMenu menu = new PagedMenu("Paged Menu Test", 3, items, 1);
		menu.setOnClick(event -> {
			event.getWhoClicked().sendMessage("You clicked on " + event.getCurrentItem().getType().name());
		});
		menu.open(player);
	}
}
