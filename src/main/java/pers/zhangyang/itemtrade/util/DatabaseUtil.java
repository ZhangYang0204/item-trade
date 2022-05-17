package pers.zhangyang.itemtrade.util;

import pers.zhangyang.itemtrade.yaml.SettingYaml;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static ThreadLocal<Connection> t = new ThreadLocal<>();
    public static Connection getConnection() throws SQLException {
        Connection connection = t.get();
        if(connection==null||connection.isClosed()){

            SettingYaml settingYamlManager= SettingYaml.SETTING_YAML_MANAGER;
            connection = DriverManager.getConnection(settingYamlManager.getDATABASE_URL()
                    ,settingYamlManager.getDATABASE_USERNAME(), settingYamlManager.getDATABASE_PASSWORD());
            connection.setAutoCommit(false);
            t.set(connection);
        }

        return connection;
    }
}
