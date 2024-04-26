package net.zffu.buildtickets.wrappers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WrappedMaterials {

    public static ItemStack DYE_GREEN;
    public static ItemStack DYE_LIME;
    public static ItemStack DYE_YELLOW;
    public static ItemStack DYE_ORANGE;
    public static ItemStack DYE_RED;

    public WrappedMaterials() {
        try {
            Class.forName("org.bukkit.entity.Pillager");
            DYE_RED = new ItemStack(Material.valueOf("RED_DYE"));
            DYE_LIME = new ItemStack(Material.valueOf("LIME_DYE"));
            DYE_YELLOW = new ItemStack(Material.valueOf("YELLOW_DYE"));
            DYE_ORANGE = new ItemStack(Material.valueOf("ORANGE_DYE"));
            DYE_GREEN = new ItemStack(Material.valueOf("GREEN_DYE"));
        } catch (ClassNotFoundException e) {
            DYE_RED = new ItemStack(Material.INK_SACK,1,(short)1);
            DYE_ORANGE = new ItemStack(Material.INK_SACK, 1,(short)14);
            DYE_YELLOW = new ItemStack(Material.INK_SACK, 1,(short)11);
            DYE_LIME = new ItemStack(Material.INK_SACK, 1,(short)10);
            DYE_GREEN = new ItemStack(Material.INK_SACK, 1,(short)2);
        }
    }

}
