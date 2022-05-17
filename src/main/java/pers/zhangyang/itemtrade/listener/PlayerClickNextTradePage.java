package pers.zhangyang.itemtrade.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import pers.zhangyang.itemtrade.domain.TradeMenu;
import pers.zhangyang.itemtrade.meta.GoodMeta;
import pers.zhangyang.itemtrade.service.CommandService;
import pers.zhangyang.itemtrade.service.impl.CommandServiceImpl;
import pers.zhangyang.itemtrade.util.InvocationUtil;
import pers.zhangyang.itemtrade.util.PageUtil;
import pers.zhangyang.itemtrade.util.ReplaceUtil;
import pers.zhangyang.itemtrade.yaml.GuiYaml;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PlayerClickNextTradePage implements Listener {
    @EventHandler
    public void click(InventoryClickEvent event){
        Inventory inventory=event.getInventory();
        if (!(inventory.getHolder() instanceof TradeMenu)){return;}
        TradeMenu saveMenu = (TradeMenu) inventory.getHolder();
        if (event.getRawSlot()!=53){return;}
        event.setCancelled(true);
        if (!(event.getWhoClicked() instanceof Player)){return;}

        if (event.getCurrentItem()==null){return;}

        Player player= (Player) event.getWhoClicked();
        List<GoodMeta> goodMetaList;
        CommandService commandService;
        int maxPageIndex;
        try {
          commandService= (CommandService) InvocationUtil.getService(new CommandServiceImpl());
            goodMetaList =commandService.getItemMeta(saveMenu.getMenuName(),saveMenu.getPageIndex()+1);
            maxPageIndex=commandService.getMaxPageIndex(saveMenu.getMenuName());
        } catch (SQLException e) {
            e.printStackTrace();
        return;
        }
        int currPage= saveMenu.getPageIndex();
        if (currPage>=maxPageIndex){
          return;
        } HashMap<String,String> rep=new HashMap<>();
        rep.put("{index}",currPage+1+"");
        rep.put("{menu}", saveMenu.getMenuName());
        saveMenu=new TradeMenu(ReplaceUtil.replace(GuiYaml.GUI_MANAGER.getTITLE_TRADE_MENU(), rep), saveMenu.getMenuName());
        saveMenu.init(currPage+1,goodMetaList);
        player.openInventory(saveMenu.getInventory());

    }
}
