package net.zffu.buildtickets.locale;

import net.zffu.buildtickets.BuildTicketsPlugin;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Manages the languages files for translation support.
 */
public class LocaleManager {

    private BuildTicketsPlugin plugin;
    public static final Locale[] SUPPORTED_LOCALES = new Locale[] {Locale.ENGLISH};

    public LocaleManager(BuildTicketsPlugin plugin) {
        this.plugin =  plugin;
    }

}
