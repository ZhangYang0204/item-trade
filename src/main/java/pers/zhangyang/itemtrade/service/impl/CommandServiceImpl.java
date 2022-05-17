package pers.zhangyang.itemtrade.service.impl;

import org.bukkit.inventory.meta.ItemMeta;
import pers.zhangyang.itemtrade.dao.GoodDao;
import pers.zhangyang.itemtrade.meta.GoodMeta;
import pers.zhangyang.itemtrade.service.CommandService;

import java.sql.SQLException;
import java.util.List;

public class CommandServiceImpl implements CommandService {
    private GoodDao goodDao =new GoodDao();
    public CommandServiceImpl() throws SQLException {
    }

    @Override
    public List<GoodMeta> getItemMeta(String menuName,int pageIndex) throws SQLException {
        return goodDao.selectByMenuNameAndPageIndex(menuName,pageIndex);
    }

    @Override
    public List<GoodMeta> getItemMeta(String menuName) throws SQLException {
        return goodDao.selectByMenuName(menuName);
    }

    @Override
    public void deleteItemMeta(String menuName, int pageIndex) throws SQLException {
        goodDao.deleteByMenuNameAndPageIndex(menuName,pageIndex);
    }

    @Override
    public void insert(List<GoodMeta> goodMetaList) throws SQLException {
        for (GoodMeta g:goodMetaList){
            goodDao.insert(g);
        }
    }

    @Override
    public int getMaxPageIndex(String maenuName) throws SQLException {
        return goodDao.selectMaxPageIndex(maenuName);
    }

    @Override
    public int getMinPageIndex(String maenuName) throws SQLException {
        return goodDao.selectMinPageIndex(maenuName);
    }

    @Override
    public List<String> selectDistinctMenuname( ) throws SQLException {
        return goodDao.selectDistinctMenuName();
    }

    @Override
    public void changeName(String old, String newName) throws SQLException {
        List<GoodMeta> goodMetaList=goodDao.selectByMenuName(old);
        goodDao.deleteByMenuName(old);
        for (GoodMeta g:goodMetaList){
            g.setMenuName(newName);
            goodDao.insert(g);
        }

    }

}
