package pers.zhangyang.itemtrade.listener;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pers.zhangyang.itemtrade.ItemTrade;
import pers.zhangyang.itemtrade.domain.TradeMenu;
import pers.zhangyang.itemtrade.meta.GoodMeta;
import pers.zhangyang.itemtrade.util.InventoryUtil;
import pers.zhangyang.itemtrade.util.ItemStackUtil;
import pers.zhangyang.itemtrade.util.MessageUtil;
import pers.zhangyang.itemtrade.util.ReplaceUtil;
import pers.zhangyang.itemtrade.yaml.MessageYaml;

import java.util.ArrayList;
import java.util.List;

public class PlayerClickTrade implements Listener {
    @EventHandler
    public void click(InventoryClickEvent event){
        Inventory inventory=event.getInventory();
        if (!(inventory.getHolder() instanceof TradeMenu)){return;}
        TradeMenu saveMenu = (TradeMenu) inventory.getHolder();
        if (event.getRawSlot()<0||event.getRawSlot()>=9){return;}
        event.setCancelled(true);
        if (!(event.getWhoClicked() instanceof Player)){return;}

        if (event.getCurrentItem()==null){return;}

        if (!event.getClick().isRightClick()){
            return;
        }

        Player player= (Player) event.getWhoClicked();

        GoodMeta goodMeta=null;
        for (GoodMeta g:saveMenu.getGoodMetaList()){
            if (g.getSlot()==event.getRawSlot()){
                goodMeta=g;
                break;
            }
        }

        Bukkit.getPluginManager().registerEvents( new InputAfterClickTrade(player,saveMenu,goodMeta), ItemTrade.getInstance());
        player.closeInventory();
        List<String> list = MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_HOW_TO_TRADE();
        MessageUtil.sendMessageTo(player, list);
    }
}
