package pers.zhangyang.itemtrade.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import pers.zhangyang.itemtrade.ItemTrade;
import pers.zhangyang.itemtrade.domain.EditMenu;
import pers.zhangyang.itemtrade.domain.TradeMenu;
import pers.zhangyang.itemtrade.meta.GoodMeta;
import pers.zhangyang.itemtrade.service.CommandService;
import pers.zhangyang.itemtrade.service.impl.CommandServiceImpl;
import pers.zhangyang.itemtrade.util.InvocationUtil;
import pers.zhangyang.itemtrade.util.MessageUtil;
import pers.zhangyang.itemtrade.util.ReplaceUtil;
import pers.zhangyang.itemtrade.yaml.GuiYaml;
import pers.zhangyang.itemtrade.yaml.MessageYaml;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class PlayerClickChangeMenuName implements Listener {
    @EventHandler
    public void click(InventoryClickEvent event){
        Inventory inventory=event.getInventory();
        if (!(inventory.getHolder() instanceof EditMenu)){return;}
        EditMenu saveMenu = (EditMenu) inventory.getHolder();
        if (event.getRawSlot()!=49){return;}
        event.setCancelled(true);
        if (!(event.getWhoClicked() instanceof Player)){return;}

        if (event.getCurrentItem()==null){return;}

        Player player= (Player) event.getWhoClicked();

        Bukkit.getPluginManager().registerEvents(new PlayerCreateParty(player,saveMenu), ItemTrade.getInstance());

        player.closeInventory();
        List<String> list = MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_HOW_TO_CHANGE_MENU_NAME();
        MessageUtil.sendMessageTo(player, list);

    }
}