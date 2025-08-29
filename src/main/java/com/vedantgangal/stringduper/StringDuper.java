package com.vedantgangal.stringduper;

import com.vedantgangal.stringduper.commands.StringDuperCommand;
import com.vedantgangal.stringduper.listeners.CobwebListener;
import org.bukkit.plugin.java.JavaPlugin;

public class StringDuper extends JavaPlugin {

    private static StringDuper instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new CobwebListener(this), this);
        getCommand("stringduper").setExecutor(new StringDuperCommand(this));
        getLogger().info("StringDuper enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("StringDuper disabled.");
    }

    public static StringDuper getInstance() {
        return instance;
    }
}
