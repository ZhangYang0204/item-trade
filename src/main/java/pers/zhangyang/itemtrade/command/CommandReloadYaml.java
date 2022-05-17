package pers.zhangyang.itemtrade.command;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import pers.zhangyang.itemtrade.base.CommandBase;
import pers.zhangyang.itemtrade.util.MessageUtil;
import pers.zhangyang.itemtrade.yaml.GuiYaml;
import pers.zhangyang.itemtrade.yaml.MessageYaml;
import pers.zhangyang.itemtrade.yaml.SettingYaml;

import java.io.IOException;

public class CommandReloadYaml extends CommandBase {
    public CommandReloadYaml(CommandSender sender, boolean forcePlayer, String[] args) {
        super(sender, forcePlayer, args);
    }

    @Override
    protected boolean run() {
        try {
            SettingYaml.SETTING_YAML_MANAGER.init();
            GuiYaml.GUI_MANAGER.init();
            MessageYaml.MESSAGE_YAML_MANAGER.init();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            return true;
        }

        MessageUtil.sendMessageTo(sender, MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_SUCCESS_RELOAD_YAML());
return true;
    }
}
