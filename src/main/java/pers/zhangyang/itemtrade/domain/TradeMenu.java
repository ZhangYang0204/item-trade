package pers.zhangyang.itemtrade.domain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.zhangyang.itemtrade.meta.GoodMeta;
import pers.zhangyang.itemtrade.util.ItemStackUtil;
import pers.zhangyang.itemtrade.util.ReplaceUtil;
import pers.zhangyang.itemtrade.yaml.GuiYaml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TradeMenu implements InventoryHolder {
    private Inventory inventory;
    private int pageIndex;
    private final List<GoodMeta> goodMetaList;

    public List<GoodMeta> getGoodMetaList() {
        return new ArrayList<>(goodMetaList);
    }

    public String getMenuName() {
        return menuName;
    }

    private final String menuName;

    public TradeMenu(String title, String menuName) {
        this.menuName=menuName;
        if (title == null) {
            this.inventory = Bukkit.createInventory(this, 54);
        } else {
            this.inventory = Bukkit.createInventory(this, 54, ChatColor.translateAlternateColorCodes('&',title));
        }
        this.goodMetaList = new ArrayList<>();
        this.pageIndex = 0;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void init(int pageIndex, List<GoodMeta> goodMetaList) {
        inventory.clear();
        this.goodMetaList.clear();
        for (GoodMeta im : goodMetaList) {this.goodMetaList.add(im.clone());}
        this.pageIndex=pageIndex;

        for (int i=0;i<goodMetaList.size();i++){
            GoodMeta g=goodMetaList.get(i);
            ItemStack dataStack= ItemStackUtil.itemStackDeserialize(g.getData());
            inventory.setItem(g.getSlot(),dataStack);
            if (g.getTwoData()!=null) {
                ItemStack tw = ItemStackUtil.itemStackDeserialize(g.getTwoData());
                inventory.setItem(g.getSlot() + 9 * 2, tw);
            }
            if (g.getThreeData()!=null) {
                ItemStack three= ItemStackUtil.itemStackDeserialize(g.getThreeData());
                inventory.setItem(g.getSlot()+9*3,three);
            }

            if (g.getFourData()!=null) {
                ItemStack f = ItemStackUtil.itemStackDeserialize(g.getFourData());
                inventory.setItem(g.getSlot()+9*4,f);
            }
        }

        for (int i=9;i<18;i++){
            String materialName=GuiYaml.GUI_MANAGER.getBUTTON_TRADE_MENU_GLASS_MATERIAL();
            String displayName=GuiYaml.getGuiManager().
                    getBUTTON_TRADE_MENU_GLASS_DISPLAY_NAME();

            List<String> list= GuiYaml.GUI_MANAGER.getBUTTON_TRADE_MENU_GLASS_LORE();
            ItemStack nextPage = ItemStackUtil.getItemStack(materialName,displayName,list);

            ItemMeta meta=nextPage.getItemMeta();
            meta.setCustomModelData(GuiYaml.GUI_MANAGER.getBUTTON_TRADE_MENU_GLASS_CUSTOM_MODEL_DATA());
            nextPage.setItemMeta(meta);

            inventory.setItem(i, nextPage);
        }



        String materialName=GuiYaml.GUI_MANAGER.getBUTTON_TRADE_MENU_NEXT_TRADE_PAGE_MATERIAL();
        String displayName=GuiYaml.getGuiManager().
                getBUTTON_TRADE_MENU_NEXT_TRADE_PAGE_DISPLAY_NAME();

        List<String> list= GuiYaml.GUI_MANAGER.getBUTTON_TRADE_MENU_NEXT_TRADE_PAGE_LORE();
        displayName=ReplaceUtil.replace(displayName,Collections.singletonMap("{next}",(pageIndex+1)+""));
        ReplaceUtil.replace(list,Collections.singletonMap("{next}",(pageIndex+1)+""));
        ItemStack nextPage = ItemStackUtil.getItemStack(materialName,displayName,list);

        ItemMeta meta=nextPage.getItemMeta();
        meta.setCustomModelData(GuiYaml.GUI_MANAGER.getBUTTON_TRADE_MENU_NEXT_TRADE_PAGE_CUSTOM_MODEL_DATA());
        nextPage.setItemMeta(meta);

        displayName=GuiYaml.getGuiManager().getBUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_DISPLAY_NAME();
        list= GuiYaml.GUI_MANAGER.GUI_MANAGER.getBUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_LORE();

        displayName=ReplaceUtil.replace(displayName,Collections.singletonMap("{pre}",(pageIndex-1)+""));
        ReplaceUtil.replace(list,Collections.singletonMap("{pre}",(pageIndex-1)+""));
        ItemStack previousPage =ItemStackUtil.getItemStack(
                GuiYaml.getGuiManager().GUI_MANAGER.getBUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_MATERIAL(),displayName,list);

         meta=previousPage.getItemMeta();
        meta.setCustomModelData(GuiYaml.GUI_MANAGER.getBUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_CUSTOM_MODEL_DATA());
        previousPage.setItemMeta(meta);

        inventory.setItem(53, nextPage);
        inventory.setItem(45, previousPage);



    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
