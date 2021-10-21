package dev.leho.tagplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TagPlugin extends JavaPlugin {
	
	private boolean tagging = false;
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		boolean result = true;
		
		if (sender instanceof Player) {
	
			if (cmd.getName().equals("starttag")) {
				tagging = true;
	            Bukkit.getServer().broadcastMessage("Started the tag game!");
				}
			} else if (cmd.getName().equals("stoptag")) {
				tagging = false;
	            Bukkit.getServer().broadcastMessage("Stopped the tag game!");
			}
		
		
		else {
			System.out.println("This command must be run from a player");
			result = false;
		}
			
		return result;
	}	
	
	public boolean isTagging() {
		return tagging;
	}
	
	@Override
	public void onEnable() {
	    Bukkit.getServer().getPluginManager().registerEvents(new EventListener(this), this);
		System.out.println("Tag Plugin Started!");
	}
	
	@Override
	public void onDisable() {
		System.out.println("Tag Plugin Stopped!");
	}
		

}
