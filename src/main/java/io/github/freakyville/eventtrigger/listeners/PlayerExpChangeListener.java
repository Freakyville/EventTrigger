package io.github.freakyville.eventtrigger.listeners;

import io.github.freakyville.eventtrigger.services.ExpActionService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class PlayerExpChangeListener implements Listener {
    private final ExpActionService expActionService;

    public PlayerExpChangeListener(ExpActionService expActionService) {
        this.expActionService = expActionService;
    }

    @EventHandler
    public void onExpChange(PlayerExpChangeEvent event) {
        Player player = event.getPlayer();
        expActionService.executeExpTrigger(event.getEventName(), player, event.getAmount());
    }

}
