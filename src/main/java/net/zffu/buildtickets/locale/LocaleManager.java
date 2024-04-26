package net.zffu.buildtickets.locale;

import net.zffu.buildtickets.BuildTicketsPlugin;
import org.bukkit.entity.HumanEntity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

/**
 * Manages the languages files for translation support.
 */
public class LocaleManager {

    private final BuildTicketsPlugin plugin;
    private static final File LOCALE_FOLDER = new File(BuildTicketsPlugin.getInstance().getDataFolder(), "locales");
    public static final Locale[] SUPPORTED_LOCALES = new Locale[] {Locale.ENGLISH, Locale.FRENCH};
    public static HashMap<Locale, EnumMap<LocaleString, String>> locales = new HashMap<>();
    public static HashMap<UUID, Locale> playerLocales;

    public static Locale defaultLocale = null;

    public LocaleManager(BuildTicketsPlugin plugin) {
        this.plugin =  plugin;
        defaultLocale = Locale.forLanguageTag(plugin.getConfig().getString("default-language", "en"));
        plugin.getLogger().info("Default Locale: " + defaultLocale.getDisplayName());
        if(plugin.getConfig().getBoolean("allow-players-to-choose-custom-language", false)) {
            playerLocales = new HashMap<>();
        }

        if(!LOCALE_FOLDER.exists()) LOCALE_FOLDER.mkdirs();
    }

    /**
     * Loads a specific locale.
     * @param locale the locale type to load.
     */
    public void loadLocale(Locale locale) {
        File customLocale = new File(LOCALE_FOLDER, "buildtickets_" + locale.getDisplayName() + ".properties");
        ResourceBundle bundle = null;
        EnumMap<LocaleString, String> l = new EnumMap<>(LocaleString.class);

        if(customLocale.exists()) {
            plugin.getLogger().info("Loading custom locale from locales folder!");

            try (BufferedReader reader = Files.newBufferedReader(customLocale.toPath(), StandardCharsets.UTF_8)) {
                bundle = new PropertyResourceBundle(reader);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } ;
        }
        else {
            try {
                bundle = ResourceBundle.getBundle("buildtickets", locale);
            } catch (Exception e) {
                this.plugin.getLogger().warning("Could not load locale " + locale + e);
            }
        }

        for(LocaleString string : LocaleString.values()) {
            if(bundle.containsKey(string.getKey())) {
                l.put(string, bundle.getString(string.getKey()));
            }
        }

        locales.put(locale, l);
    }

    /**
     * Gets the message from the locale.
     * @param id the id of the stored string in the locale files.
     * @return the translated message, or the key if he couldn't be translated.
     */
    public static String getMessage(LocaleString id) {
        String t = locales.get(defaultLocale).get(id);
        if(t == null) return id.getKey();
        return t;
    }

    /**
     * Gets the message from the locale.
     * @param id the id of the stored string in the locale files.
     * @param locale the locale to get the message from.
     * @return the translated message, or the key if he couldn't be translated.
     */
    public static String getMessage(LocaleString id, Locale locale) {
        String t = locales.get(locale).get(id);
        if(t == null) return id.getKey();
        return t;
    }

    /**
     * Gets the translated message by using the player's locale preference.
     * @param id the string id.
     * @param entity the player to base the locale of.
     * @return the translated message, or the key if he couldn't be translated.
     */
    public static String getMessage(LocaleString id, HumanEntity entity) {
        if(playerLocales == null || !playerLocales.containsKey(entity.getUniqueId())) return getMessage(id);
        return getMessage(id, playerLocales.get(entity.getUniqueId()));
    }

    /**
     * Sets the player's locale preference.
     * @param entity the player.
     * @param locale the local to set as the new preference.
     */
    public static void setPlayerLocale(HumanEntity entity, Locale locale) {
        if(locale == defaultLocale) playerLocales.remove(entity.getUniqueId());
        playerLocales.put(entity.getUniqueId(), locale);
    }

    /**
     * Loads the locales from the plugin's resources.
     */
    public void loadLocales() {
        for(Locale locale : SUPPORTED_LOCALES) {
            loadLocale(locale);
        }
    }
}
