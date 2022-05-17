package pers.zhangyang.itemtrade.command;

import org.bukkit.command.CommandSender;
import pers.zhangyang.itemtrade.base.CommandBase;
import pers.zhangyang.itemtrade.service.CommandService;
import pers.zhangyang.itemtrade.service.impl.CommandServiceImpl;
import pers.zhangyang.itemtrade.util.InvocationUtil;
import pers.zhangyang.itemtrade.util.MessageUtil;
import pers.zhangyang.itemtrade.yaml.MessageYaml;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandListMenu extends CommandBase {


    public CommandListMenu(CommandSender sender, boolean forcePlayer, String[] args) {
        super(sender, forcePlayer, args);
    }

    @Override
    protected boolean run() {
        List<String> list=new ArrayList<>();
        try {
            CommandService commandService= (CommandService) InvocationUtil.getService(new CommandServiceImpl());
            list=commandService.selectDistinctMenuname();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String ss="&b&l所有菜单名字:";
        for (String s:list){
            ss+=" "+s;
        }
        MessageUtil.sendMessageTo(sender, ss);
        return true;
    }
}