package dev.leho.tagplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class EventListener implements Listener {

	public String currentlyTagged = ""; 
	
	// implement later for logging old tnts
//	public Player oldTagger;

	private final TagPlugin tp;
	
	public EventListener(TagPlugin tp) {
		this.tp = tp;
	}
		
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if (tp.isTagging()) {
			if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
	             Player whoWasHit = (Player) e.getEntity();
	             Player whoHit = (Player) e.getDamager();
	             
	             whoWasHit.setHealth(20.0);
	             whoWasHit.setSaturation(20);
	             
	             whoHit.setHealth(20.0);
	             whoHit.setSaturation(20);
	             
	             
	             Bukkit.getServer().broadcastMessage(ChatColor.RED + whoWasHit.getDisplayName().toString() + ChatColor.RESET + " Was Tagged By " + ChatColor.GREEN + whoHit.getDisplayName().toString() + ChatColor.RESET);
	             whoWasHit.getInventory().setHelmet(new ItemStack(Material.TNT));
	             whoHit.getInventory().setHelmet(new ItemStack(Material.AIR));
	             
	             whoWasHit.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2555, 2));
	             whoHit.removePotionEffect(PotionEffectType.SPEED);
//	             whoHit.removePotionEffect(new PotionEffect(PotionEffectType.SPEED, 255, 2));
	             

	             
	             
			}
		} else {
			Bukkit.getServer().broadcastMessage("The game is not running; to start, use /starttag");
		}
	}
	

}
