package me.apeiros.alchimiavitae;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun5.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;

import me.apeiros.alchimiavitae.setup.Setup;

/**
 * Main class
 */
public class AlchimiaVitae extends JavaPlugin implements SlimefunAddon {

    private static AlchimiaVitae instance;

    @Override
    public void onEnable() {
        instance = this;

        // Register translations for item lore / names
        Slimefun.getItemTranslationService().registerTranslations(this);

        // Set up items and listeners
        Setup.setup(this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/Apeiros-46B/AlchimiaVitae/issues";
    }

    public static AlchimiaVitae i() {
        return instance;
    }

}
