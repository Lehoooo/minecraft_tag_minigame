// Made By Leho | leho.dev | github.com/lehoooo
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
	private final TagPlugin tp;
	public int hitsCounter = 0;
	public String winner;
	
		
	public EventListener(TagPlugin tp) {
		this.tp = tp;
	}
	
	// main stuff - listens for the entity damage event
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if (tp.isTagging()) {
			if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
	             Player whoWasHit = (Player) e.getEntity();
	             Player whoHit = (Player) e.getDamager();
	             winner = whoHit.getDisplayName();
	             
	             
	             // setting health and hunger to max so the players dont die
	             whoWasHit.setHealth(20.0);
	             whoWasHit.setSaturation(20);
	             // hunger
	             whoHit.setHealth(20.0);
	             whoHit.setSaturation(20);
	             
	             // tagged message
	             Bukkit.getServer().broadcastMessage(ChatColor.RED + whoWasHit.getDisplayName().toString() + ChatColor.RESET + " Was Tagged By " + ChatColor.GREEN + whoHit.getDisplayName().toString() + ChatColor.RESET);
	             
	             // put tnt on head
	             whoWasHit.getInventory().setHelmet(new ItemStack(Material.TNT));
	             whoHit.getInventory().setHelmet(new ItemStack(Material.AIR));
	             
	             // add speed to tagged player
	             whoWasHit.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2555, 2));
	             whoHit.removePotionEffect(PotionEffectType.SPEED);
	             
	             // add a you have been tagged title to the tagged player, and reset the title of the tagger - depricated but still works on 1.8
	             whoWasHit.sendTitle(ChatColor.RED + "You Have Been Tagged!" + ChatColor.RESET, "");
	             whoHit.resetTitle();
	             hitsCounter++;
	             
	             

	             
	             
			}
		} else {
			Bukkit.getServer().broadcastMessage(ChatColor.RED + "The game is not running; to start, use /starttag" + ChatColor.RESET);
		}
		
		// Game finished logic
		if (hitsCounter == tp.hitsToWin) {
			tp.setTagging(false);
            Bukkit.getServer().broadcastMessage("There has been " + hitsCounter + " Hits! Game Over. " + winner.toString() + " Wins!");
            hitsCounter = 0;

		}
	}
	

}
