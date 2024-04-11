package net.zffu.buildtickets.commands;

import net.zffu.buildtickets.config.Messages;
import net.zffu.buildtickets.config.Permissions;
import net.zffu.buildtickets.utils.HeadUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class HeadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;

        if(!Permissions.HEAD_GIVER_USER.hasPermission(player)) {
            player.sendMessage(Messages.NO_PERMISSION.getMessage());
            return false;
        }

        if(args.length == 0) {
            player.sendMessage(Messages.INVALID_USAGE.getMessage());
            return false;
        }

        String headIdentifier = args[0];
        ItemStack head = null;

        // If it's higher than 16 chars it must be a texture url.
        if(headIdentifier.length() > 16) {
            if(!headIdentifier.startsWith("http")) headIdentifier = "http://textures.minecraft.net/texture/" + headIdentifier;
            head = HeadUtils.getReflectiveHeadStack(headIdentifier);
        }
        else {
            head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setOwner(headIdentifier);
            head.setItemMeta(meta);
        }

        player.getInventory().addItem(head);
        return true;
    }
}
