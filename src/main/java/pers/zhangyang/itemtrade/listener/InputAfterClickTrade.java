package pers.zhangyang.itemtrade.listener;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import pers.zhangyang.itemtrade.domain.EditMenu;
import pers.zhangyang.itemtrade.domain.TradeMenu;
import pers.zhangyang.itemtrade.meta.GoodMeta;
import pers.zhangyang.itemtrade.service.CommandService;
import pers.zhangyang.itemtrade.service.impl.CommandServiceImpl;
import pers.zhangyang.itemtrade.util.*;
import pers.zhangyang.itemtrade.yaml.GuiYaml;
import pers.zhangyang.itemtrade.yaml.MessageYaml;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InputAfterClickTrade implements Listener {
    private Player player;
    private TradeMenu tradeMenu;
    private GoodMeta goodMeta;

    public InputAfterClickTrade(Player player,TradeMenu tradeMenu,GoodMeta goodMeta) {
        this.player = player;
        this.tradeMenu = tradeMenu;
        this.goodMeta=goodMeta;
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event){

        Player player=event.getPlayer();
        if (!player.equals(this.player)){return;}
        event.setCancelled(true);
        CommandService commandService;

        String message=event.getMessage();

        int amount;

        try {
            amount=Integer.parseInt(message);
        }catch (NumberFormatException e){
            List<String> list = MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_HOW_TO_TRADE();
            MessageUtil.sendMessageTo(player, list);
            return;
        }

        if (message.equalsIgnoreCase("cancel")){
            unregisterSelf();
            backGui();
            return;
        }


        ItemStack dataS = ItemStackUtil.itemStackDeserialize(goodMeta.getData());

        ItemStack tw=null;ItemStack three=null;ItemStack f=null;
        if (goodMeta.getTwoData()!=null) {
            tw=ItemStackUtil.itemStackDeserialize(goodMeta.getTwoData());
            tw.setAmount(amount);
            System.out.println(tw.getAmount());
        }
        if (goodMeta.getThreeData()!=null) {
            three=ItemStackUtil.itemStackDeserialize(goodMeta.getThreeData());
            three.setAmount(amount);
        }
        if (goodMeta.getFourData()!=null) {
            f = ItemStackUtil.itemStackDeserialize(goodMeta.getFourData());
            f.setAmount(amount);
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
            ItemStack itemStack222=dataS.clone();
            itemStack222.setAmount(amount);
            for (ItemStack res:player.getInventory().addItem(itemStack222).values()){
                World w= player.getWorld();
                w.dropItem(player.getLocation(),res);
            }
            List<String> list = MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_SUCCESS_TRADE();
            MessageUtil.sendMessageTo(player, list);
        }else {
        }

        backGui();
        unregisterSelf();
    }
    private void backGui(){
        player.openInventory(tradeMenu.getInventory());
    }

    private void unregisterSelf(){
        PlayerChatEvent.getHandlerList().unregister(this);
        PlayerQuitEvent.getHandlerList().unregister(this);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        if (!event.getPlayer().equals(this.player)){return;}
        unregisterSelf();

    }


}