package io.github.freakyville.eventtrigger.listeners;

import io.github.freakyville.eventtrigger.services.BlockActionService;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    private final BlockActionService blockActionService;

    public BlockBreakListener(BlockActionService blockActionService) {
        this.blockActionService = blockActionService;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        blockActionService.executeBlockTrigger(event.getEventName(), player, block);
    }

}
