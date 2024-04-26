package net.zffu.buildtickets.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.zffu.buildtickets.wrappers.WrappedMaterials;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.UUID;

public class HeadUtils {

    private static Field profileField;
    private static Method setOwningPlayer;

    public HeadUtils() {
        try {
            if(WrappedMaterials.old) {
                setOwningPlayer = SkullMeta.class.getMethod("setOwner", String.class);
            }
            else {
                setOwningPlayer = SkullMeta.class.getMethod("setOwningPlayer", OfflinePlayer.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ItemStack getHeadStack(UUID uuid) {
        ItemStack stack = new ItemStack(WrappedMaterials.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();
        try {
            setOwningPlayer.invoke(meta, (WrappedMaterials.old) ? Bukkit.getOfflinePlayer(uuid).getPlayer().getName() :Bukkit.getOfflinePlayer(uuid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        stack.setItemMeta(meta);
        return stack;
    }

    public static ItemStack getReflectiveHeadStack(String textureURL) {
        ItemStack stack = new ItemStack(WrappedMaterials.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        byte[] data = Base64.getEncoder().encode(String.format(
                "{textures:{SKIN:{url:\"%s\"}}}", textureURL).getBytes());

        profile.getProperties().put("textures", new Property("textures", new String(data)));

        try {
            if(profileField == null) {
                profileField = meta.getClass().getDeclaredField("profile");
            }

            profileField.setAccessible(true);
            profileField.set(meta, profile);

            stack.setItemMeta(meta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stack;
    }
}
