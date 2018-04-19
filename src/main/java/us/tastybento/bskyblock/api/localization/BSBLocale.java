package us.tastybento.bskyblock.api.localization;

import java.io.File;
import java.util.Locale;

import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author Poslovitch, Tastybento
 */
public class BSBLocale {

    private Locale locale;
    private YamlConfiguration config;

    public BSBLocale(Locale locale, File file) {
        this.locale = locale;
        config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Get text from the yml file for this locale
     * @param reference - the YAML node where the text is
     * @return Text for this locale reference or the reference if nothing has been found
     */
    public String get(String reference) {
        if (config.contains(reference)) {
            return config.getString(reference);
        }
        return reference; // return reference in case nothing has been found
    }

    /**
     * Returns the locale language
     * @return the locale language
     */
    public String getLanguage(){
        if(locale == null) {
            return "unknown";
        }

        return locale.getDisplayLanguage();
    }

    /**
     * Returns the locale country
     * @return the locale country
     */
    public String getCountry(){
        if(locale == null) {
            return "unknown";
        }

        return locale.getDisplayCountry();
    }

    /**
     * Returns the locale language tag (e.g: en-GB)
     * @return the locale language tag
     */
    public String toLanguageTag(){
        return locale.toLanguageTag();
    }

    /**
     * Merges a language YAML file to this locale
     * @param language
     */
    public void merge(File language) {
        YamlConfiguration toBeMerged = YamlConfiguration.loadConfiguration(language);
        for (String key : toBeMerged.getKeys(true)) {
            if (!config.contains(key)) {
                config.set(key, toBeMerged.get(key));
            }
        }
    }

    public boolean contains(String reference) {
        return config.contains(reference);
    }

}
