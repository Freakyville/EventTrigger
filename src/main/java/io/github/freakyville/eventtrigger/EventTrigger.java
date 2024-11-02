package io.github.freakyville.eventtrigger;

import io.github.freakyville.eventtrigger.commands.MainCommand;
import io.github.freakyville.eventtrigger.listeners.BlockBreakListener;
import io.github.freakyville.eventtrigger.listeners.BlockPlaceListener;
import io.github.freakyville.eventtrigger.listeners.CraftItemListener;
import io.github.freakyville.eventtrigger.listeners.EntityDeathListener;
import io.github.freakyville.eventtrigger.services.BlockActionService;
import io.github.freakyville.eventtrigger.services.CraftActionService;
import io.github.freakyville.eventtrigger.services.EntityActionService;
import io.github.freakyville.eventtrigger.settings.Settings;
import io.github.freakyville.pmp.api.CPCAPI;
import io.github.freakyville.utilsupdated.PluginModule;
import io.github.freakyville.utilsupdated.commands.ReloadCommand;
import io.github.freakyville.utilsupdated.config.ConfigManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;

public final class EventTrigger extends PluginModule {
    private Settings settings;

    @Override
    public void onEnable() {
        // Configs
        ConfigManager configManager = new ConfigManager(this, "config.yml");

        // Settings
        this.settings = new Settings(configManager);

        // API
        ServicesManager servicesManager = getServer().getServicesManager();
        RegisteredServiceProvider<CPCAPI> registration = servicesManager.getRegistration(CPCAPI.class);
        if (registration == null) {
            throw new RuntimeException("Failed to hook into PlaceholderManager API!");
        }
        CPCAPI cpcApi = registration.getProvider();

        // Services
        BlockActionService blockActionService = new BlockActionService(this.settings, cpcApi);
        EntityActionService entityActionService = new EntityActionService(this.settings, cpcApi);
        CraftActionService craftActionService = new CraftActionService(this.settings, cpcApi);

        // Commands
        ReloadCommand reloadCommand = new ReloadCommand(this, "/eventtrigger reload");
        MainCommand mainCommand = new MainCommand(reloadCommand);
        getCommand("eventtrigger").setExecutor(mainCommand);

        // Listeners
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(blockActionService), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(blockActionService), this);
        getServer().getPluginManager().registerEvents(new EntityDeathListener(entityActionService), this);
        getServer().getPluginManager().registerEvents(new CraftItemListener(craftActionService), this);
    }

    @Override
    public void onEnabled() {

    }

    @Override
    public void onDisabled() {

    }

    @Override
    public void onDisable() {
    }

    @Override
    public void reload() {
        this.settings.reload();
    }

}
