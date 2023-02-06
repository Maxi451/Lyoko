package it.tristana.lyoko.arena;

import org.bukkit.entity.Player;

import it.tristana.commons.helper.BasicPartiesManager;
import it.tristana.commons.interfaces.arena.player.Party;

public class LyokoPartiesManager extends BasicPartiesManager {

	@Override
	protected Party generateParty(Player owner) {
		return new LyokoParty(owner);
	}
}
