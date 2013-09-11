package me.PimpDuck.NearPlus;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class NearPlus extends JavaPlugin{

	  private CommandClass CommandClass = new CommandClass(this);

	Logger log = Logger.getLogger("Minecraft");


	  public static FileManager configurationFile;
	  public static HashMap<String, Long> delayedPlayers;
	  private static int delay;

	@Override
	public void onEnable(){
		log.info("[NearPlus] has been enabled!");
        log.info("Copyright 2013 PimpDuck All rights reserved.");
		saveDefaultConfig();
        configurationFile = new FileManager(this, "config.yml");
        delayedPlayers = new HashMap<String, Long>();
        delay = getConfig().getInt("DCNear.Options.Cooldown");
        getCommand("near").setExecutor(this.CommandClass);
	}
	@Override
	public void onDisable(){
		log.info("[NearPlus] has been disabled!");	
	}

	  public static void addDelayedPlayer(Player player) {
		    delayedPlayers.put(player.getName(), System.currentTimeMillis());
		  }

		  public static void removeDelayedPlayer(Player player) {
		    delayedPlayers.remove(player.getName());
		  }

		  public static boolean playerDelayed(Player player) {
		    return delayedPlayers.containsKey(player.getName());
		  }

		  public static Long getPlayerDelay(Player player) {
		    return (Long)delayedPlayers.get(player.getName());
		  }

		  public static int getDelay(int multiplier) {
		    return delay * multiplier;
		  }

		  public static int getRemainingTime(Player player) {
		    return (int)(getDelay(1) - (System.currentTimeMillis() - getPlayerDelay(player).longValue()) / 1000L / 60);
		  }

}
