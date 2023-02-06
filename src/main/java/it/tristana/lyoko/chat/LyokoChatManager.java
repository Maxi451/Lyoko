package it.tristana.lyoko.chat;

import org.bukkit.entity.Player;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.interfaces.chat.ChatManager;

public class LyokoChatManager implements ChatManager {

	public LyokoChatManager() {
		
	}

	@Override
	public void onChat(Player player, String msg) {
		CommonsHelper.broadcast(player + " > " + msg);
	}
}
