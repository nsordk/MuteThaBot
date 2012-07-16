package info.plugmania.MuteThaBot;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MuteThaBot extends JavaPlugin {

	public void onDisable (){
		getLogger().info("MuteThaBot is now disabled.");
	}
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(new LoginListener(), this);
		getLogger().info("MuteThaBot is enabled.");
	}
	
	public class LoginListener implements Listener {
	    @EventHandler
	    public void onPlayerLogin(PlayerJoinEvent event) {
	    	event.getPlayer().sendMessage("We know you are here");
	    	getLogger().info("Starting auth on " + event.getPlayer().getName());
	    }
	}

	public MuteThaBot(){
		char username;
		char promptVal;
		
		
	}
	
}
