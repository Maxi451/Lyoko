package it.tristana.lyoko.arena;

import org.bukkit.World;
import org.bukkit.entity.Player;

import it.tristana.commons.arena.BasicArena;
import it.tristana.lyoko.Main;
import it.tristana.lyoko.arena.player.LyokoPlayer;

public class LyokoArena extends BasicArena<LyokoPlayer> {

	public static final int MAX_PLAYERS = 4;
	
	public LyokoArena(Main plugin, World world, String name) {
		super(plugin, world, name, 1);
	}

	@Override
	public void runTick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean testPlayerJoin(Player player) {
		return players.size() < MAX_PLAYERS;
	}

	@Override
	public boolean onPlayerJoin(Player player) {
		if (testPlayerJoin(player)) {
			return players.add(new LyokoPlayer(player, this));
		}
		return false;
	}

	@Override
	public void onPlayerLeave(Player player) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onSpectator(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeArena() {
		
	}

	@Override
	public boolean checkStartingConditions() {
		return players.size() >= getMinPlayersToStart();
	}
}
