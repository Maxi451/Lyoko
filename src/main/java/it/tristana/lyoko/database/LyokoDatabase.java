package it.tristana.lyoko.database;

import java.sql.SQLException;

import org.bukkit.OfflinePlayer;

import it.tristana.commons.database.DatabaseManager;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.lyoko.Main;

public class LyokoDatabase extends DatabaseManager<LyokoUser> {

	private Main plugin;
	private String tablePlayers;

	public LyokoDatabase(String host, String database, String username, String password, int port, Main plugin, String tablePlayers) {
		super(host, database, username, password, port);
		this.plugin = plugin;
		this.tablePlayers = tablePlayers;
	}

	@Override
	public LyokoUser getUser(OfflinePlayer offlinePlayer) {
		String uuid = getUuid(offlinePlayer);
		try {
			return executeQuery(String.format("SELECT max_level FROM %s WHERE uuid = '%s';", tablePlayers, uuid), resultSet -> {
				try {
					if (resultSet.next()) {
						return new LyokoUser(offlinePlayer, resultSet.getInt("max_level"));
					}
				} catch (SQLException e) {
					plugin.writeThrowableOnErrorsFile(e);
					CommonsHelper.consoleInfo("&cCould not load Player " + uuid + "'s data!");
				}
				return new LyokoUser(offlinePlayer);
			});
		} catch (SQLException e) {
			plugin.writeThrowableOnErrorsFile(e);
		}
		return new LyokoUser(offlinePlayer);
	}

	@Override
	public void saveUser(LyokoUser user) {
		try {
			String uuid = getUuid(user.getPlayer());
			executeUpdate(String.format("REPLACE INTO %s (uuid, max_level) VALUES ('%s', %d);", tablePlayers, uuid, user.getMaxLevel()));
		} catch (SQLException e) {
			plugin.writeThrowableOnErrorsFile(e);
			CommonsHelper.consoleInfo("&cCould not save Player " + user.getPlayer().getName() + "! His data were: " + user);
		}
	}

	@Override
	protected void createTables() throws SQLException {
		executeUpdate("CREATE TABLE IF NOT EXISTS " + tablePlayers + "("
				+ "uuid CHAR(36) PRIMARY KEY,"
				+ "max_level INTEGER NOT NULL DEFAULT 0"
				+ ");"
				);
	}
}
