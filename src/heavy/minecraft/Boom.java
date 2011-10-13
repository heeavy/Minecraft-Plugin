package heavy.minecraft;

//import java.util.logging.Logger;
import org.bukkit.event.Event;
//import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Boom extends JavaPlugin {

    //private final Logger logfile = Logger.getLogger("Minecraft");
    //private final PluginDescriptionFile pdFile = this.getDescription();
    private final BoomPlayerListener playerListener = new BoomPlayerListener(this);
	
    @Override
	public void onDisable() {
	    //this.logfile.info(pdFile.getName() + " is now disabled.");
    	System.out.println("Plugin " + BoomMessage.PLUGIN_NAME + "is now disabled.");
	}
	
    @Override
	public void onEnable() {
		//this.logfile.info(pdFile.getName() + " version " + pdFile.getVersion() + "is enabled.");
    	System.out.println("Plugin " + BoomMessage.PLUGIN_NAME + "is now disabled.");
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Normal, this);
	}


    
}
