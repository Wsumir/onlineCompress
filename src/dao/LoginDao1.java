package dao;

import Utils.JDBCUtils;
import entity.User;

import java.sql.*;

public class LoginDao1 {

    public static int login(User user)	//登陆
    {
        try {
            Connection conn= JDBCUtils.getConnection();

            String SQL = "select count(*) from test2 where id=? and name=?";
            PreparedStatement pstmt=conn.prepareStatement(SQL);
            pstmt.setString(1, user.getLogin_name());
            pstmt.setString(2, user.getLogin_pw());

            ResultSet rs= pstmt.executeQuery();
            int result=0;
            if(rs.next())
            {
                result = rs.getInt(1);
            }
            if(result>0)
            {
                return 1;
            }else
            {
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
