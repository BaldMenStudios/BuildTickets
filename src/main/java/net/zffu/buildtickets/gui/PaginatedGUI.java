package net.zffu.buildtickets.gui;

import dev.triumphteam.gui.guis.GuiItem;
import net.zffu.buildtickets.locale.LocaleManager;
import net.zffu.buildtickets.locale.LocaleString;
import net.zffu.buildtickets.utils.Bundle;
import net.zffu.buildtickets.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class PaginatedGUI<T extends ItemConvertible> extends AbstractGUI {

    public static ItemStack BACK = null;
    public static ItemStack GO_BACK = null;
    public static ItemStack GO_NEXT = null;
    protected int page;
    protected int elementsPerPage;
    protected int startingIndex;
    protected int startingSlotIndex = 0;
    protected int elementsPerLine;

    protected Bundle<String, Comparator<T>>[] sortingOptions = null;
    protected int sortingSlot = 48;
    private int selectedFilter = -1;

    public PaginatedGUI(String inventoryName, int page, int elementsPerPage) {
        super(inventoryName);
        this.page = page;
        this.elementsPerPage = elementsPerPage;
        this.startingIndex = elementsPerPage * page;
    }

    @Override
    public void initItems() {
        List<ItemStack> stacks = getItems();
        int rowIndex = 0;
        for(int i = startingIndex; i < startingIndex + elementsPerPage; i++) {
            if(stacks.size() <= i) return;
            rowIndex++;
            if(rowIndex >= elementsPerLine && elementsPerLine > 0) {
                rowIndex = 0;
                i += (9 - elementsPerLine);
            }
            this.gui.setItem(i - startingIndex, new GuiItem(stacks.get(i)));
        }

        if(this.sortingOptions.length != 0) {
            ItemBuilder builder = ItemBuilder.create(Material.ANVIL);
            builder.display("§aSorting");
            for(int i = 0; i < sortingOptions.length; i++) {
                builder.lore((selectedFilter == i) ? "§2► " + sortingOptions[i].getFirst() : "  §7" + sortingOptions[i].getFirst());
            }

            builder.lore("§7", "§eLeft-Click to go forward", "§eRight-Click to go backwards", "§eShift-Click to remove filter");

            this.gui.setItem(sortingSlot, new GuiItem(builder.build()));

            setAction(sortingSlot, (event -> {
                event.setCancelled(true);

                if(event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT) {
                    this.selectedFilter = -1;
                    return;
                }

                if(event.getClick() == ClickType.LEFT) {
                    if(this.selectedFilter == -1 || (this.selectedFilter + 1) >= (this.sortingOptions.length)) {
                        this.selectedFilter = 0;
                    }
                    else {
                        this.selectedFilter++;
                    }
                }

                if(event.getClick() == ClickType.RIGHT) {
                    this.selectedFilter--;
                    if(this.selectedFilter == -1) {
                        this.selectedFilter = this.sortingOptions.length - 1;
                    }
                }

                this.initItems();
                this.gui.update();
            }));
        }
    }

    /**
     * Returns the elements to display in the Paginated GUIS. T must be a {@link ItemConvertible}
     * @return
     */
    public abstract List<T> getElements();

    /**
     * Parses the elements into items.
     * @return
     */
    private List<ItemStack> getItems() {
        List<T> elems = new ArrayList<>();
        if(selectedFilter != -1) {
            elems.sort(sortingOptions[selectedFilter].getSecond());
        }
        return elems.stream().map(element -> element.toItemStack()).collect(Collectors.toList());
    }

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
            player.sendMessage(LocaleManager.getMessage(LocaleString.PAGE_ALREADY_FIRST, player));
            return;
        }
        this.page--;
        this.startingIndex = elementsPerPage * page;
        this.open(player);
    }

    public void goNext(HumanEntity player) {
        if(this.getStacks().size() <= (this.page + 1) * elementsPerPage) {
            player.sendMessage(LocaleManager.getMessage(LocaleString.PAGE_ALREADY_LAST, player));
            return;
        }
        this.page++;
        this.startingIndex = elementsPerPage * page;
        this.open(player);
    }

}
