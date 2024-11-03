package io.github.freakyville.eventtrigger.services;

import io.github.freakyville.eventtrigger.models.EntityEventModel;
import io.github.freakyville.eventtrigger.settings.Settings;
import io.github.freakyville.pmp.api.CPCAPI;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class EntityActionService {
    private final Settings settings;
    private final CPCAPI cpcApi;

    public EntityActionService(Settings settings, CPCAPI cpcApi) {
        this.settings = settings;
        this.cpcApi = cpcApi;
    }

    public void executeEntityTrigger(String eventName, Player player, Entity entity) {
        EntityEventModel entitySettings = settings.getEntitySettings(eventName);
        boolean enabled = entitySettings.isEnabled();
        if (!enabled) {
            return;
        }
        String entityName = entity.getType().name();
        List<String> entities = entitySettings.getEntities();
        boolean isOkayEntity = entities.contains(entityName);
        if (!isOkayEntity && !entities.isEmpty()) {
            return;
        }
        String placeholder = entitySettings.getName() + "_" + entityName;
        cpcApi.addToPlaceholder(player.getUniqueId(), placeholder.replace("_", "-"), 1);
    }

}
