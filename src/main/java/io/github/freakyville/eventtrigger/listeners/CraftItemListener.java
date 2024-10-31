package io.github.freakyville.eventtrigger.listeners;

import io.github.freakyville.eventtrigger.services.CraftActionService;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftItemListener implements Listener {
    private final CraftActionService craftActionService;

    public CraftItemListener(CraftActionService craftActionService) {
        this.craftActionService = craftActionService;
    }

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        ItemStack result = event.getRecipe().getResult();
        HumanEntity whoClicked = event.getWhoClicked();
        if (!(whoClicked instanceof Player)) {
            return;
        }
        craftActionService.executeCraftTrigger(event.getEventName(), (Player) whoClicked, result);
    }

}
