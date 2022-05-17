package pers.zhangyang.itemtrade.base;


import pers.zhangyang.itemtrade.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DaoBase {
    //给sqlite用
    public Connection getConnection() throws SQLException {
        return DatabaseUtil.getConnection();

    }
    public DaoBase() throws SQLException {
        init();
    }
    public abstract void init() throws SQLException;

}
