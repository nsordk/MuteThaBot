package info.plugmania.MuteThaBot;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MuteThaBot extends JavaPlugin {

	Authorisation auth = new Authorisation(this);
	
	public void onDisable (){
		getLogger().info("MuteThaBot is now disabled.");
	}
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(new PluginListener(), this);
		getLogger().info("MuteThaBot is enabled.");
	}
	
	public class PluginListener implements Listener {
		@EventHandler
	    public void onPlayerLogin(PlayerJoinEvent event) {
	    	auth.player = event.getPlayer();
	    	auth.name = event.getPlayer().getName();
	    	auth.rndChar = auth.genRandChar();
	    	auth.startAuth();	    
	    }
	    
	    @EventHandler
	    public void onPlayerChat(PlayerChatEvent event) {
	    	if(auth.isAuth!=true) {
	    		if(auth.rndChar==event.getMessage().toCharArray()[0]) {
	    			auth.isAuth = true;
	    			auth.playerAccess();
	    		}
	    		else auth.playerQuit();
	    		event.setCancelled(true);
	    	}
	    }
	    
	    @EventHandler
	    public void onPlayerMove(PlayerMoveEvent event) {
	    	if(auth.isAuth!=true) {
	    		Location loc = event.getFrom();
	    		event.setTo(loc);
	    	}	
	    }
	    
	    @EventHandler
	    public void onPlayerQuit(PlayerQuitEvent event) {
	    	auth.isAuth = false;	    
	    }
	    
	}
	
	public MuteThaBot() {
		
	}
	
	class Authorisation {
		boolean isAuth;
		String name;
		Player player;
		char rndChar;
	
		MuteThaBot plugin;
		public Authorisation(MuteThaBot instance){
		plugin=instance;
		}
		
		char genRandChar() {
	    	Random r = new Random();
	    	char c = (char)(r.nextInt(26) + 'a');
	    	return c;
		}
		
		void startAuth(){
			player.sendMessage(ChatColor.GOLD + "[MuteThaBot] Please type '" + rndChar + "' now to authenticate yourself");
		}
		
		void playerAccess() {
			player.sendMessage(ChatColor.GOLD + "[MuteThaBot] Thank you!");
		}
		
		void playerQuit(){
			getLogger().info(name + " failed authentication");
			player.kickPlayer("Authentication failed, Goodbye!");
		}
	}
	
	
}
