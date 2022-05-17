package pers.zhangyang.itemtrade.yaml;

import pers.zhangyang.itemtrade.base.YamlManagerBase;

import java.util.ArrayList;
import java.util.List;

public class MessageYaml extends YamlManagerBase {
    public static final MessageYaml MESSAGE_YAML_MANAGER =new MessageYaml();

    private MessageYaml( ) {
        super("language/"+ SettingYaml.SETTING_YAML_MANAGER.getLANGUAGE()+"/message.yml");
    }

    private List<String> CHAT_FAILURE_NOTIFY_VERSION_BECAUSE_NOT_GET_LATEST_VERSION;
    private List<String> CHAT_NOT_PLAYER;
    private List<String> CHAT_NO_PERMISSION;
    private List<String> CHAT_INVALID_ARGUMENT;
    private List<String> CHAT_SUCCESS_NOTIFY_VERSION;
    private List<String> CHAT_SUCCESS_CORRECT_YAML;
    private List<String> CHAT_SUCCESS_RELOAD_YAML;
    private List<String> CHAT_SUCCESS_HELP;
    private List<String> CHAT_SUCCESS_ENABLE_PLUGIN;
    private List<String> CHAT_SUCCESS_DISABLE_PLUGIN;




    @Override
    protected void check()  {




        CHAT_SUCCESS_NOTIFY_VERSION =getStringList("message.chat.successNotifyVersion",false);
        CHAT_NOT_PLAYER=getStringList("message.chat.notPlayer",false);
        CHAT_NO_PERMISSION=getStringList("message.chat.noPermission",false);
        CHAT_SUCCESS_HELP =getStringList("message.chat.successHelp",false);
        CHAT_SUCCESS_CORRECT_YAML=getStringList("message.chat.successCorrectYaml",false);
        CHAT_SUCCESS_RELOAD_YAML =getStringList("message.chat.successReloadYaml",false);
        CHAT_INVALID_ARGUMENT=getStringList("message.chat.invalidArgument",false);
        CHAT_SUCCESS_ENABLE_PLUGIN=getStringList("message.chat.successEnablePlugin",false);
        CHAT_SUCCESS_DISABLE_PLUGIN=getStringList("message.chat.successDisablePlugin",false);
        CHAT_FAILURE_NOTIFY_VERSION_BECAUSE_NOT_GET_LATEST_VERSION =getStringList("message.chat.failureNotifyVersionBecauseNotGetLatestVersion",false);


    }


    public List<String> getCHAT_FAILURE_NOTIFY_VERSION_BECAUSE_NOT_GET_LATEST_VERSION() {
        if (CHAT_FAILURE_NOTIFY_VERSION_BECAUSE_NOT_GET_LATEST_VERSION==null){
            return null; }return new ArrayList<>(CHAT_FAILURE_NOTIFY_VERSION_BECAUSE_NOT_GET_LATEST_VERSION);
    }
    
    

    public List<String> getCHAT_SUCCESS_ENABLE_PLUGIN() {
        if (CHAT_SUCCESS_ENABLE_PLUGIN==null){
            return null; }return new ArrayList<>(CHAT_SUCCESS_ENABLE_PLUGIN);
    }

    public List<String> getCHAT_SUCCESS_DISABLE_PLUGIN() {
        if (CHAT_SUCCESS_DISABLE_PLUGIN==null){
            return null; }return new ArrayList<>(CHAT_SUCCESS_DISABLE_PLUGIN);
    }






    public List<String> getCHAT_SUCCESS_NOTIFY_VERSION() {
        if (CHAT_SUCCESS_NOTIFY_VERSION ==null){
            return null;
        }return new ArrayList<>(CHAT_SUCCESS_NOTIFY_VERSION);
    }



    public List<String> getCHAT_NOT_PLAYER() {
        if (CHAT_NOT_PLAYER==null){
            return null;
        }return new ArrayList<>(CHAT_NOT_PLAYER);
    }

    public List<String> getCHAT_NO_PERMISSION() {
        if (CHAT_NO_PERMISSION==null){
            return null;
        }return new ArrayList<>(CHAT_NO_PERMISSION);
    }

    public List<String> getCHAT_INVALID_ARGUMENT() {
        if (CHAT_INVALID_ARGUMENT==null){
            return null;
        }return new ArrayList<>(CHAT_INVALID_ARGUMENT);
    }

    public List<String> getCHAT_SUCCESS_RELOAD_YAML() {
        if (CHAT_SUCCESS_RELOAD_YAML ==null){
            return null;
        }return new ArrayList<>(CHAT_SUCCESS_RELOAD_YAML);
    }

    public List<String> getCHAT_SUCCESS_CORRECT_YAML() {
        if (CHAT_SUCCESS_CORRECT_YAML==null){
            return null;
        }return new ArrayList<>(CHAT_SUCCESS_CORRECT_YAML);
    }
    public List<String> getCHAT_SUCCESS_HELP() {
        if (CHAT_SUCCESS_HELP==null){
            return null;
        }return new ArrayList<>(CHAT_SUCCESS_HELP);
    }

}
