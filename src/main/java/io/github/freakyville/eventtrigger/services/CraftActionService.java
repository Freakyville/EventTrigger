package io.github.freakyville.eventtrigger.services;

import io.github.freakyville.eventtrigger.models.CraftEventModel;
import io.github.freakyville.eventtrigger.settings.Settings;
import io.github.freakyville.pmp.api.CPCAPI;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CraftActionService {
    private final Settings settings;
    private final CPCAPI cpcApi;

    public CraftActionService(Settings settings, CPCAPI cpcApi) {
        this.settings = settings;
        this.cpcApi = cpcApi;
    }

    public void executeCraftTrigger(String eventName, Player player, ItemStack item) {
        CraftEventModel craftSettings = settings.getCraftSettings(eventName);
        boolean enabled = craftSettings.isEnabled();
        if (!enabled) {
            return;
        }
        String itemName = item.getType().name();
        boolean isOkayEntity = craftSettings.getItems().contains(itemName);
        if (!isOkayEntity) {
            return;
        }
        cpcApi.addToPlaceholder(player.getUniqueId(), craftSettings.getName() + "_" + itemName, 1);
    }
}
