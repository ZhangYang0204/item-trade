package pers.zhangyang.itemtrade.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import pers.zhangyang.itemtrade.domain.EditMenu;
import pers.zhangyang.itemtrade.domain.TradeMenu;
import pers.zhangyang.itemtrade.meta.GoodMeta;
import pers.zhangyang.itemtrade.service.CommandService;
import pers.zhangyang.itemtrade.service.impl.CommandServiceImpl;
import pers.zhangyang.itemtrade.util.InvocationUtil;
import pers.zhangyang.itemtrade.util.ItemStackUtil;
import pers.zhangyang.itemtrade.util.ReplaceUtil;
import pers.zhangyang.itemtrade.util.UuidUtil;
import pers.zhangyang.itemtrade.yaml.GuiYaml;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PlayerClickPreviousEditPage implements Listener {
    @EventHandler
    public void click(InventoryClickEvent event){
        Inventory inventory=event.getInventory();
        if (!(inventory.getHolder() instanceof EditMenu)){return;}
        EditMenu saveMenu = (EditMenu) inventory.getHolder();
        if (event.getRawSlot()!=45){return;}
        event.setCancelled(true);
        if (!(event.getWhoClicked() instanceof Player)){return;}

        if (event.getCurrentItem()==null){return;}

        Player player= (Player) event.getWhoClicked();


        int currPage= saveMenu.getPageIndex();
        List<GoodMeta> goodMetaList;

        CommandService commandService;
        try {
            commandService= (CommandService) InvocationUtil.getService(new CommandServiceImpl());
            goodMetaList =commandService.getItemMeta(saveMenu.getMenuName(), saveMenu.getPageIndex()-1);
            commandService.deleteItemMeta(saveMenu.getMenuName(), currPage-1 );
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        if (currPage<=0){
            return;
        }
        HashMap<String,String> rep=new HashMap<>();
        rep.put("{index}",currPage-1+"");
        rep.put("{menu}", saveMenu.getMenuName());
        saveMenu=new EditMenu(ReplaceUtil.replace(GuiYaml.GUI_MANAGER.getTITLE_EDIT_MENU(), rep), saveMenu.getMenuName());
        saveMenu.init(currPage-1, goodMetaList);
        player.openInventory(saveMenu.getInventory());


    }
}
