package pers.zhangyang.itemtrade.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pers.zhangyang.itemtrade.domain.EditMenu;
import pers.zhangyang.itemtrade.meta.GoodMeta;
import pers.zhangyang.itemtrade.service.CommandService;
import pers.zhangyang.itemtrade.service.impl.CommandServiceImpl;
import pers.zhangyang.itemtrade.util.InvocationUtil;
import pers.zhangyang.itemtrade.util.MessageUtil;
import pers.zhangyang.itemtrade.util.RefreshUtil;
import pers.zhangyang.itemtrade.util.ReplaceUtil;
import pers.zhangyang.itemtrade.yaml.GuiYaml;
import pers.zhangyang.itemtrade.yaml.MessageYaml;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PlayerCreateParty implements Listener {
    private Player player;
    private EditMenu oldName;

    public PlayerCreateParty(Player player,EditMenu oldName) {
        this.player = player;
        this.oldName=oldName;
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event){

        Player player=event.getPlayer();
        if (!player.equals(this.player)){return;}
        event.setCancelled(true);
        CommandService commandService;



        String message=event.getMessage();

        if (message.contains(" ")){
            List<String> list = MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_HOW_TO_CHANGE_MENU_NAME();
            MessageUtil.sendMessageTo(player, list);
            return;
        }

        if (message.equalsIgnoreCase("cancel")){
            unregisterSelf();
            backGui(oldName.getMenuName());
            return;
        }


        try {
            commandService= (CommandService) InvocationUtil.getService(new CommandServiceImpl());

            if (!commandService.getItemMeta(message).isEmpty()){
                List<String> list= MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_FAILURE_CHANGE_MENU_NAME_BECAUSE_DUPLICATE_NAME();
                MessageUtil.sendMessageTo(player,list );
                return;
            }

            commandService.changeName(oldName.getMenuName(),message );
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        List<String> list= MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_SUCCESS_CHANGE_MENU_NAME();
        MessageUtil.sendMessageTo(player,list );
        backGui(message);
        unregisterSelf();
    }
    private void backGui(String newName){
        List<GoodMeta> goodMetaList;
        try {
            CommandService commandService= (CommandService) InvocationUtil.getService(new CommandServiceImpl());
            goodMetaList=commandService.getItemMeta(newName,oldName.getPageIndex());
            commandService.deleteItemMeta(newName, oldName.getPageIndex() );
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        HashMap<String,String> rep=new HashMap<>();
        rep.put("{index}",""+oldName.getPageIndex());
        rep.put("{menu}",newName);
        EditMenu editMenu =new EditMenu(ReplaceUtil.replace(GuiYaml.GUI_MANAGER.getTITLE_EDIT_MENU(), rep),newName);
        editMenu.init(oldName.getPageIndex(), goodMetaList);
        player.openInventory(editMenu.getInventory());


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
