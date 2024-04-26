package net.zffu.buildtickets.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    private ItemStack itemStack;
    private ItemMeta meta;
    private List<String> lore = new ArrayList<>();

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.meta = itemStack.getItemMeta();
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.meta = itemStack.getItemMeta();
    }

    public static ItemBuilder create(Material material) {
        return new ItemBuilder(material);
    }

    public ItemBuilder display(String displayName) {
        this.meta.setDisplayName(displayName);
        return this;
    }

    public ItemBuilder lore(String... lines) {
        this.lore.addAll(Arrays.asList(lines));
        return this;
    }

    public ItemStack build() {
        this.meta.setLore(this.lore);
        this.itemStack.setItemMeta(meta);
        return this.itemStack;
    }

}
