package skywolf46.IntegratedMessageUtil;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import skywolf46.IntegratedMessageUtil.CustomEvent.PlaceHolderRegisterEvent;
import skywolf46.IntegratedMessageUtil.Data.BuilderHashMap;
import skywolf46.IntegratedMessageUtil.Data.ParameterWrapper;
import skywolf46.IntegratedMessageUtil.Data.PreparsedDataSentence;
import skywolf46.IntegratedMessageUtil.Data.PreparsedSentenceStorage;
import skywolf46.IntegratedMessageUtil.Listener.RegisterEventListener;
import skywolf46.IntegratedMessageUtil.PlaceHolders.DefaultHolder.*;
import skywolf46.IntegratedMessageUtil.Storage.CharacterHolderStorage;
import skywolf46.IntegratedMessageUtil.Storage.PlaceHolderCharacterStorage;
import skywolf46.IntegratedMessageUtil.Storage.PlaceHolderStorage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class IntegratedMessageUtil extends JavaPlugin {
    private static HashMap<String, PreparsedSentenceStorage> preparsedStorage = new HashMap<>();
    private static PlaceHolderCharacterStorage storage = new PlaceHolderCharacterStorage();

    public static boolean isPlaceHolder(char c) {
        return storage.containsKey(c);
    }

    public static PlaceHolderStorage getHolderStorage(char start, char end) {
        return getCharacterHolder(start).getHolderStorage(end);
    }

    public static CharacterHolderStorage getCharacterHolder(char c) {
        return storage.getStorage(c);
    }

    public static List<Character> getHolders() {
        return storage.getCharacterList();
    }

    @Override
    public void onLoad() {
        PlaceHolderStorage storage = getHolderStorage('<', '>');
        storage.registerPlaceholder("player", new PlayerNameHolder());
        storage.registerPlaceholder("hp", new PlayerCurrentHPHolder(0));
        storage.registerPlaceholder("maxHp", new PlayerMaxHPHolder(0));
        storage.registerPlaceholder("absorption", new AbsorptionHolder());
        storage.registerPlaceholder("money", new MoneyHolder());
        storage.registerPlaceholder("moneyFormat", new MoneyFormattedHolder());
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new RegisterEventListener(), this);
        Bukkit.getConsoleSender().sendMessage("§6[IntegratedMessageUtil] §7Loading messages from loaded existing...");
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.getPluginManager().callEvent(new PlaceHolderRegisterEvent());
            }
        });
    }

    public static PreparsedSentenceStorage getSentenceStorage(String storageName) {
        return preparsedStorage.computeIfAbsent(storageName, str -> new PreparsedSentenceStorage());
    }


    public static PreparsedDataSentence getSentence(String storage, String name) {
        return getSentenceStorage(storage).getSentence(name);
    }

    public static List<PreparsedDataSentence> getAllSentence(String storage, String name) {
        return getSentenceStorage(storage).getAll(name);
    }


    public static PreparsedSentenceStorage loadSentenceStorage(String storageName, File toLoad) {
        PreparsedSentenceStorage pss = getSentenceStorage(storageName);
        if (toLoad.exists()) {
            YamlConfiguration ymc = YamlConfiguration.loadConfiguration(toLoad);
            for (String n : ymc.getKeys(true))
                if (ymc.isList(n)) {
                    for (String str : ymc.getStringList(n))
                        pss.addSentence(n, new PreparsedDataSentence(str));
                } else {
                    pss.addSentence(n, new PreparsedDataSentence(ymc.getString(n)));
                }
        }
        return pss;
    }

    public static void sendMessage(Player p, String storage, String name) {
        List<PreparsedDataSentence> sent = getSentenceStorage(storage).getAll(name);
        if (sent != null) {
            ParameterWrapper pw = ParameterWrapper.of(p);
            for (PreparsedDataSentence s : sent) {
                String str = s.toString(pw);
                int index = str.indexOf(":");
                if (index == -1) {
                    p.sendMessage(str);
                }
            }
        }
    }

    public static void sendMessage(Player p, String storage, String name, BuilderHashMap<String, String> map) {
        List<PreparsedDataSentence> sent = getSentenceStorage(storage).getAll(name);
        if (sent != null) {
            ParameterWrapper pw = ParameterWrapper.of(p);
            for (PreparsedDataSentence s : sent) {
                String str = s.toString(pw, map);
                int index = str.indexOf(":");
                if (index == -1) {
                    p.sendMessage(str);
                }
            }
        }
    }
}
