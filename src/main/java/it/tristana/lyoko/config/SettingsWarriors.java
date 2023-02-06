package it.tristana.lyoko.config;

import java.io.File;

import it.tristana.commons.config.Settings;

public class SettingsWarriors extends Settings<ConfigWarriors> {

	private String aelitaSkinUuid;
	private String oddSkinUuid;
	private String yumiSkinUuid;
	private String ulrichSkinUuid;
	
	public SettingsWarriors(File folder) {
		super(folder, ConfigWarriors.class);
	}

	@Override
	protected void reload(ConfigWarriors config) {
		aelitaSkinUuid = config.getString(ConfigWarriors.AELITA_SKIN_UUID);
		oddSkinUuid = config.getString(ConfigWarriors.ODD_SKIN_UUID);
		yumiSkinUuid = config.getString(ConfigWarriors.YUMI_SKIN_UUID);
		ulrichSkinUuid = config.getString(ConfigWarriors.ULRICH_SKIN_UUID);
	}
	
	public String getAelitaSkinUuid() {
		return aelitaSkinUuid;
	}

	public String getOddSkinUuid() {
		return oddSkinUuid;
	}

	public String getYumiSkinUuid() {
		return yumiSkinUuid;
	}

	public String getUlrichSkinUuid() {
		return ulrichSkinUuid;
	}
}
