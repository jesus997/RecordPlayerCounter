package me.dexton.recordplayercounter;

import me.dexton.recordplayercounter.config.Config;
import me.dexton.recordplayercounter.events.Events;
import net.md_5.bungee.api.plugin.Plugin;

public class RecordPlayerCounter extends Plugin {
	
	private Config c;
	
	@Override
	public void onEnable() {
		getProxy().getPluginManager().registerListener(this, new Events(this));
		c.loadConfig();
	}
	
	@Override
	public void onDisable() {
		c.saveConfig();
	}
	
	public Config getConfig() {
		return c;
	}
}
