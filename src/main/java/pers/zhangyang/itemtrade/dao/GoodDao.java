package pers.zhangyang.itemtrade.dao;

import pers.zhangyang.itemtrade.base.DaoBase;
import pers.zhangyang.itemtrade.meta.GoodMeta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodDao extends DaoBase {
    public GoodDao() throws SQLException {
    }

    @Override
    public void init() throws SQLException {
        PreparedStatement ps =  getConnection().prepareStatement("" +
                "CREATE TABLE if not exists good_table (" +
                "  uuid STRING NOT NULL ," +
                "  menuName STRING NOT NULL ," +
                "  pageIndex INT NOT NULL ," +
                "  slot INT NOT NULL ," +
                "  'data' STRING NOT NULL ," +
                "  'twoData' STRING ," +
                "  'threeData' STRING ," +
                "  'fourData' STRING  ," +
                "  PRIMARY KEY (uuid)," +
                "UNIQUE (menuName,pageIndex,slot)"+

                ")");
        ps.executeUpdate();
    }


    public List<String> selectDistinctMenuName() throws SQLException {
        PreparedStatement ps=getConnection().prepareStatement("select *  from good_table group by menuName");

        ResultSet rs=ps.executeQuery();
        List<String> goodMetaList =new ArrayList<>();
        while (rs.next()){
            goodMetaList.add(changeFromResultSet(rs).getMenuName());
        }
        return goodMetaList;
    }

    public List<GoodMeta> selectByMenuName(String menuName ) throws SQLException {
        PreparedStatement ps=getConnection().prepareStatement("select * from good_table where  menuName=?");

        ps.setString(1,menuName);
        ResultSet rs=ps.executeQuery();
        List<GoodMeta> goodMetaList =new ArrayList<>();
        while (rs.next()){
            goodMetaList.add(changeFromResultSet(rs));
        }
        return goodMetaList;
    }
    public List<GoodMeta> selectByMenuNameAndPageIndex(String menuName,int pageIndex) throws SQLException {
        PreparedStatement ps=getConnection().prepareStatement("select * from good_table where pageIndex=? and menuName=?");
        ps.setInt(1,pageIndex);
        ps.setString(2,menuName);
        ResultSet rs=ps.executeQuery();
        List<GoodMeta> goodMetaList =new ArrayList<>();
        while (rs.next()){
            goodMetaList.add(changeFromResultSet(rs));
        }
        return goodMetaList;
    }
    public int selectMaxPageIndex(String menuName) throws SQLException {
        PreparedStatement ps=getConnection().prepareStatement("select max (pageIndex) as max from good_table where menuName=?");
        ps.setString(1,menuName);
        ResultSet rs=ps.executeQuery();
        rs.next();
       return rs.getInt("max");

    }
    public int selectMinPageIndex(String menuName) throws SQLException {
        PreparedStatement ps=getConnection().prepareStatement("select min (pageIndex) as min from good_table where menuName=?");
        ps.setString(1,menuName);
        ResultSet rs=ps.executeQuery();
        rs.next();
        return rs.getInt("min");

    }


    public int insert(GoodMeta goodMetaList) throws SQLException {
        PreparedStatement ps=getConnection().prepareStatement("insert into good_table (uuid,menuName,pageIndex,slot," +
                "data,twoData,threeData,fourData) values (?,?,?,?,?,?,?,?)");
        ps.setString(1,goodMetaList.getUuid());
        ps.setString(2,goodMetaList.getMenuName());
        ps.setInt(3,goodMetaList.getPageIndex());
        ps.setInt(4,goodMetaList.getSlot());
        ps.setString(5,goodMetaList.getData());
        ps.setString(6,goodMetaList.getTwoData());
        ps.setString(7,goodMetaList.getThreeData());
        ps.setString(8,goodMetaList.getFourData());
        return ps.executeUpdate();
    }
    public int deleteByMenuNameAndPageIndex(String menuName,int pageIndex) throws SQLException {
        PreparedStatement ps=getConnection().prepareStatement("delete from good_table where menuName=? and pageIndex=?");
        ps.setString(1,menuName);
        ps.setInt(2,pageIndex);
        return ps.executeUpdate();
    }
    public int deleteByMenuName(String menuName) throws SQLException {
        PreparedStatement ps=getConnection().prepareStatement("delete from good_table where menuName=?");
        ps.setString(1,menuName);
        return ps.executeUpdate();
    }

    public GoodMeta changeFromResultSet(ResultSet rs) throws SQLException {
        GoodMeta goodMeta =new GoodMeta();
        goodMeta.setPageIndex(rs.getInt("pageIndex"));
        goodMeta.setSlot(rs.getInt("slot"));
        goodMeta.setUuid(rs.getString("uuid"));
        goodMeta.setData(rs.getString("data"));
        goodMeta.setMenuName(rs.getString("menuName"));
        goodMeta.setThreeData(rs.getString("threeData"));
        goodMeta.setTwoData(rs.getString("twoData"));
        goodMeta.setFourData(rs.getString("fourData"));
        return goodMeta;
    }

}
