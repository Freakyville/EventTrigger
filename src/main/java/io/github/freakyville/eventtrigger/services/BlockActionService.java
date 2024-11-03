package io.github.freakyville.eventtrigger.services;

import io.github.freakyville.eventtrigger.models.BlockEventModel;
import io.github.freakyville.eventtrigger.settings.Settings;
import io.github.freakyville.pmp.api.CPCAPI;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;

import java.util.List;

public class BlockActionService {
    private final Settings settings;
    private final CPCAPI cpcApi;

    public BlockActionService(Settings settings, CPCAPI cpcApi) {
        this.settings = settings;
        this.cpcApi = cpcApi;
    }

    public void executeBlockTrigger(String event, Player player, Block block) {
        BlockEventModel eventSetting = settings.getBlockSettings(event);
        boolean enabled = eventSetting.isEnabled();
        if (!enabled) {
            return;
        }
        boolean isOkayBlock = isOkayBlock(block, eventSetting);
        if (!isOkayBlock) {
            return;
        }

        String name = getName(block);

        String placeholder = eventSetting.getName() + "-" + name;
        cpcApi.addToPlaceholder(player.getUniqueId(), placeholder.replace("_", "-"), 1);
    }

    private String getName(Block block) {
        switch (block.getType()) {
            case CARROT:
                if (isMaxAge(block)) return "GROWN_CARROT";
                break;
            case POTATO:
                if (isMaxAge(block)) return "GROWN_POTATO";
                break;
            case BEETROOT:
                if (isMaxAge(block)) return "GROWN_BEETROOT";
                break;
            case NETHER_WART:
                if (isMaxAge(block)) return "GROWN_NETHER_WART";
                break;
            case COCOA:
                if (isMaxAge(block)) return "GROWN_COCOA";
                break;
            case PUMPKIN:
                if (isMaxAge(block)) return "GROWN_PUMPKIN";
                break;
            case MELON:
                if (isMaxAge(block)) return "GROWN_MELON";
                break;
            case WHEAT:
                if (isMaxAge(block)) return "GROWN_WHEAT";
                break;
            default:
                break;
        }
        return block.getType().name();
    }

    private boolean isMaxAge(Block block) {
        Ageable ageable = (Ageable) block.getBlockData();
        int age = ageable.getAge();
        return age == ageable.getMaximumAge();
    }

    private boolean isOkayBlock(Block block, BlockEventModel eventSetting) {
        List<String> blocks = eventSetting.getBlocks();
        if (!blocks.contains(getName(block))) return false;
        if (block.getType().equals(Material.WHEAT) && blocks.contains("GROWN_WHEAT")) { // Wheat growth stage
            Ageable ageable = (Ageable) block.getBlockData();
            int age = ageable.getAge();
            if (age != ageable.getMaximumAge()) return false;
        }
        return true;
    }

}
