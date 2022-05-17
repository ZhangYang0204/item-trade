package pers.zhangyang.itemtrade.base;


import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pers.zhangyang.itemtrade.util.MessageUtil;
import pers.zhangyang.itemtrade.util.ReplaceUtil;
import pers.zhangyang.itemtrade.yaml.MessageYaml;

import java.util.Collections;
import java.util.List;

public abstract class CommandBase {

    protected boolean forcePlayer;
    protected CommandSender sender;
    protected String[] args;

    public CommandBase(CommandSender sender, boolean forcePlayer , String[] args) {
        this.sender=sender;
        this.forcePlayer = forcePlayer;
        this.args=args;
    }
    public boolean process(){
        if (!(sender instanceof Player)&&forcePlayer){
            MessageUtil.sendMessageTo(sender, MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_NOT_PLAYER());
            return true;
        }
        String permission="ItemTrade."+args[0];
        if (!sender.hasPermission(permission)){

            List<String> list= MessageYaml.MESSAGE_YAML_MANAGER
                    .getCHAT_NO_PERMISSION();
            ReplaceUtil.replace(list, Collections.singletonMap("{permission}",permission));
            MessageUtil.sendMessageTo(sender, list);

            return true;
        }
       return run();
    }

    protected void invalidArgument(String arg){
        List<String> list=  MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_INVALID_ARGUMENT();
        ReplaceUtil.replace(list,Collections.singletonMap("{argument}",arg));
        MessageUtil.sendMessageTo(sender, list);
    }

    protected abstract boolean run();

}

