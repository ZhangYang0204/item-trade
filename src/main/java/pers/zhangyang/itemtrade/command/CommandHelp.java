package pers.zhangyang.itemtrade.command;

import org.bukkit.command.CommandSender;
import pers.zhangyang.itemtrade.base.CommandBase;
import pers.zhangyang.itemtrade.util.MessageUtil;
import pers.zhangyang.itemtrade.yaml.MessageYaml;

public class CommandHelp extends CommandBase {


    public CommandHelp(CommandSender sender, boolean forcePlayer, String[] args) {
        super(sender, forcePlayer, args);
    }

    @Override
    protected boolean run() {
        MessageUtil.sendMessageTo(sender, MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_SUCCESS_HELP());
return true;
    }
}
