package pers.zhangyang.itemtrade.yaml;

import org.bukkit.Material;
import pers.zhangyang.itemtrade.base.YamlManagerBase;

import java.util.ArrayList;
import java.util.List;

public class GuiYaml extends YamlManagerBase {
    public static GuiYaml GUI_MANAGER =new GuiYaml();
    private GuiYaml( ) {
        super("language/"+ SettingYaml.SETTING_YAML_MANAGER.getLANGUAGE()+"/gui.yml");
    }





    @Override
    protected void check()   {


    }

}
