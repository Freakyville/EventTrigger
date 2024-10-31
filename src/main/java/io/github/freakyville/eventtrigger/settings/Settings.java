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
        }
        configurationSection.getKeys(false).forEach(key -> {
            boolean enabled = configurationSection.getBoolean(key + ".enabled");
            String string = configurationSection.getString(key + ".type");
            if (string == null) {
                logger.severe("Type not found for event " + key);
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
            craftEventModels.put(key, new CraftEventModel(key, false, new ArrayList<>(), new ArrayList<>()));
            return;
        }
        List<String> items = configurationSection.getStringList(key + ".items");
        List<String> actions = configurationSection.getStringList(key + ".actions");
        if (actions.isEmpty()) {
            Bukkit.getLogger().severe("Actions not found for event " + key);
        }
        craftEventModels.put(key, new CraftEventModel(key, true, items, actions));
    }

    private void loadEntityEvents(String key, boolean enabled, ConfigurationSection configurationSection) {
        if (!enabled) {
            entityEventModels.put(key, new EntityEventModel(key, false, new ArrayList<>(), new ArrayList<>()));
            return;
        }
        List<String> entities = configurationSection.getStringList(key + ".entities");
        List<String> actions = configurationSection.getStringList(key + ".actions");
        if (actions.isEmpty()) {
            Bukkit.getLogger().severe("Actions not found for event " + key);
        }
        entityEventModels.put(key, new EntityEventModel(key, true, entities, actions));
    }

    private void loadBlockEvents(String key, boolean enabled, ConfigurationSection configurationSection) {
        if (!enabled) {
            blockEventModels.put(key, new BlockEventModel(key, false, new ArrayList<>(), new ArrayList<>()));
            return;
        }
        List<String> blocks = configurationSection.getStringList(key + ".blocks");
        List<String> actions = configurationSection.getStringList(key + ".actions");
        if (actions.isEmpty()) {
            Bukkit.getLogger().severe("Actions not found for event " + key);
        }
        blockEventModels.put(key, new BlockEventModel(key, true, blocks, actions));
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
