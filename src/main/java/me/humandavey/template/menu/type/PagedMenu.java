package me.humandavey.template.menu.type;

import me.humandavey.template.Template;
import me.humandavey.template.menu.Menu;
import me.humandavey.template.util.Util;
import me.humandavey.template.util.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PagedMenu extends Menu implements Listener {

	private List<ItemStack> items;
	private int page;

	public PagedMenu(String name, int rows, List<ItemStack> items, int page) {
		super(name + " - " + page, rows);
		this.page = page;
		this.items = items;

		ItemStack right = new ItemBuilder(Material.BARRIER).setItemName(Util.colorize("&cNext")).build();
		ItemStack left = new ItemBuilder(Material.BARRIER).setItemName(Util.colorize("&cBack")).build();

		if (isPageValid(items, page - 1, (rows * 9) - 2)) {
			left = new ItemBuilder(Material.ARROW).setItemName(Util.colorize("&eBack")).build();
		}
		if (isPageValid(items, page + 1, (rows * 9) - 2)) {
			right = new ItemBuilder(Material.ARROW).setItemName(Util.colorize("&eNext")).build();
		}

		setItemAt((rows * 9) - 1, right);
		setItemAt((rows * 9) - 9, left);

		for (ItemStack item : getPageItems(items, page, (rows * 9) - 2)) {
			addItem(item);
		}

		Template.getInstance().getServer().getPluginManager().registerEvents(this, Template.getInstance());
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getInventory().equals(getInventory())) {
			if (event.getCurrentItem() == null) return;
			if (event.getCurrentItem().getItemMeta() == null) return;
			if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
			if (event.getCurrentItem().getType() == null) return;
			if (event.getCurrentItem().getType() == Material.ARROW && event.getCurrentItem().getItemMeta().getDisplayName().contains("Back")) {
				new PagedMenu(getName().substring(0, getName().length() - 3 - String.valueOf(page).length()), getRows(), items, page - 1).open((Player) event.getWhoClicked());
				event.setCancelled(true);
			} else if (event.getCurrentItem().getType() == Material.ARROW && event.getCurrentItem().getItemMeta().getDisplayName().contains("Next")) {
				new PagedMenu(getName().substring(0, getName().length() - 3 - String.valueOf(page).length()), getRows(), items, page + 1).open((Player) event.getWhoClicked());
				event.setCancelled(true);
			}
			if (event.getCurrentItem().getType() == Material.BARRIER && (event.getCurrentItem().getItemMeta().getDisplayName().contains("Back")) || event.getCurrentItem().getItemMeta().getDisplayName().contains("Next")) {
				event.setCancelled(true);
			}
		}
	}

	public static ArrayList<ItemStack> getPageItems(List<ItemStack> items, int page, int spaces) {
		int upperBound = page * spaces;
		int lowerBound = upperBound - spaces;

		ArrayList<ItemStack> newItems = new ArrayList<>();
		for (int i = lowerBound; i < upperBound; i++) {
			try {
				newItems.add(items.get(i));
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

		return newItems;
	}

	public static boolean isPageValid(List<ItemStack> items, int page, int spaces) {
		if (page <= 0) return false;

		int upperBound = page * spaces;
		int lowerBound = upperBound - spaces;

		return items.size() > lowerBound;
	}
}
