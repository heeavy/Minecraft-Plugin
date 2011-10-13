package heavy.minecraft;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class BoomPlayerListener extends PlayerListener {
	public static Boom plugin;
	
	public BoomPlayerListener(Boom instance) {
		plugin = instance;
	}
	
	public void onPlayerChat(PlayerChatEvent event) {
		
		//Checks if user is operator and gives him a prefix
		this.prefixHandler(event);
		//Checks if a command has been used.
		if(event.getMessage().startsWith("!boom")) {
			this.boomHandler(event);
		}
   }
	
	public void prefixHandler(PlayerChatEvent player) {
		
		final String PREFIX = "[ADMIN]";
		
		if(player.getPlayer().isOp()) {
            player.setFormat(ChatColor.RED + PREFIX + ChatColor.WHITE + "<" + player.getPlayer().getName() + "> " + player.getMessage());
        } else {
            player.setFormat("<" + player.getPlayer().getName() +"> " + player.getMessage());
        }
	}
	
	public void boomHandler(PlayerChatEvent player) {
		String[] split = null;
		Player[] targets = null;
		
		if(player.getMessage().length() >= 7) {
			split = player.getMessage().split(" ");
			targets = plugin.getServer().getOnlinePlayers();
		} else {
			player.getPlayer().sendMessage(BoomMessage.WRONG_COMMAND_BOOM_SYNTAX);
			return;
		}
		
		for(Player target : targets) {
			if(target.getName().equalsIgnoreCase(split[1])) {
				Player boomer = target; //Player boomer = plugin.getServer().getPlayer(split[1]); can also be used to globally find the player.
				this.explodePlayer(player.getPlayer(), boomer);
			} else {
				player.getPlayer().sendMessage(BoomMessage.PLAYER_NOT_ONLINE);

			}
			
		}
	}
	
	public void explodePlayer(Player sender, Player target) {
		float explosionPower = 4F;
		target.getWorld().createExplosion(target.getLocation(), explosionPower);
		target.setHealth(0);
	}

}
	