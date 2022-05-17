package pers.zhangyang.itemtrade.service;

import org.bukkit.inventory.meta.ItemMeta;
import pers.zhangyang.itemtrade.meta.GoodMeta;

import java.sql.SQLException;
import java.util.List;

public interface CommandService {
    List<GoodMeta> getItemMeta(String menuName, int pageIndex) throws SQLException;
    List<GoodMeta> getItemMeta(String menuName) throws SQLException;

    void deleteItemMeta(String menuName, int pageIndex) throws SQLException;

    void insert(List<GoodMeta> goodMetaList) throws SQLException;

    int getMaxPageIndex(String maenuName) throws SQLException;
    int getMinPageIndex(String maenuName) throws SQLException;
    List<String> selectDistinctMenuname() throws SQLException;
    void changeName(String old,String newName) throws SQLException;
}
