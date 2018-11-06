package service;

import dao.AirinfoDao;
import dao.impl.AirinfoDaoImpl;
import entity.Airinfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 通用方法类
 */
public class Command {

    /**
     * 对航班信息备份
     */
    public void infoBackup(){
        List<Airinfo> infoList = new ArrayList<>();
        AirinfoDao airinfoDao = new AirinfoDaoImpl();
        Date date = new Date();
        //获取全部航班信息
        infoList = airinfoDao.queryAirinfo(1,null);
        //拼接
        StringBuilder strb = new StringBuilder();
        for ( Airinfo airinfo : infoList){
            strb.append(airinfo);
        }
        FileWriter fw = null;
        String backupName = "infoBackup"+date.getTime()+".txt";  //名字加上时间毫秒数，以防止重名
        File file = new File("src//" + backupName);
        try {
            fw= new FileWriter(file);
            fw.write(strb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 检验日期格式是否为 yyyy-MM-dd格式
     * @param datestr 日期
     * @return
     */
    public boolean dateCheck(String datestr){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateFormat.parse(datestr);
            return true;
        } catch (ParseException e) {
        }
        return false;
    }

    /**
     * 比较日期
     * @param datestr
     * @return
     */
    public boolean dateCompare(String datestr){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = sdf.parse(datestr);
            if(date.getTime() - date1.getTime() > 0){
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }
}
