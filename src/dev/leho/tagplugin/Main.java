package dev.leho.tagplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	public boolean shouldTag = false;
	public String currentlyTagged = ""; 
	
	@Override
	public void onEnable() {
		System.out.println("Tag Plugin Started!");
	}
	
	@Override
	public void onDisable() {
		System.out.println("Tag Plugin Stopped!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equals("starttag")) {
			if (sender instanceof Player) {
				shouldTag = true;
				currentlyTagged = "";
	            Bukkit.getServer().broadcastMessage("Started the tag game!");
			} else {
				System.out.println("This command must be run from a player");
			}
		} else if (cmd.getName().equals("stoptag")) {
			if (sender instanceof Player) {
				shouldTag = false;
				currentlyTagged = "";
	            Bukkit.getServer().broadcastMessage("Stopped the tag game!");
			} else {
				System.out.println("This command must be run from a player");
			}
		}
		
		return false;
	}
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if (shouldTag) {
			if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
	             Player whoWasHit = (Player) e.getEntity();
	             Player whoHit = (Player) e.getDamager();
	             currentlyTagged = whoWasHit.getDisplayName().toString();
	             Bukkit.getServer().broadcastMessage(currentlyTagged + " Is currently tagged!");
	             
			}
		} else {
			Bukkit.getServer().broadcastMessage("The game hasnt started yet! run /starttag");
		}
	}
	
	
	
}
