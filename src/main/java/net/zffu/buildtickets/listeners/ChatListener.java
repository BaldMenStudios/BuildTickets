package net.zffu.buildtickets.listeners;

import net.zffu.buildtickets.BuildTicketsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if(BuildTicketsPlugin.getInstance().getChatHandlers().containsKey(event.getPlayer().getUniqueId())) {
            BuildTicketsPlugin.getInstance().getChatHandlers().get(event.getPlayer().getUniqueId()).execute(event);
            BuildTicketsPlugin.getInstance().getChatHandlers().remove(event.getPlayer().getUniqueId());
        }
    }

}
