package it.tristana.lyoko.database;

import java.io.File;

import it.tristana.commons.config.ConfigDatabase;

public class ConfigLyokoDatabase extends ConfigDatabase {

	public static final String TABLE_PLAYERS = "table-players";
	
	public ConfigLyokoDatabase(File folder) {
		super(new File(folder, "database.yml"));
	}

	@Override
	protected void createDefault() {
		super.createDefault();
		set(TABLE_PLAYERS, "lyoko_players");
	}
}
