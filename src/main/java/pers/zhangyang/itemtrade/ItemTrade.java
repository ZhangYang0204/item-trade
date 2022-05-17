package pers.zhangyang.itemtrade;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pers.zhangyang.itemtrade.command.*;
import pers.zhangyang.itemtrade.domain.EditMenu;
import pers.zhangyang.itemtrade.domain.TradeMenu;
import pers.zhangyang.itemtrade.listener.*;
import pers.zhangyang.itemtrade.service.PluginService;
import pers.zhangyang.itemtrade.service.impl.PluginServiceImpl;
import pers.zhangyang.itemtrade.util.InvocationUtil;
import pers.zhangyang.itemtrade.util.MessageUtil;
import pers.zhangyang.itemtrade.yaml.GuiYaml;
import pers.zhangyang.itemtrade.yaml.MessageYaml;
import pers.zhangyang.itemtrade.yaml.SettingYaml;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemTrade extends JavaPlugin {
    private static ItemTrade instance;

    public static ItemTrade getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance=this;
        //初始化setting.yml,出错直接关闭插件
        try {
            SettingYaml.SETTING_YAML_MANAGER.init();
            MessageYaml.MESSAGE_YAML_MANAGER.init();
            GuiYaml.GUI_MANAGER.init();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            setEnabled(false);
            return;
        }

        //初始化数据库,出错直接关闭插件
        try {
            PluginService pluginService = (PluginService) InvocationUtil.getService(new PluginServiceImpl());
            pluginService.initDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            setEnabled(false);
            return;
        }

        //更新数据库,出错直接关闭插件,暂不需要
        try {
            PluginService pluginService = (PluginService) InvocationUtil.getService(new PluginServiceImpl());
            String ver=pluginService.getVersion();

            //如果小于xxx版本,就更新下数据库，然后再设置版本
            //if<xxx
            pluginService.setVersion(getDescription().getVersion());

            //重复上述操作


        } catch (SQLException e) {
            e.printStackTrace();
            setEnabled(false);
            return;
        }

        Bukkit.getPluginManager().registerEvents(new PlayerClickNextTradePage(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerClickPreviousTradePage(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerCloseEditMenu(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerClickTrade(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerClickMaterial(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerClickNextEditPage(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerClickPreviousEditPage(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerClickChangeMenuName(),this);

        Bukkit.getPluginManager().registerEvents(new PlayerClickGlass(),this);
        //到这里插件已经成功可以使用了,提示插件标准
        MessageUtil.sendMessageTo(Bukkit.getConsoleSender(),MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_SUCCESS_ENABLE_PLUGIN());


    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list=new ArrayList<>();
        String latest=args[args.length-1];
        if (args.length==1){
            list.add("correctYaml");
            list.add("help");
            list.add("listMenu");
            list.add("reloadYaml");

            list.add("editMenu");

            list.add("tradeMenu");
        }
        if (alias.length()==2&&args[0].equalsIgnoreCase("editMenu")){
            list.add("名字");
        }
        if (alias.length()==2&&args[0].equalsIgnoreCase("tradeMenu")){
            list.add("名字");
        }

        if (latest==null||list==null){return  new ArrayList<>();}
        String ll = latest.toLowerCase();
        list.removeIf(k -> !k.toLowerCase().startsWith(ll));

        return list;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
           return new CommandHelp(sender, false, args).process();


        }
        if (args.length == 1 && args[0].equalsIgnoreCase("reloadYaml")) {
          return   new CommandReloadYaml(sender, false, args).process();

        }
        if (args.length == 1 && args[0].equalsIgnoreCase("correctYaml")) {
          return   new CommandCorrectYaml(sender, false, args).process();

        }
        if (args.length == 1 && args[0].equalsIgnoreCase("listMenu")) {
            return   new CommandListMenu(sender, false, args).process();

        }
        if (args.length == 2 && args[0].equalsIgnoreCase("editMenu")) {
            return   new CommandEditMenu(sender, true, args).process();
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("tradeMenu")) {
            return   new CommandTradeMenu(sender, true, args).process();
        }
        return true;

    }

    @Override
    public void onDisable() {

        //关闭打开的gui
        for (Player p:Bukkit.getOnlinePlayers()){
            if (p.getOpenInventory().getTopInventory().getHolder() instanceof TradeMenu){
                p.closeInventory();
            }
            if (p.getOpenInventory().getTopInventory().getHolder() instanceof EditMenu){
                p.closeInventory();
            }
        }

        //关闭消息
        MessageUtil.sendMessageTo(Bukkit.getConsoleSender(),MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_SUCCESS_DISABLE_PLUGIN());



    }


    }
