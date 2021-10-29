// Made By Leho | leho.dev | github.com/lehoooo
package dev.leho.tagplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TagPlugin extends JavaPlugin {
	
	// setup variables and load config
	private boolean tagging = false;
	public int hitsToWin = this.getConfig().getInt("HitsToWin");
		
	// command handler
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		boolean result = true;
		
		if (sender instanceof Player) {
	
			if (cmd.getName().equals("starttag")) {
				tagging = true;
	            Bukkit.getServer().broadcastMessage("Tag Has Started! First To " + hitsToWin + " Hits Wins!");
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
	
	public void setTagging(boolean settag) {
		tagging = settag;
	}
	
	// register events and load default config file - will make if it doesnt exist
	@Override
	public void onEnable() {
	    Bukkit.getServer().getPluginManager().registerEvents(new EventListener(this), this);
	    System.out.println("Registered Events! - Loading Config");
	    
	    this.getConfig().options().copyDefaults();
	    saveDefaultConfig();
	    System.out.println("Config Has Been Loaded!");
	    
		System.out.println("Tag Plugin Started!");

	}
	
	@Override
	public void onDisable() {
		System.out.println("Tag Plugin Stopped!");
	}
		

}
