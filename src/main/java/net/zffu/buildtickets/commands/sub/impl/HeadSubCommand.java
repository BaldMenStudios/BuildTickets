package net.zffu.buildtickets.commands.sub.impl;

import net.zffu.buildtickets.commands.sub.SubCommand;
import net.zffu.buildtickets.config.Permissions;
import net.zffu.buildtickets.locale.LocaleManager;
import net.zffu.buildtickets.locale.LocaleString;
import net.zffu.buildtickets.utils.HeadUtils;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadSubCommand extends SubCommand {
    public HeadSubCommand() {
        super("head", "Head Giver", Permissions.HEAD_GIVER_USER.getPermission());
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length == 0) {
            player.sendMessage(LocaleManager.getMessage(LocaleString.USAGE_MISSING_ARGS, player));
            return false;
        }

        ItemStack head = null;

        if((args[0].equals("url") || args[0].equals("name")) && args.length != 2) {
            player.sendMessage(LocaleManager.getMessage(LocaleString.USAGE_MISSING_ARGS, player));
            return false;
        }

        switch (args[0]) {
            case "url":
                String textureURL = args[1];
                if(!textureURL.startsWith("http")) textureURL = "http://textures.minecraft.net/texture/" + textureURL;
                head = HeadUtils.getReflectiveHeadStack(textureURL);
                break;
            case "name":
                head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) head.getItemMeta();
                meta.setOwner(args[1]);
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
