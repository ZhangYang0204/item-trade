package pers.zhangyang.itemtrade.dao;


import pers.zhangyang.itemtrade.base.DaoBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UpdateDao  extends DaoBase{

    public UpdateDao() throws SQLException {
    }
    public void init() throws SQLException {


        PreparedStatement ps =  getConnection().prepareStatement("" +
                "CREATE TABLE if not exists update_table (" +
                "  version STRING NOT NULL ," +
                "  PRIMARY KEY (version)" +
                ")");
          ps.executeUpdate();
    }

    public String select() throws SQLException {

        PreparedStatement ps=getConnection().prepareStatement("select * from update_table");
        ResultSet rs=ps.executeQuery();
        String version=null;
        if (rs.next()){
          version=rs.getString("version");
        }
        return version;
    }
    public int insert(String version) throws SQLException {

        PreparedStatement ps=getConnection().prepareStatement("insert into update_table (version)" +
                "values(?)");
        ps.setString(1,version);
        return ps.executeUpdate();
    }

    public int delete( ) throws SQLException {

        PreparedStatement ps=getConnection().prepareStatement("delete from update_table ");
        return ps.executeUpdate();
    }
}
