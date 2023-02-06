package it.tristana.lyoko.database;

import org.bukkit.OfflinePlayer;

import it.tristana.commons.database.BasicUser;

public class LyokoUser extends BasicUser {

	private int maxLevel;
	
	public LyokoUser(OfflinePlayer player) {
		this(player, 0);
	}

	public LyokoUser(OfflinePlayer player, int maxLevel) {
		super(player);
		this.maxLevel = maxLevel;
	}
	
	public int getMaxLevel() {
		return maxLevel;
	}
	
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
}
