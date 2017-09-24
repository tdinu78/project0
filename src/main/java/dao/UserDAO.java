package dao;


import enums.DATABASE;

import java.sql.*;

public class UserDAO {
    public static boolean login(String user, String password) {
        String sql = "select * from userinfo where users= ? and pass= ? ";
        try (Connection conn = DriverManager.getConnection(DATABASE.DBURL.getValue(), DATABASE.USER.getValue(), DATABASE.PASS.getValue());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("users"));
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        }
    }
}