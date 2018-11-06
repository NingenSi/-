package dao.impl;

import java.sql.*;

public class BaseDAO {
    /**
     * 获取Oracle连接对象
     *
     * @return
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); //加载驱动
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "lsj";
            String pwd = "lsj";
            conn = DriverManager.getConnection(url, username, pwd); //获取连接对象
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("获取连接失败!");
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * 关闭数据库对象资源
     *
     * @param conn
     * @param stat
     * @param rs
     */
    public void closeAll(Connection conn, Statement stat, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * DML通用方法
     *
     * @param sql
     * @param params 预处理参数
     * @return
     */
    public boolean operateDML(String sql, Object[] params) {
        Connection conn = getConnection();
        PreparedStatement preStat = null;
        try {
            preStat = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preStat.setObject(i + 1, params[i]);
            }
            if ( preStat.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException e) {
            System.out.println("operateDML预处理异常!");
            e.printStackTrace();
        } finally {
            closeAll(conn, preStat, null);
        }

        return false;
    }


}
