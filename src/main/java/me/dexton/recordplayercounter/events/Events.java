package me.dexton.recordplayercounter.events;

import me.dexton.recordplayercounter.RecordPlayerCounter;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.ServerPing.Players;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Events implements Listener {

	private RecordPlayerCounter plugin;
	
	public Events(RecordPlayerCounter plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPing(ProxyPingEvent event) {
		ServerPing response = event.getResponse();
 		Players players = response.getPlayers();
 		players = new Players(plugin.getConfig().getRecordPlayerCount(), players.getOnline(), players.getSample());
 		ServerPing ping = new ServerPing(response.getVersion(), players, response.getDescription(), response.getFaviconObject());
 		event.setResponse(ping);
	}
	
	@EventHandler
	public void onLogin(LoginEvent event) {
		int count = plugin.getProxy().getOnlineCount() + 1;
		if(count > plugin.getConfig().getRecordPlayerCount()) {
			plugin.getConfig().setRecordPlayerCount(count);
			plugin.getLogger().info("New record of online players is " + count);
		}
	}
}
