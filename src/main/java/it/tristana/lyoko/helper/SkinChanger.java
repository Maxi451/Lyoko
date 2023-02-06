package it.tristana.lyoko.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.util.UUIDTypeAdapter;

import it.tristana.lyoko.arena.Warrior;
import it.tristana.lyoko.config.SettingsWarriors;

public class SkinChanger {

	private Player player;
	private SettingsWarriors settings;
	
	public SkinChanger(Player player, SettingsWarriors settings) {
		this.player = player;
		this.settings = settings;
	}
	
	public void changeSkin(Warrior warrior) {
		setSkin(((CraftPlayer) player).getHandle().getGameProfile(), UUID.fromString(warrior.getSkinUuid(settings)));
	}
	
	private static boolean setSkin(GameProfile profile, UUID uuid) {
	    try {
	        HttpsURLConnection connection = (HttpsURLConnection) new URL(String.format("https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false", UUIDTypeAdapter.fromUUID(uuid))).openConnection();
	        if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
	            String reply = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
	            String skin = reply.split("\"value\":\"")[1].split("\"")[0];
	            String signature = reply.split("\"signature\":\"")[1].split("\"")[0];
	            profile.getProperties().put("textures", new Property("textures", skin, signature));
	            return true;
	        } else {
	            System.out.println("Connection could not be opened (Response code " + connection.getResponseCode() + ", " + connection.getResponseMessage() + ")");
	            return false;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
