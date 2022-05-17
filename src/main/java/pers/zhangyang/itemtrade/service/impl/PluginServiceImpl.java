package pers.zhangyang.itemtrade.service.impl;

import pers.zhangyang.itemtrade.ItemTrade;
import pers.zhangyang.itemtrade.dao.*;
import pers.zhangyang.itemtrade.service.PluginService;

import java.sql.SQLException;

public class PluginServiceImpl implements PluginService {

    private final UpdateDao updateDao=new UpdateDao();

    private final GoodDao goodDao=new GoodDao();
    public PluginServiceImpl() throws SQLException {

    }

    public String getVersion() throws SQLException {
        return updateDao.select();
    }
    public void setVersion(String version) throws SQLException {
        updateDao.delete();
        updateDao.insert(version);
    }
    public void initDatabase() throws SQLException {
        updateDao.init();
goodDao.init();
        if (updateDao.select()==null){
            updateDao.insert(ItemTrade.getInstance().getDescription().getVersion());
        }
    }
}
