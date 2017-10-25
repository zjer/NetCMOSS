package util;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {

    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static String driver = null;

    //初始化连接池
    private static BasicDataSource dataSource;

    static {

        try {
            //1.读取db.properties配置文件
            //创建properties对象
            Properties props = new Properties();
            //创建输入流
            InputStream in = DBUtil.class.getResourceAsStream("../db.properties");
            props.load(in);
            url = props.getProperty("url");
            user = props.getProperty("username");
            password = props.getProperty("password");
            driver = props.getProperty("driverClassName");
            //加载驱动
            Class.forName(driver);
            dataSource = new BasicDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 抽取获得连接数据库的方法
     * @return
     */
    public static Connection getConnection () {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 释放资源
     * @param conn
     * @param st
     * @param rs
     */
    public static void close (Connection conn, Statement st, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
    }

}
