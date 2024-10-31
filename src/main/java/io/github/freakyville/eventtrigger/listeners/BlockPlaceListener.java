package io.github.freakyville.eventtrigger.listeners;

import io.github.freakyville.eventtrigger.services.BlockActionService;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
    private final BlockActionService blockActionService;

    public BlockPlaceListener(BlockActionService blockActionService) {
        this.blockActionService = blockActionService;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block blockPlaced = event.getBlockPlaced();
        blockActionService.executeBlockTrigger(event.getEventName(), player, blockPlaced);
    }

}
