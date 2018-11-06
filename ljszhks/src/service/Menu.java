package service;

import java.util.Scanner;

public class Menu {
    /**
     * 获取菜单
     *
     */
    public void getMenu(){
        MenuOperation mo = new MenuOperation();
        Scanner input = new Scanner(System.in);
        int operation;  //菜单操作
        System.out.println("***********************欢迎使用航班信息管理系统***********************");
        while (true){
            System.out.println("请选择操作:");
            System.out.println("1.列出所有航班");
            System.out.println("2.按起飞时间查询");
            System.out.println("3.按目的地查询");
            System.out.println("4.删除航班");
            System.out.println("5.更新航班");
            System.out.println("6.新增航班信息");
            System.out.println("7.导出航班信息");
            System.out.println("8.退出系统");
            operation = input.nextInt();
            switch (operation){
                case 1:
                    mo.queryAll();
                    break;
                case 2:
                    mo.queryByDate();
                    break;
                case 3:
                    mo.queryByDestination();
                    break;
                case 4:
                    mo.delAirinfo();
                    break;
                case 5:
                    mo.modifyAirinfo();
                    break;
                case 6:
                    mo.addAirinfo();
                    break;
                case 7:
                    mo.infoBackup();
                    break;
                case 8:
                    System.exit(0);
                    break;

            }
        }
    }

}
