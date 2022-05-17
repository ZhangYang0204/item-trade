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

    private String TITLE_TRADE_MENU;
    private String TITLE_EDIT_MENU;

    private String BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_MATERIAL;
    private String BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_DISPLAY_NAME;
    private List<String> BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_LORE;

    private Integer BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_CUSTOM_MODEL_DATA;
    private Integer BUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_CUSTOM_MODEL_DATA;
    private Integer BUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_CUSTOM_MODEL_DATA;
    private Integer BUTTON_EDIT_MENU_CHANGE_MENU_NAME_CUSTOM_MODEL_DATA;
    private Integer BUTTON_EDIT_MENU_NEXT_EDIT_PAGE_CUSTOM_MODEL_DATA;

    private Integer BUTTON_EDIT_MENU_GLASS_CUSTOM_MODEL_DATA;
    public Integer getBUTTON_TRADE_MENU_NEXT_TRADE_PAGE_CUSTOM_MODEL_DATA() {
        return BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_CUSTOM_MODEL_DATA;
    }

    public Integer getBUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_CUSTOM_MODEL_DATA() {
        return BUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_CUSTOM_MODEL_DATA;
    }

    public Integer getBUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_CUSTOM_MODEL_DATA() {
        return BUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_CUSTOM_MODEL_DATA;
    }

    public Integer getBUTTON_EDIT_MENU_CHANGE_MENU_NAME_CUSTOM_MODEL_DATA() {
        return BUTTON_EDIT_MENU_CHANGE_MENU_NAME_CUSTOM_MODEL_DATA;
    }

    public Integer getBUTTON_EDIT_MENU_NEXT_EDIT_PAGE_CUSTOM_MODEL_DATA() {
        return BUTTON_EDIT_MENU_NEXT_EDIT_PAGE_CUSTOM_MODEL_DATA;
    }

    public Integer getBUTTON_TRADE_MENU_GLASS_CUSTOM_MODEL_DATA() {
        return BUTTON_TRADE_MENU_GLASS_CUSTOM_MODEL_DATA;
    }

    private Integer BUTTON_TRADE_MENU_GLASS_CUSTOM_MODEL_DATA;



    private String BUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_MATERIAL;
    private String BUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_DISPLAY_NAME;
    private List<String> BUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_LORE;


    private String BUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_MATERIAL;
    private String BUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_DISPLAY_NAME;
    private List<String> BUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_LORE;


    private String BUTTON_EDIT_MENU_CHANGE_MENU_NAME_MATERIAL;
    private String BUTTON_EDIT_MENU_CHANGE_MENU_NAME_DISPLAY_NAME;
    private List<String> BUTTON_EDIT_MENU_CHANGE_MENU_NAME_LORE;


    private String BUTTON_EDIT_MENU_NEXT_EDIT_PAGE_MATERIAL;
    private String BUTTON_EDIT_MENU_NEXT_EDIT_PAGE_DISPLAY_NAME;
    private List<String> BUTTON_EDIT_MENU_NEXT_EDIT_PAGE_LORE;




    private String BUTTON_TRADE_MENU_GLASS_MATERIAL;
    private String BUTTON_TRADE_MENU_GLASS_DISPLAY_NAME;
    private List<String> BUTTON_TRADE_MENU_GLASS_LORE;

    private String BUTTON_EDIT_MENU_GLASS_MATERIAL;
    private String BUTTON_EDIT_MENU_GLASS_DISPLAY_NAME;
    private List<String> BUTTON_EDIT_MENU_GLASS_LORE;

    @Override
    protected void check()   {
        TITLE_EDIT_MENU=getString("gui.title.editMenu",false);
        TITLE_TRADE_MENU=getString("gui.title.tradeMenu",false);

        BUTTON_TRADE_MENU_GLASS_MATERIAL=getString("gui.button.tradeMenuGlass.material",true);
        if (Material.matchMaterial(BUTTON_TRADE_MENU_GLASS_MATERIAL)==null){
            BUTTON_TRADE_MENU_GLASS_MATERIAL="STONE";
        }
        BUTTON_TRADE_MENU_GLASS_DISPLAY_NAME=getString("gui.button.tradeMenuGlass.displayName",false);
        BUTTON_TRADE_MENU_GLASS_LORE=getStringList("gui.button.tradeMenuGlass.lore",false);


        BUTTON_EDIT_MENU_GLASS_MATERIAL=getString("gui.button.editMenuGlass.material",true);
        if (Material.matchMaterial(BUTTON_EDIT_MENU_GLASS_MATERIAL)==null){
            BUTTON_EDIT_MENU_GLASS_MATERIAL="STONE";
        }
        BUTTON_EDIT_MENU_GLASS_DISPLAY_NAME=getString("gui.button.editMenuGlass.displayName",false);
        BUTTON_EDIT_MENU_GLASS_LORE=getStringList("gui.button.editMenuGlass.lore",false);


        BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_CUSTOM_MODEL_DATA=getInteger("gui.button.tradeMenuNextTradePage.customModelData",false);
        BUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_CUSTOM_MODEL_DATA=getInteger("gui.button.tradeMenuPreviousTradePage.customModelData",false);
        BUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_CUSTOM_MODEL_DATA=getInteger("gui.button.editMenuPreviousEditPage.customModelData",false);
        BUTTON_EDIT_MENU_CHANGE_MENU_NAME_CUSTOM_MODEL_DATA=getInteger("gui.button.editMenuChangeMenuName.customModelData",false);
        BUTTON_EDIT_MENU_NEXT_EDIT_PAGE_CUSTOM_MODEL_DATA=getInteger("gui.button.editMenuNextEditPage.customModelData",false);
        BUTTON_EDIT_MENU_GLASS_CUSTOM_MODEL_DATA=getInteger("gui.button.editMenuGlass.customModelData",false);
        BUTTON_TRADE_MENU_GLASS_CUSTOM_MODEL_DATA=getInteger("gui.button.tradeMenuGlass.customModelData",false);


        BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_MATERIAL=getString("gui.button.tradeMenuNextTradePage.material",true);
        if (Material.matchMaterial(BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_MATERIAL)==null){
            BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_MATERIAL="STONE";
        }
        BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_DISPLAY_NAME=getString("gui.button.tradeMenuNextTradePage.displayName",false);
        BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_LORE=getStringList("gui.button.tradeMenuNextTradePage.lore",false);


        BUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_MATERIAL=getString("gui.button.tradeMenuPreviousTradePage.material",true);
        if (Material.matchMaterial(BUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_MATERIAL)==null){
            BUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_MATERIAL="STONE";
        }
        BUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_DISPLAY_NAME=getString("gui.button.tradeMenuPreviousTradePage.displayName",false);
        BUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_LORE=getStringList("gui.button.tradeMenuPreviousTradePage.lore",false);

        BUTTON_EDIT_MENU_NEXT_EDIT_PAGE_MATERIAL=getString("gui.button.editMenuNextEditPage.material",true);
        if (Material.matchMaterial(BUTTON_EDIT_MENU_NEXT_EDIT_PAGE_MATERIAL)==null){
            BUTTON_EDIT_MENU_NEXT_EDIT_PAGE_MATERIAL="STONE";
        }
        BUTTON_EDIT_MENU_NEXT_EDIT_PAGE_DISPLAY_NAME=getString("gui.button.editMenuNextEditPage.displayName",false);
        BUTTON_EDIT_MENU_NEXT_EDIT_PAGE_LORE=getStringList("gui.button.editMenuNextEditPage.lore",false);

        BUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_MATERIAL=getString("gui.button.editMenuPreviousEditPage.material",true);
        if (Material.matchMaterial(BUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_MATERIAL)==null){
            BUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_MATERIAL="STONE";
        }
        BUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_DISPLAY_NAME=getString("gui.button.editMenuPreviousEditPage.displayName",false);
        BUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_LORE=getStringList("gui.button.editMenuPreviousEditPage.lore",false);

        BUTTON_EDIT_MENU_CHANGE_MENU_NAME_MATERIAL=getString("gui.button.editMenuChangeMenuName.material",true);
        if (Material.matchMaterial(BUTTON_EDIT_MENU_CHANGE_MENU_NAME_MATERIAL)==null){
            BUTTON_EDIT_MENU_CHANGE_MENU_NAME_MATERIAL="STONE";
        }
        BUTTON_EDIT_MENU_CHANGE_MENU_NAME_DISPLAY_NAME=getString("gui.button.editMenuChangeMenuName.displayName",false);
        BUTTON_EDIT_MENU_CHANGE_MENU_NAME_LORE=getStringList("gui.button.editMenuChangeMenuName.lore",false);




    }

    public Integer getBUTTON_EDIT_MENU_GLASS_CUSTOM_MODEL_DATA() {
        return BUTTON_EDIT_MENU_GLASS_CUSTOM_MODEL_DATA;
    }

    public String getBUTTON_EDIT_MENU_GLASS_MATERIAL() {
        return BUTTON_EDIT_MENU_GLASS_MATERIAL;
    }

    public String getBUTTON_EDIT_MENU_GLASS_DISPLAY_NAME() {
        return BUTTON_EDIT_MENU_GLASS_DISPLAY_NAME;
    }

    public List<String> getBUTTON_EDIT_MENU_GLASS_LORE() {
        if (BUTTON_EDIT_MENU_GLASS_LORE==null){     return null;
        } return new ArrayList<>(BUTTON_EDIT_MENU_GLASS_LORE);
    }

    public String getBUTTON_TRADE_MENU_GLASS_MATERIAL() {
        return BUTTON_TRADE_MENU_GLASS_MATERIAL;
    }

    public String getBUTTON_TRADE_MENU_GLASS_DISPLAY_NAME() {
        return BUTTON_TRADE_MENU_GLASS_DISPLAY_NAME;
    }

    public List<String> getBUTTON_TRADE_MENU_GLASS_LORE() {
        if (BUTTON_TRADE_MENU_GLASS_LORE==null){     return null;
        } return new ArrayList<>(BUTTON_TRADE_MENU_GLASS_LORE);
    }

    public String getBUTTON_EDIT_MENU_CHANGE_MENU_NAME_MATERIAL() {
        return BUTTON_EDIT_MENU_CHANGE_MENU_NAME_MATERIAL;
    }

    public String getBUTTON_EDIT_MENU_CHANGE_MENU_NAME_DISPLAY_NAME() {
        return BUTTON_EDIT_MENU_CHANGE_MENU_NAME_DISPLAY_NAME;
    }

    public List<String> getBUTTON_EDIT_MENU_CHANGE_MENU_NAME_LORE() {
        if (BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_LORE==null){     return null;
        } return new ArrayList<>(BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_LORE);
    }

    public static GuiYaml getGuiManager() {
        return GUI_MANAGER;
    }

    public String getBUTTON_TRADE_MENU_NEXT_TRADE_PAGE_MATERIAL() {
        return BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_MATERIAL;
    }

    public String getBUTTON_TRADE_MENU_NEXT_TRADE_PAGE_DISPLAY_NAME() {
        return BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_DISPLAY_NAME;
    }

    public List<String> getBUTTON_TRADE_MENU_NEXT_TRADE_PAGE_LORE() {
        if (BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_LORE==null){     return null;
        }  return new ArrayList<>(BUTTON_TRADE_MENU_NEXT_TRADE_PAGE_LORE);
    }

    public String getBUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_MATERIAL() {
        return BUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_MATERIAL;
    }

    public String getBUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_DISPLAY_NAME() {
        return BUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_DISPLAY_NAME;
    }

    public List<String> getBUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_LORE() {

        if (BUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_LORE==null){     return null;
        }  return new ArrayList<>(BUTTON_TRADE_MENU_PREVIOUS_TRADE_PAGE_LORE);
    }

    public String getBUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_MATERIAL() {
        return BUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_MATERIAL;
    }

    public String getBUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_DISPLAY_NAME() {
        return BUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_DISPLAY_NAME;
    }

    public List<String> getBUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_LORE() {
        if (BUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_LORE==null){     return null;
        }  return new ArrayList<>(BUTTON_EDIT_MENU_PREVIOUS_EDIT_PAGE_LORE);
    }

    public String getBUTTON_EDIT_MENU_NEXT_EDIT_PAGE_MATERIAL() {
        return BUTTON_EDIT_MENU_NEXT_EDIT_PAGE_MATERIAL;
    }

    public String getBUTTON_EDIT_MENU_NEXT_EDIT_PAGE_DISPLAY_NAME() {
        return BUTTON_EDIT_MENU_NEXT_EDIT_PAGE_DISPLAY_NAME;
    }

    public List<String> getBUTTON_EDIT_MENU_NEXT_EDIT_PAGE_LORE() {
        if (BUTTON_EDIT_MENU_NEXT_EDIT_PAGE_LORE==null){     return null;
        }  return new ArrayList<>(BUTTON_EDIT_MENU_NEXT_EDIT_PAGE_LORE);
    }

    public String getTITLE_TRADE_MENU() {
        return TITLE_TRADE_MENU;
    }

    public String getTITLE_EDIT_MENU() {
        return TITLE_EDIT_MENU;
    }
}
