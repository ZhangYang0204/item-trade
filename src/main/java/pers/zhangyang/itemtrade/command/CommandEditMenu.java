package pers.zhangyang.itemtrade.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pers.zhangyang.itemtrade.base.CommandBase;
import pers.zhangyang.itemtrade.domain.EditMenu;
import pers.zhangyang.itemtrade.meta.GoodMeta;
import pers.zhangyang.itemtrade.service.CommandService;
import pers.zhangyang.itemtrade.service.impl.CommandServiceImpl;
import pers.zhangyang.itemtrade.util.InvocationUtil;
import pers.zhangyang.itemtrade.util.MessageUtil;
import pers.zhangyang.itemtrade.util.PageUtil;
import pers.zhangyang.itemtrade.util.ReplaceUtil;
import pers.zhangyang.itemtrade.yaml.GuiYaml;
import pers.zhangyang.itemtrade.yaml.MessageYaml;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CommandEditMenu extends CommandBase {
    public CommandEditMenu(CommandSender sender, boolean forcePlayer, String[] args) {
        super(sender, forcePlayer, args);
    }

    @Override
    protected boolean run() {
        Player player= (Player) sender;
        List<GoodMeta> goodMetaList;
        CommandService commandService;


        try {
            commandService= (CommandService) InvocationUtil.getService(new CommandServiceImpl());
            goodMetaList =commandService.getItemMeta(args[1],0);
            commandService.deleteItemMeta(args[1],0 );
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }


        HashMap<String,String> rep=new HashMap<>();
        rep.put("{index}","0");
        rep.put("{menu}",args[1]);
        EditMenu editMenu =new EditMenu(ReplaceUtil.replace(GuiYaml.GUI_MANAGER.getTITLE_EDIT_MENU(), rep),args[1]);



        editMenu.init(0, goodMetaList);
        player.openInventory(editMenu.getInventory());

       /* List<String> list=MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_SUCCESS_EDIT_MENU();
        ReplaceUtil.replace(list,Collections.singletonMap("{menu}",args[1]));
        MessageUtil.sendMessageTo(player,list );*/

        return true;

    }
}
