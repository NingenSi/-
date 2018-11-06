package dao.impl;

import dao.AirinfoDao;
import entity.Airinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 航班信息实现类
 */
public class AirinfoDaoImpl extends BaseDAO implements AirinfoDao {

    /**
     * 查询航班信息
     * @param status   查询类型：1.查询全部  2.按日期查询  3.按目的地查询  4.ID查询
     * @param param
     * @return
     */
    @Override
    public List<Airinfo> queryAirinfo(int status, String param){
        List<Airinfo> infoList = new ArrayList<>();
        String sql = null;
        Connection conn = getConnection();
        PreparedStatement prestat = null;
        ResultSet rs = null;
        if (status == 1){  //查询全部
            sql = "select id,flight_number,destination,flight_date from airinfo";
        }else if (status == 2){  //按起飞时间查询
            sql = "select id,flight_number,destination,flight_date from airinfo where flight_date = to_date(?,'yyyy-MM-dd')";
        }else if (status == 3){    //按目的地
            sql = "select id,flight_number,destination,flight_date from airinfo where destination = ?";
        }else {     //按航班编号
            sql = "select id,flight_number,destination,flight_date from airinfo where id = ?";
        }


        try {
            prestat = conn.prepareStatement(sql);
            if (status == 1){//查询全部

            }else if (status == 2){//按起飞时间查询
                prestat.setString(1,param);
            }else if (status == 3){//按目的地
                prestat.setString(1,param);
            }else { //按航班编号
                prestat.setInt(1,Integer.valueOf(param));
            }
            rs = prestat.executeQuery();
            while (rs.next()){
                Airinfo airinfo = new Airinfo();
                airinfo.setId(rs.getInt("id"));
                airinfo.setFlight_number(rs.getInt("flight_number"));
                airinfo.setDestination(rs.getString("destination"));
                String str = rs.getString("flight_date");
                airinfo.setFlight_date(str.substring(0,10));
                infoList.add(airinfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(conn,prestat,rs);
        }

        return infoList;
    }

    /**
     * 根据航班编号删除航班
     * @param id  航班编号
     * @return
     */
    @Override
    public boolean delAirinfo(int id){
        String sql = "delete from airinfo where id = ?";
        if(operateDML(sql,new Object[]{id})){
            return true;
        }
        return false;
    }

    /**
     * 根据航班编号更新航班信息
     * @param airinfo  存放更新的信息
     * @return
     */
    @Override
    public boolean modifyAirinfo(Airinfo airinfo){
        String sql = "update airinfo set flight_number = ?,destination = ?," +
                "flight_date = to_date(?,'yyyy-MM-dd') where id = ?\n";
        if (operateDML(sql,new Object[]{airinfo.getFlight_number(),airinfo.getDestination(),airinfo.getFlight_date(),airinfo.getId()})){
            return true;
        }
        return false;
    }

    /**
     * 新增航班信息
     * @param airinfo
     * @return
     */
    @Override
    public boolean addAirinfo(Airinfo airinfo){
        String sql = "insert into airinfo values(airinfo_id_sq.nextval,?,?,to_date(?,'yyyy-MM-dd'))";
        if (operateDML(sql,new Object[]{airinfo.getFlight_number(),airinfo.getDestination(),airinfo.getFlight_date()})){
            return true;
        }
        return false;
    }
}
