package druid;

import Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DruidDome1 {

    public static void main(String[] args) {

        try {

            Connection conn = JDBCUtils.getConnection();
            String SQL = "insert into test2 values(?,?)";
            PreparedStatement pstmt=conn.prepareStatement(SQL);
            pstmt.setInt(1,5);
            pstmt.setString(2,"张三");

            int count =pstmt.executeUpdate();
            System.out.println("完成"+count+"条数据");


        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

}
