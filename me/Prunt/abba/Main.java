package me.Prunt.abba;

import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    int time;
    boolean b = true;
    Random rand = new Random();
    List<String> list;
    BossBar bb;

    @Override
    public void onEnable() {
	saveDefaultConfig();

	list = getConfig().getStringList("random-messages");
	time = getConfig().getInt("time-between");

	getServer().getScheduler().runTaskTimer(this, new Runnable() {
	    @Override
	    public void run() {
		if (b) {
		    bb = getServer().createBossBar(list.get(rand.nextInt(list.size())), BarColor.valueOf(random()),
			    BarStyle.valueOf(rand()));

		    for (Player p : getServer().getOnlinePlayers()) {
			bb.addPlayer(p);
		    }

		    b = false;
		} else {
		    bb.removeAll();

		    b = true;
		}
	    }
	}, time * 20, time * 20);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	reloadConfig();
	list = getConfig().getStringList("random-messages");
	time = getConfig().getInt("time-between");

	sender.sendMessage(ChatColor.GREEN + "Config reloaded!");
	return true;
    }

    private String random() {
	int i = rand.nextInt(7);

	switch (i) {
	case 0:
	    return "BLUE";
	case 1:
	    return "PINK";
	case 2:
	    return "RED";
	case 3:
	    return "YELLOW";
	case 4:
	    return "WHITE";
	case 5:
	    return "PURPLE";
	case 6:
	    return "GREEN";
	default:
	    return "GREEN";
	}
    }

    private String rand() {
	int i = rand.nextInt(5);

	switch (i) {
	case 0:
	    return "SEGMENTED_6";
	case 1:
	    return "SEGMENTED_10";
	case 2:
	    return "SEGMENTED_12";
	case 3:
	    return "SEGMENTED_20";
	case 4:
	    return "SOLID";
	default:
	    return "SOLID";
	}
    }
}
