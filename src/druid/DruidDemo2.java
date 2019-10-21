package druid;

import Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DruidDemo2 {

    public static void main(String[] args) {

        try {
            Connection conn= JDBCUtils.getConnection();
            String SQL = "select * from test2";
            PreparedStatement pstmt=conn.prepareStatement(SQL);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next())
            {
                System.out.print(rs.getInt(1)+"  ");
                System.out.println(rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
