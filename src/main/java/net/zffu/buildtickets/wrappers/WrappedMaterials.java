package net.zffu.buildtickets.wrappers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Weird way to handle materials but it works soo.
 */
public class WrappedMaterials {

    public static ItemStack DYE_GREEN;
    public static ItemStack DYE_LIME;
    public static ItemStack DYE_YELLOW;
    public static ItemStack DYE_ORANGE;
    public static ItemStack DYE_RED;
    public static ItemStack LIME_WOOL;
    public static ItemStack YELLOW_WOOL;
    public static ItemStack COMPARATOR;
    public static ItemStack PLAYER_HEAD;

    public static boolean old;

    public WrappedMaterials() {
        try {
            Class.forName("org.bukkit.entity.Pillager");
            DYE_RED = new ItemStack(Material.valueOf("RED_DYE"));
            DYE_LIME = new ItemStack(Material.valueOf("LIME_DYE"));
            DYE_YELLOW = new ItemStack(Material.valueOf("YELLOW_DYE"));
            DYE_ORANGE = new ItemStack(Material.valueOf("ORANGE_DYE"));
            DYE_GREEN = new ItemStack(Material.valueOf("GREEN_DYE"));
            LIME_WOOL = new ItemStack(Material.valueOf("LIME_WOOL"));
            YELLOW_WOOL = new ItemStack(Material.valueOf("YELLOW_WOOL"));
            COMPARATOR = new ItemStack(Material.valueOf("COMPARATOR"));
            PLAYER_HEAD = new ItemStack(Material.valueOf("PLAYER_HEAD"));
        } catch (ClassNotFoundException e) {
            old = true;
            DYE_RED = new ItemStack(Material.INK_SACK,1,(short)1);
            DYE_ORANGE = new ItemStack(Material.INK_SACK, 1,(short)14);
            DYE_YELLOW = new ItemStack(Material.INK_SACK, 1,(short)11);
            DYE_LIME = new ItemStack(Material.INK_SACK, 1,(short)10);
            DYE_GREEN = new ItemStack(Material.INK_SACK, 1,(short)2);
            LIME_WOOL = new ItemStack(Material.WOOL, 1, (short)10);
            YELLOW_WOOL = new ItemStack(Material.WOOL, 1, (short)11);
            COMPARATOR = new ItemStack(Material.REDSTONE_COMPARATOR);
            PLAYER_HEAD = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        }
    }

}
