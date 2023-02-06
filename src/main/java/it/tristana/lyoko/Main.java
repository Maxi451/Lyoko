package it.tristana.lyoko;

import java.io.File;

import org.bukkit.Location;

import it.tristana.commons.database.BasicUsersManager;
import it.tristana.commons.database.DatabaseManager;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.helper.PluginDraft;
import it.tristana.commons.interfaces.DatabaseHolder;
import it.tristana.commons.interfaces.MainLobbyHolder;
import it.tristana.commons.interfaces.PartiesHolder;
import it.tristana.commons.interfaces.Reloadable;
import it.tristana.commons.interfaces.arena.player.PartiesManager;
import it.tristana.commons.interfaces.chat.ChatManager;
import it.tristana.commons.interfaces.database.Database;
import it.tristana.commons.interfaces.database.UsersManager;
import it.tristana.commons.listener.ChatListener;
import it.tristana.commons.listener.LoginQuitListener;
import it.tristana.lyoko.arena.LyokoPartiesManager;
import it.tristana.lyoko.chat.LyokoChatManager;
import it.tristana.lyoko.command.LyokoCommand;
import it.tristana.lyoko.config.SettingsWarriors;
import it.tristana.lyoko.database.ConfigLyokoDatabase;
import it.tristana.lyoko.database.LyokoDatabase;
import it.tristana.lyoko.database.LyokoUser;

public class Main extends PluginDraft implements Reloadable, DatabaseHolder, PartiesHolder, MainLobbyHolder {

	private UsersManager<LyokoUser> usersManager;
	private DatabaseManager<LyokoUser> database;
	private PartiesManager partiesManager;
	private ChatManager chatManager;

	private SettingsWarriors settingsWarriors;

	private Location mainLobby;
	private boolean isDisabled;

	@Override
	public void onEnable() {
		File folder = getFolder();
		try {
			database = getDatabaseManager(folder);
			database.openConnection();
		} catch (Exception e) {
			CommonsHelper.consoleInfo("&cCould not open the database connection, fix the issue and restart the server");
			writeThrowableOnErrorsFile(e);
			isDisabled = true;
			return;
		}
		setupConfigs(folder);
		setupManagers();
		setupListeners();
		registerCommand(this, LyokoCommand.class, "lyoko", "commands.yml");
	}

	@Override
	public void onDisable() {
		if (isDisabled) {
			return;
		}
	}

	@Override
	public void reload() {
		settingsWarriors.reload();
	}

	@Override
	public PartiesManager getPartiesManager() {
		return partiesManager;
	}

	@Override
	public Database getStorage() {
		return database;
	}

	@Override
	public Location getMainLobby() {
		return mainLobby;
	}

	@Override
	public void setMainLobby(Location mainLobby) {
		this.mainLobby = mainLobby;
	}

	private DatabaseManager<LyokoUser> getDatabaseManager(File folder) {
		ConfigLyokoDatabase config = new ConfigLyokoDatabase(folder);
		String host = config.getString(ConfigLyokoDatabase.HOST);
		int port = Integer.parseInt(config.getString(ConfigLyokoDatabase.PORT));
		String database = config.getString(ConfigLyokoDatabase.DATABASE);
		String username = config.getString(ConfigLyokoDatabase.USER);
		String password = config.getString(ConfigLyokoDatabase.PASSWORD);
		String tablePlayers = config.getString(ConfigLyokoDatabase.TABLE_PLAYERS);
		return new LyokoDatabase(host, database, username, password, port, this, tablePlayers);
	}

	private void setupConfigs(File folder) {
		settingsWarriors = new SettingsWarriors(folder);
	}

	private void setupManagers() {
		usersManager = new BasicUsersManager<>(database);
		partiesManager = new LyokoPartiesManager();
		chatManager = new LyokoChatManager();
	}

	private void setupListeners() {
		register(new LoginQuitListener<>(usersManager, database));
		register(new ChatListener(chatManager));
	}
}
