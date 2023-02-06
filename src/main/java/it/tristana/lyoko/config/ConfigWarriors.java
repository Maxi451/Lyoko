package it.tristana.lyoko.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigWarriors extends Config {

	private static final String SKIN_UUID = "skin-uuid";

	private static final String AELITA = "aelita.";
	public static final String AELITA_SKIN_UUID = AELITA + SKIN_UUID;

	private static final String ODD = "odd.";
	public static final String ODD_SKIN_UUID = ODD + SKIN_UUID;

	private static final String YUMI = "yumi.";
	public static final String YUMI_SKIN_UUID = YUMI + SKIN_UUID;

	private static final String ULRICH = "ulrich.";
	public static final String ULRICH_SKIN_UUID = ULRICH + SKIN_UUID;

	public ConfigWarriors(File folder) {
		super(new File(folder, "warriors.yml"));
	}

	@Override
	protected void createDefault() {
		set(AELITA_SKIN_UUID, "");

		set(ODD_SKIN_UUID, "");

		set(YUMI_SKIN_UUID, "");

		set(ULRICH_SKIN_UUID, "");
	}
}
