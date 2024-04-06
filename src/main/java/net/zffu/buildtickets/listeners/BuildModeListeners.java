package net.zffu.buildtickets.listeners;

import net.zffu.buildtickets.BuildTicketsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BuildModeListeners implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(!BuildTicketsPlugin.getInstance().getBuildMode().contains(event.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(!BuildTicketsPlugin.getInstance().getBuildMode().contains(event.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event) {
        if(event.getClickedBlock() == null) return;
        event.setCancelled(!BuildTicketsPlugin.getInstance().getBuildMode().contains(event.getPlayer().getUniqueId()));
    }

}
