package pers.zhangyang.itemtrade.command;

import org.bukkit.command.CommandSender;
import pers.zhangyang.itemtrade.base.CommandBase;
import pers.zhangyang.itemtrade.util.MessageUtil;
import pers.zhangyang.itemtrade.yaml.GuiYaml;
import pers.zhangyang.itemtrade.yaml.MessageYaml;
import pers.zhangyang.itemtrade.yaml.SettingYaml;

import java.io.IOException;

public class CommandCorrectYaml extends CommandBase {
    public CommandCorrectYaml(CommandSender sender, boolean forcePlayer, String[] args) {
        super(sender, forcePlayer, args);
    }

    @Override
    protected boolean run() {
        try {
            SettingYaml.SETTING_YAML_MANAGER.correct();
            GuiYaml.GUI_MANAGER.correct();
            MessageYaml.MESSAGE_YAML_MANAGER.correct();
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }

        MessageUtil.sendMessageTo(sender, MessageYaml.MESSAGE_YAML_MANAGER.getCHAT_SUCCESS_CORRECT_YAML());
return true;
    }
}
