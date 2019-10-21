package Utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
//Druid连接池工具类

public class JDBCUtils {

    private static DataSource ds;

    static {
        try {
//            加载配置文件
            Properties pro = new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid-driver.properties"));
//            获取DataSourse
            ds = DruidDataSourceFactory.createDataSource(pro);
            System.out.println("pro="+pro);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close(Statement stmt,Connection conn)
    {
        close(null,stmt,conn);
    }
    public static void close(ResultSet rs,Statement stmt, Connection conn)
    {
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null)
        {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    获取连接池的方法
     */
    public static DataSource getDataSourse()
    {
        return ds;
    }



}












