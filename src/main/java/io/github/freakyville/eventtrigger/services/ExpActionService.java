package io.github.freakyville.eventtrigger.services;

import io.github.freakyville.eventtrigger.models.ExpEventModel;
import io.github.freakyville.eventtrigger.settings.Settings;
import io.github.freakyville.pmp.api.CPCAPI;
import org.bukkit.entity.Player;

public class ExpActionService {
    private final Settings settings;
    private final CPCAPI cpcapi;

    public ExpActionService(Settings settings, CPCAPI cpcapi) {
        this.settings = settings;
        this.cpcapi = cpcapi;
    }

    public void executeExpTrigger(String eventName, Player player, int amountReceived) {
        ExpEventModel expEventModel = settings.getExpSettings(eventName);
        boolean enabled = expEventModel.isEnabled();
        if (!enabled) {
            return;
        }
        int expRequiredToNextLevel = player.getExpToLevel();
        if (amountReceived < expRequiredToNextLevel) {
            return;
        }
        cpcapi.addToPlaceholder(player.getUniqueId(), expEventModel.getName(), 1);
    }

}
