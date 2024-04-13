package net.zffu.buildtickets.commands;

import net.zffu.buildtickets.config.Messages;
import net.zffu.buildtickets.config.Permissions;
import net.zffu.buildtickets.locale.LocaleManager;
import net.zffu.buildtickets.locale.LocaleString;
import net.zffu.buildtickets.utils.HeadUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class HeadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;

        if(!Permissions.HEAD_GIVER_USER.hasPermission(player)) {
            player.sendMessage(LocaleManager.getMessage(LocaleString.PERMISSION_NOT_MET, player));
            return false;
        }

        if(args.length == 0) {
            player.sendMessage(LocaleManager.getMessage(LocaleString.USAGE_MISSING_ARGS, player));
            return false;
        }

        ItemStack head = null;

        switch (args[0]) {
            case "url":
                if(args.length != 2) {
                    player.sendMessage(LocaleManager.getMessage(LocaleString.USAGE_MISSING_ARGS, player));
                    return false;
                }
                String textureURL = args[1];
                if(!textureURL.startsWith("http")) textureURL = "http://textures.minecraft.net/texture/" + textureURL;
                head = HeadUtils.getReflectiveHeadStack(textureURL);
                break;
            case "name":
                if(args.length != 2) {
                    player.sendMessage(LocaleManager.getMessage(LocaleString.USAGE_MISSING_ARGS, player));
                    return false;
                }
                head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) head.getItemMeta();
                meta.setOwner(args[0]);
                head.setItemMeta(meta);
                break;
            default:
                player.sendMessage("§c/head url <textureUrl> §fGives you the head with the texture url");
                player.sendMessage("§c/head name <playerName> §fGives you the head with the player name");
                return false;
        }

        player.getInventory().addItem(head);
        return true;
    }
}
