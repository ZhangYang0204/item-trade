package pers.zhangyang.itemtrade.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import pers.zhangyang.itemtrade.domain.EditMenu;
import pers.zhangyang.itemtrade.domain.TradeMenu;

public class PlayerClickMaterial implements Listener {
    @EventHandler
    public void click(InventoryClickEvent event){
        Inventory inventory=event.getInventory();
        if (!(inventory.getHolder() instanceof TradeMenu)){return;}
        if (event.getRawSlot()<9||event.getRawSlot()>44){return;}
        event.setCancelled(true);
    }
}