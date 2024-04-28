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
        super("head", "Head Giver", Permissions.HEAD_GIVER_USER.getPermission(), new String[] {"help", "url", "name"});
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length == 1) {
            player.sendMessage(LocaleManager.getMessage(LocaleString.USAGE_MISSING_ARGS, player));
            return false;
        }

        ItemStack head = null;

        if((args[1].equals("url") || args[1].equals("name")) && args.length != 3) {
            player.sendMessage(LocaleManager.getMessage(LocaleString.USAGE_MISSING_ARGS, player));
            return false;
        }

        switch (args[1]) {
            case "url":
                String textureURL = args[2];
                if(!textureURL.startsWith("http")) textureURL = "http://textures.minecraft.net/texture/" + textureURL;
                head = HeadUtils.getReflectiveHeadStack(textureURL);
                break;
            case "name":
                head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) head.getItemMeta();
                meta.setOwner(args[2]);
                head.setItemMeta(meta);
                break;
            default:
                player.sendMessage("§c/bt head url <textureUrl> §fGives you the head with the texture url");
                player.sendMessage("§c/bt head name <playerName> §fGives you the head with the player name");
                return false;
        }

        player.getInventory().addItem(head);
        return true;
    }
}
