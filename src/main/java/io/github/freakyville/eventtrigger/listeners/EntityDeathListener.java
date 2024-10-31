package io.github.freakyville.eventtrigger.listeners;

import io.github.freakyville.eventtrigger.services.EntityActionService;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {
    private final EntityActionService entityActionService;

    public EntityDeathListener(EntityActionService entityActionService) {
        this.entityActionService = entityActionService;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Player killer = entity.getKiller();
        if (killer == null) {
            return;
        }
        entityActionService.executeEntityTrigger(event.getEventName(), killer, entity);
    }

}
