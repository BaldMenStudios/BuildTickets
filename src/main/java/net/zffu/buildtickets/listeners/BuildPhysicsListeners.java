package net.zffu.buildtickets.listeners;

import net.zffu.buildtickets.BuildTicketsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockSpreadEvent;

public class BuildPhysicsListeners implements Listener {

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
        event.setCancelled(!BuildTicketsPlugin.getInstance().isBuildPhysics());
    }

    @EventHandler
    public void onBlockSpread(BlockSpreadEvent event) {
        event.setCancelled(!BuildTicketsPlugin.getInstance().isBuildPhysics());
    }

}
