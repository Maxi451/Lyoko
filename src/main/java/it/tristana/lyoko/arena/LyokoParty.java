package it.tristana.lyoko.arena;

import org.bukkit.entity.Player;

import it.tristana.commons.arena.player.BasicParty;

public class LyokoParty extends BasicParty {
	
	public LyokoParty(Player owner) {
		super(owner);
	}
	
	@Override
	public boolean tryToJoin(Player player) {
		if (players.size() + 1 < LyokoArena.MAX_PLAYERS) {
			return super.tryToJoin(player);
		}
		return false;
	}
}
