package it.tristana.lyoko.command;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.config.SettingsDefaultCommands;
import it.tristana.lyoko.Main;

public class LyokoCommand extends MainCommand<Main> {

	public LyokoCommand(Main plugin, SettingsDefaultCommands settings, String command) {
		super(plugin, settings, command);
	}
}
