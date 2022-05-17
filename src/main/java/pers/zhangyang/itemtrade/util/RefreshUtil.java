package pers.zhangyang.itemtrade.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pers.zhangyang.itemtrade.domain.EditMenu;
import pers.zhangyang.itemtrade.domain.TradeMenu;
import pers.zhangyang.itemtrade.meta.GoodMeta;
import pers.zhangyang.itemtrade.service.CommandService;
import pers.zhangyang.itemtrade.service.impl.CommandServiceImpl;
import pers.zhangyang.itemtrade.yaml.GuiYaml;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RefreshUtil {



    public static void refreshTradeMenu(Player player){

        for (Player p: Bukkit.getOnlinePlayers()) {

            if (p.equals(player)){continue;}
            if (!(p.getOpenInventory().getTopInventory().getHolder() instanceof TradeMenu)) {return;}

            TradeMenu saveMenu = (TradeMenu) p.getOpenInventory().getTopInventory().getHolder();
            List<GoodMeta> goodMetaList = new ArrayList<>();
            CommandService commandService;
            int should= saveMenu.getPageIndex();
            try {
                commandService = (CommandService) InvocationUtil.getService(new CommandServiceImpl());
                int max=commandService.getMaxPageIndex(saveMenu.getMenuName());
                if (max<should){
                    should=max;
                }
                goodMetaList=commandService.getItemMeta(saveMenu.getMenuName(), should);
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
            HashMap<String,String> rep=new HashMap<>();
            rep.put("{index}",should+"");
            rep.put("{menu}", saveMenu.getMenuName());
            TradeMenu editMenu =new TradeMenu(ReplaceUtil.replace(GuiYaml.GUI_MANAGER.getTITLE_TRADE_MENU(), rep), saveMenu.getMenuName());
            editMenu.init(should,goodMetaList);
            p.openInventory(editMenu.getInventory());

        }

    }
}
