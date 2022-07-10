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
import pers.zhangyang.itemtrade.yaml.MessageYaml;

import java.util.ArrayList;
import java.util.List;

public class PlayerLeftClickTrade implements Listener {
    @EventHandler
    public void click(InventoryClickEvent event){
        Inventory inventory=event.getInventory();
        if (!(inventory.getHolder() instanceof TradeMenu)){return;}
        TradeMenu saveMenu = (TradeMenu) inventory.getHolder();
        if (event.getRawSlot()<0||event.getRawSlot()>=9){return;}
        event.setCancelled(true);
        if (!(event.getWhoClicked() instanceof Player)){return;}

        if (event.getCurrentItem()==null){return;}

        if (!event.getClick().isLeftClick()){
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
        ItemStack dataS = ItemStackUtil.itemStackDeserialize(goodMeta.getData());

        ItemStack tw=null;ItemStack three=null;ItemStack f=null;
        if (goodMeta.getTwoData()!=null) {
            tw=ItemStackUtil.itemStackDeserialize(goodMeta.getTwoData());
        }
        if (goodMeta.getThreeData()!=null) {
            three=ItemStackUtil.itemStackDeserialize(goodMeta.getThreeData());
        }
        if (goodMeta.getFourData()!=null) {
            f = ItemStackUtil.itemStackDeserialize(goodMeta.getFourData());
        }

        List<ItemStack> itl=new ArrayList<>();
        itl.add(tw);
        itl.add(three);
        itl.add(f);
        if(InventoryUtil.contains(itl,player.getInventory(),player)){
            if (tw!=null) {
                InventoryUtil.removeItem(player.getInventory(),tw,tw.getAmount());
            }if (three!=null) {
                InventoryUtil.removeItem(player.getInventory(),three,three.getAmount());
            }if (f!=null) {
                InventoryUtil.removeItem(player.getInventory(), f, f.getAmount());
            }
            for (ItemStack res:player.getInventory().addItem(dataS).values()){
                World w= player.getWorld();
                w.dropItem(player.getLocation(),res);
            }
            List<String> list = MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_SUCCESS_TRADE();
            MessageUtil.sendMessageTo(player, list);
        }else {
        }

    }
}