package pers.zhangyang.itemtrade.runnable;

import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import pers.zhangyang.itemtrade.ItemTrade;
import pers.zhangyang.itemtrade.util.MessageUtil;
import pers.zhangyang.itemtrade.util.ReplaceUtil;
import pers.zhangyang.itemtrade.yaml.MessageYaml;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class NotifyVersionRunnable extends BukkitRunnable {

    private String latest;
    private CommandSender sender;
    public NotifyVersionRunnable(@Nullable String latest, CommandSender sender){
        this.latest=latest;
        this.sender=sender;
    }

    @Override
    public void run() {
        List<String> list;
        if (latest!=null) {
            list = MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_SUCCESS_NOTIFY_VERSION();
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("{current}", ItemTrade.getInstance().getDescription().getVersion());
            hashMap.put("{latest}",latest);
            ReplaceUtil.replace(list,hashMap);
        }else {
            list = MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_FAILURE_NOTIFY_VERSION_BECAUSE_NOT_GET_LATEST_VERSION();
        }
        MessageUtil.sendMessageTo(sender,list);
    }
}
