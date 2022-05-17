package pers.zhangyang.itemtrade.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pers.zhangyang.itemtrade.domain.EditMenu;
import pers.zhangyang.itemtrade.meta.GoodMeta;
import pers.zhangyang.itemtrade.service.CommandService;
import pers.zhangyang.itemtrade.service.impl.CommandServiceImpl;
import pers.zhangyang.itemtrade.util.InvocationUtil;
import pers.zhangyang.itemtrade.util.ItemStackUtil;
import pers.zhangyang.itemtrade.util.RefreshUtil;
import pers.zhangyang.itemtrade.util.UuidUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerCloseEditMenu implements Listener {
    @EventHandler
    public void close(InventoryCloseEvent event){
        Inventory inventory=event.getView().getTopInventory();
        if (!(inventory.getHolder() instanceof EditMenu)){return;}
        EditMenu editMenu = (EditMenu) inventory.getHolder();
        if (!(event.getPlayer() instanceof Player)){return;}

        Player player= (Player) event.getPlayer();


        List<GoodMeta> goodMetaList=new ArrayList<>();
        for (int i=0;i<9;i++){
            ItemStack itemStack=inventory.getItem(i);
            if (itemStack==null){continue;}
            GoodMeta goodMeta=new GoodMeta();
            goodMeta.setUuid(UuidUtil.getUUID());
            goodMeta.setData(ItemStackUtil.itemStackSerialize(itemStack));
            goodMeta.setMenuName(editMenu.getMenuName());
            goodMeta.setPageIndex(editMenu.getPageIndex());
            goodMeta.setSlot(i);


            if (inventory.getItem(i+2*9) !=null) {
            goodMeta.setTwoData(ItemStackUtil.itemStackSerialize(inventory.getItem(i+9*2)));
            }
            if (inventory.getItem(i+3*9) !=null) {
            goodMeta.setThreeData(ItemStackUtil.itemStackSerialize(inventory.getItem(i+9*3)));
            }
            if (inventory.getItem(i+4*9) !=null) {
            goodMeta.setFourData(ItemStackUtil.itemStackSerialize(inventory.getItem(i+9*4)));
            }
            goodMetaList.add(goodMeta);
        }

        //插入
        try {
            CommandService commandService= (CommandService) InvocationUtil.getService(new CommandServiceImpl());
            commandService.deleteItemMeta(editMenu.getMenuName(),editMenu.getPageIndex());
            commandService.insert(goodMetaList);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        RefreshUtil.refreshTradeMenu(null);



    }
}
