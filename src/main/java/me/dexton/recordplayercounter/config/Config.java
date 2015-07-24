package me.dexton.recordplayercounter.config;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import me.dexton.recordplayercounter.RecordPlayerCounter;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Config {

	RecordPlayerCounter plugin;
	File config;
	ConfigurationProvider yaml;

	private int recordPlayerCount;

	public Config(RecordPlayerCounter plugin) {
		this.plugin = plugin;
		this.config = new File(plugin.getDataFolder(), "record.yml");
		this.config.getParentFile().mkdirs();
		this.yaml = ConfigurationProvider.getProvider(YamlConfiguration.class);
	}

	public void loadConfig() {
		try {
			config.createNewFile();
			reloadConfig();
		} catch (IOException e) {
			plugin.getLogger().log(Level.WARNING, "Cannot load configuration file.", e);
		}
	}

	public void saveConfig() {
		try {
			Configuration conf = yaml.load(config);
			yaml.save(conf, config);
		} catch (IOException e) {
			plugin.getLogger().log(Level.WARNING, "Cannot save configuration file.", e);
		}
	}

	public void reloadConfig() {
		try {
			Configuration conf = yaml.load(config);

			if(conf.get("record-player-count") == null) {
				conf.set("record-player-count", 1);
				recordPlayerCount = 1;
			} else {
				recordPlayerCount = conf.getInt("record-player-count");
			}

			yaml.save(conf, config);
		} catch (IOException e) {
			plugin.getLogger().log(Level.WARNING, "Cannot load configuration file.", e);
		}
	}

	public Configuration getRunningConfig() {
		try {
			Configuration conf = yaml.load(config);
			return conf;
		} catch (IOException e) {
			plugin.getLogger().log(Level.WARNING, "Cannot load configuration file.", e);
			e.printStackTrace();
		}
		return null;
	}
	
	public int getRecordPlayerCount() {
		return recordPlayerCount;
	}
	
	public void setRecordPlayerCount(int count) {
		recordPlayerCount = count;
		getRunningConfig().set("record-player-count", count);
		saveConfig();
	}
}
