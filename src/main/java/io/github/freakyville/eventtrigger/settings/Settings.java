package io.github.freakyville.eventtrigger.settings;

import io.github.freakyville.eventtrigger.models.BlockEventModel;
import io.github.freakyville.eventtrigger.models.CraftEventModel;
import io.github.freakyville.eventtrigger.models.EntityEventModel;
import io.github.freakyville.utilsupdated.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Settings {
    private final Map<String, BlockEventModel> blockEventModels = new HashMap<>();
    private final Map<String, EntityEventModel> entityEventModels = new HashMap<>();
    private final Map<String, CraftEventModel> craftEventModels = new HashMap<>();
    private final ConfigManager configManager;

    public Settings(ConfigManager configManager) {
        this.configManager = configManager;
        load();
    }

    private void load() {
        Logger logger = Bukkit.getLogger();
        ConfigurationSection configurationSection = configManager.getConfigurationSection("events");
        if (configurationSection == null) {
            logger.severe("Events section not found");
            return;
        }
        configurationSection.getKeys(false).forEach(key -> {
            boolean enabled = configurationSection.getBoolean(key + ".enabled");
            String string = configurationSection.getString(key + ".type");
            if (string == null) {
                logger.severe("Type not found for event " + key);
                return;
            }
            if (string.equalsIgnoreCase("block")) {
                loadBlockEvents(key, enabled, configurationSection);
            } else if (string.equalsIgnoreCase("entity")) {
                loadEntityEvents(key, enabled, configurationSection);
            } else if (string.equalsIgnoreCase("craft")) {
                loadCraftEvents(key, enabled, configurationSection);
            } else {
                logger.severe("Type " + string + " not found for event " + key);
            }
        });
    }

    private void loadCraftEvents(String key, boolean enabled, ConfigurationSection configurationSection) {
        if (!enabled) {
            craftEventModels.put(key, new CraftEventModel(key, false, new ArrayList<>()));
            return;
        }
        List<String> items = configurationSection.getStringList(key + ".items");
        craftEventModels.put(key, new CraftEventModel(key, true, items));
    }

    private void loadEntityEvents(String key, boolean enabled, ConfigurationSection configurationSection) {
        if (!enabled) {
            entityEventModels.put(key, new EntityEventModel(key, false, new ArrayList<>()));
            return;
        }
        List<String> entities = configurationSection.getStringList(key + ".entities");
        entityEventModels.put(key, new EntityEventModel(key, true, entities));
    }

    private void loadBlockEvents(String key, boolean enabled, ConfigurationSection configurationSection) {
        if (!enabled) {
            blockEventModels.put(key, new BlockEventModel(key, false, new ArrayList<>()));
            return;
        }
        List<String> blocks = configurationSection.getStringList(key + ".blocks");
        blockEventModels.put(key, new BlockEventModel(key, true, blocks));
    }

    public BlockEventModel getBlockSettings(String event) {
        BlockEventModel blockEventModel = blockEventModels.get(event);
        if (blockEventModel == null) {
            Bukkit.getLogger().severe("Event " + event + " not found");
        }
        return blockEventModel;
    }

    public EntityEventModel getEntitySettings(String event) {
        EntityEventModel entityEventModel = entityEventModels.get(event);
        if (entityEventModel == null) {
            Bukkit.getLogger().severe("Event " + event + " not found");
        }
        return entityEventModel;
    }

    public CraftEventModel getCraftSettings(String event) {
        CraftEventModel craftEventModel = craftEventModels.get(event);
        if (craftEventModel == null) {
            Bukkit.getLogger().severe("Event " + event + " not found");
        }
        return craftEventModel;
    }

    public void reload() {
        blockEventModels.clear();
        this.configManager.reloadCustomConfig();
        load();
    }

}
