package druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class Druid {
    public static void main(String[] args)throws Exception {
//        导入jar包
//        定义配置文件
//        加载配置文件
//        获取连接池对象
        Properties pro = new Properties();
        InputStream is = Druid.class.getClassLoader().getResourceAsStream("druid-driver.properties");
        pro.load(is);

        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        int i=0;
        for(i=0;i<90;i++)
        {
            Connection conn = ds.getConnection();
            System.out.println(conn);
        }
        System.out.println(i);


    }
}










































