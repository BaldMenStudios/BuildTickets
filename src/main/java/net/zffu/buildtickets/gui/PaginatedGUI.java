package net.zffu.buildtickets.gui;

import dev.triumphteam.gui.guis.GuiItem;
import net.zffu.buildtickets.config.Messages;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public abstract class PaginatedGUI extends AbstractGUI {

    public static ItemStack BACK = null;
    public static ItemStack GO_BACK = null;
    public static ItemStack GO_NEXT = null;
    protected int page;
    protected int elementsPerPage;
    protected int startingIndex;
    public PaginatedGUI(String inventoryName, int page, int elementsPerPage) {
        super(inventoryName);
        this.page = page;
        this.elementsPerPage = elementsPerPage;
        this.startingIndex = elementsPerPage * page;
    }

    @Override
    public void initItems() {
        List<ItemStack> stacks = getStacks();
        for(int i = startingIndex; i < startingIndex + elementsPerPage; i++) {
            if(stacks.size() <= i) return;
            this.gui.setItem(i - startingIndex, new GuiItem(getStacks().get(i)));
        }
    }

    public abstract List<ItemStack> getStacks();

    static {
        BACK = new ItemStack(Material.ARROW);
        ItemMeta meta = BACK.getItemMeta();
        meta.setDisplayName("§aGo Back");
        meta.setLore(Arrays.asList("§7Click here to go back!"));
        BACK.setItemMeta(meta);

        GO_BACK = new ItemStack(Material.ARROW);
        meta = GO_BACK.getItemMeta();
        meta.setDisplayName("§aPrevious Page");
        meta.setLore(Arrays.asList("§7Click here to go back to the previous page!"));
        GO_BACK.setItemMeta(meta);

        GO_NEXT = new ItemStack(Material.ARROW);
        meta = GO_NEXT.getItemMeta();
        meta.setDisplayName("§aNext Page");
        meta.setLore(Arrays.asList("§7Click here to go back to the next page!"));
        GO_NEXT.setItemMeta(meta);
    }

    public void goBack(HumanEntity player) {
        if(this.page <= 0) {
            player.sendMessage(Messages.PAGE_ALREADY_FIRST_PAGE.getMessage());
            return;
        }
        this.page--;
        this.startingIndex = elementsPerPage * page;
        this.open(player);
    }

    public void goNext(HumanEntity player) {
        if(this.getStacks().size() <= (this.page + 1) * elementsPerPage) {
            player.sendMessage(Messages.PAGE_ALREADY_LAST_PAGE.getMessage());
            return;
        }
        this.page++;
        this.startingIndex = elementsPerPage * page;
        this.open(player);
    }

}
