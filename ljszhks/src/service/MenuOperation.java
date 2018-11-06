package service;

import dao.AirinfoDao;
import dao.impl.AirinfoDaoImpl;
import entity.Airinfo;

import java.util.List;
import java.util.Scanner;

public class MenuOperation {
    private AirinfoDao airinfoDao = new AirinfoDaoImpl();
    Command comm = new Command();
    Scanner input = new Scanner(System.in);

    /**
     * 查询全部航班信息
     */
    public void queryAll(){

        List<Airinfo> infoList = airinfoDao.queryAirinfo(1,null);
        if (infoList.size() > 0) {
            System.out.println("所有航班信息:");
            System.out.println("航班编号\t航班号\t目的地\t起飞日期");
            for (Airinfo airinfo : infoList) {
                System.out.println(airinfo.getId() + "\t" + airinfo.getFlight_number() + "  \t" + airinfo.getDestination() + " \t" + airinfo.getFlight_date());
            }
        }else System.out.println("抱歉！没有航班信息！");
    }

    /**
     * 按日期查询航班信息
     */
    public boolean queryByDate(){
        List<Airinfo> infoList = airinfoDao.queryAirinfo(2,null);
        System.out.println("请输入查询日期(格式:2018-01-01):");
        String date_ = input.next();
        if(!comm.dateCheck(date_)){
            System.out.println("请按照正确格式输入日期！(格式:2018-01-01)");
            return false;
        }
        infoList = airinfoDao.queryAirinfo(2,date_);
        if (infoList.size() > 0) {
            System.out.println("航班编号\t航班号\t目的地\t起飞日期");
            for (Airinfo airinfo : infoList) {
                System.out.println(airinfo.getId() + "\t" + airinfo.getFlight_number() + "  \t" + airinfo.getDestination() + " \t" + airinfo.getFlight_date());
            }
            return true;
        }else System.out.println("抱歉！该天没有航班信息！");
        return false;
    }

    /**
     * 按目的地查询
     */
    public void queryByDestination(){
        List<Airinfo> infoList = airinfoDao.queryAirinfo(3,null);
        System.out.println("请输入目的地:");
        String destination = input.next();
        infoList = airinfoDao.queryAirinfo(3,destination);
        if (infoList.size() > 0) {
            System.out.println("航班编号\t航班号\t目的地\t起飞日期");
            for (Airinfo airinfo : infoList) {
                System.out.println(airinfo.getId() + "\t" + airinfo.getFlight_number() + "  \t" + airinfo.getDestination() + " \t" + airinfo.getFlight_date());
            }
        }else System.out.println("抱歉！该地点没有航班信息！");
    }

    /**
     * 删除航班信息
     * @return
     */
    public boolean delAirinfo(){
        System.out.println("请输入需要删除的航班编号:");
        int id = input.nextInt();
        List<Airinfo> infoList = airinfoDao.queryAirinfo(4,id+"");
        if (infoList.size() <= 0){
            System.out.println("无此航班编号!");
            return false;
        }
        if (airinfoDao.delAirinfo(id)){
            System.out.println("删除成功!");
            return true;
        }else {
            System.out.println("删除失败，原因未知！");
            return false;
        }
    }

    /**
     * 更新航班信息
     * @return
     */
    public boolean modifyAirinfo(){
        Airinfo airinfo = new Airinfo();
        System.out.println("请输入需要修改的航班的编号:");
        int id = input.nextInt();
        List<Airinfo> infoList = airinfoDao.queryAirinfo(4,id+"");
        if (infoList.size() <= 0){
            System.out.println("无此航班编号!");
            return false;
        }
        airinfo.setId(id);
        System.out.println("请输入新的航班号:");
        airinfo.setFlight_number(input.nextInt());
        System.out.println("请输入新的目的地:");
        airinfo.setDestination(input.next());
        System.out.println("请输入新的起飞日期(格式:2018-01-01):");
        String date_ = input.next();
        if(!comm.dateCheck(date_)){
            System.out.println("请按照正确格式输入日期！(格式:2018-01-01)");
            return false;
        }
        airinfo.setFlight_date(date_);
        if(airinfoDao.modifyAirinfo(airinfo)){
            System.out.println("修改航班信息成功！");
            return true;
        }else {
            System.out.println("修改航班信息失败！请正确填写信息！");
            return false;
        }
    }

    /**
     * 新增航班
     * @return
     */
    public boolean addAirinfo(){
        Airinfo airinfo = new Airinfo();
        System.out.println("新增航班信息:");

        System.out.println("请输入新的航班号:");
        airinfo.setFlight_number(input.nextInt());
        System.out.println("请输入新的目的地:");
        airinfo.setDestination(input.next());
        System.out.println("请输入新的起飞日期(格式:2018-01-01):");
        String date_ = input.next();
        if(!comm.dateCheck(date_)){
            System.out.println("请按照正确格式输入日期！(格式:2018-01-01)");
            return false;
        }
        if (comm.dateCompare(date_)){
            System.out.println("新增航班起飞时间不能早于当前时刻");
            return false;
        }
        airinfo.setFlight_date(date_);

        if(airinfoDao.addAirinfo(airinfo)){
            System.out.println("新增航班信息成功！");
            return true;
        }else {
            System.out.println("新增航班信息失败！请已存在航班号填写信息！");
            return false;
        }
    }

    /**
     * 航班信息备份
     */
    public void infoBackup(){
        comm.infoBackup();
        System.out.println("备份成功!");
    }
}
