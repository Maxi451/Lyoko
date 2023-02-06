package it.tristana.lyoko.arena.player;

import org.bukkit.entity.Player;

import it.tristana.commons.interfaces.arena.player.ArenaPlayer;
import it.tristana.lyoko.arena.LyokoArena;

public class LyokoPlayer implements ArenaPlayer<LyokoArena> {

	private Player player;
	private LyokoArena arena;
	
	public LyokoPlayer(Player player, LyokoArena arena) {
		this.player = player;
		this.arena = arena;
	}
	
	@Override
	public LyokoArena getArena() {
		return arena;
	}

	@Override
	public Player getPlayer() {
		return player;
	}
}
