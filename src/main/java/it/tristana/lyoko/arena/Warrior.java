package it.tristana.lyoko.arena;

import it.tristana.lyoko.config.SettingsWarriors;

public enum Warrior {
	
	AELITA {
		@Override
		public String getSkinUuid(SettingsWarriors settings) {
			return settings.getAelitaSkinUuid();
		}
	},
	ODD {
		@Override
		public String getSkinUuid(SettingsWarriors settings) {
			return settings.getOddSkinUuid();
		}
	},
	YUMI {
		@Override
		public String getSkinUuid(SettingsWarriors settings) {
			return settings.getYumiSkinUuid();
		}
	},
	ULRICH {
		@Override
		public String getSkinUuid(SettingsWarriors settings) {
			return settings.getUlrichSkinUuid();
		}
	};
	
	public abstract String getSkinUuid(SettingsWarriors settings);
}
