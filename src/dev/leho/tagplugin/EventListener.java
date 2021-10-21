package dev.leho.tagplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EventListener implements Listener {

	public String currentlyTagged = ""; 

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
	             currentlyTagged = whoWasHit.getDisplayName().toString();
	             Bukkit.getServer().broadcastMessage(currentlyTagged + " Is currently tagged!");
	             System.out.println(currentlyTagged + " Is currently tagged!");
	             
			}
		} else {
			Bukkit.getServer().broadcastMessage("The game is not running; to start, use /starttag");
		}
	}
	

}
