package info.plugmania.MuteThaBot;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MuteThaBot extends JavaPlugin {

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
	    	Authorisation auth = new Authorisation(false,event.getPlayer());
	    	auth.startAuth(event.getPlayer());	    
	    }
	    
	    @EventHandler
	    public void onPlayerChat(PlayerChatEvent event) {
	    	getLogger().info("Tracked:" + event.getMessage());
	    }
	}
	
	public MuteThaBot() {
		
	}
	
	class Authorisation {
		boolean isAuth;
		String name;
		Player player;
		char rndChar;
			
		Authorisation(boolean isAuth, Player player) {
			name = player.getName();
			rndChar = genRandChar();
		}
		
		char genRandChar() {
	    	Random r = new Random();
	    	char c = (char)(r.nextInt(26) + 'a');
	    	return c;
		}
		
		void startAuth(Player player){
			getLogger().info("Starting auth on " + name);
			player.sendMessage("Please type '" + rndChar + "' now to authenticate yourself");
		}
	}
	
	
}
