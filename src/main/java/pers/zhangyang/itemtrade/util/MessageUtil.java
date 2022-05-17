package pers.zhangyang.itemtrade.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

public class MessageUtil {

    public static void sendTitleTo(Player player,String title,String subtitle){



        player.sendTitle(ChatColor.translateAlternateColorCodes('&',title),ChatColor.translateAlternateColorCodes('&',subtitle),10,10,20);


    }

    public static void sendMessageTo(CommandSender sender,@Nullable List<String> strings) {
        if (strings==null){return;}


        for (String s : strings) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',s));
        }
    }
    public static void sendMessageTo(Collection<? extends CommandSender> senderList, @Nullable List<String> strings) {
        if (strings==null){return;}


        for (CommandSender sender:senderList) {
            for (String s : strings) {sender.sendMessage(ChatColor.translateAlternateColorCodes('&',s));
            }
        }
    }
    public static void sendMessageTo(Collection<? extends CommandSender> senderList,@Nullable String s) {
        if (s==null){return;}
        for (CommandSender sender:senderList) {sender.sendMessage(ChatColor.translateAlternateColorCodes('&',s));
        }
    }
    public static void sendMessageTo(CommandSender sender,@Nullable String s) {
        if (s==null){return;}
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',s));
    }
}
