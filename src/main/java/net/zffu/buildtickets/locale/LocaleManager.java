package net.zffu.buildtickets.locale;

import net.zffu.buildtickets.BuildTicketsPlugin;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Manages the languages files for translation support.
 */
public class LocaleManager {

    private BuildTicketsPlugin plugin;
    public static final Locale[] SUPPORTED_LOCALES = new Locale[] {Locale.ENGLISH};
    public static HashMap<Locale, HashMap<String, String>> locales = new HashMap<>();
    public static Locale defaultLocale = null;

    public LocaleManager(BuildTicketsPlugin plugin) {
        this.plugin =  plugin;
        defaultLocale = Locale.ENGLISH;
    }

    /**
     * Loads a specific locale.
     * @param locale
     */
    public void loadLocale(Locale locale) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("buildtickets", locale);
            Enumeration<String> keys = bundle.getKeys();

            HashMap<String, String> l = new HashMap<>();

            while(keys.hasMoreElements()) {
                String key = keys.nextElement();
                l.put(key, bundle.getString(key));
            }

            locales.put(locale, l);
        } catch (Exception e) {
            this.plugin.getLogger().warning("Could not load locale " + locale);
        }
    }

    /**
     * Gets the message from the locale.
     * @param id
     * @return
     */
    public static String getMessage(String id) {
        String t = locales.get(defaultLocale).get(id);
        if(t == null) return id;
        return t;
    }

    /**
     * Gets the message from the locale.
     * @param id
     * @param locale
     * @return
     */
    public static String getMessage(String id, Locale locale) {
        String t = locales.get(locale).get(id);
        if(t == null) return id;
        return t;
    }


    /**
     * Loads the locales
     */
    public void loadLocales() {
        for(Locale locale : SUPPORTED_LOCALES) {
            loadLocale(locale);
        }
    }
}
