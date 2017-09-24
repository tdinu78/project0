package utils;
import enums.DATABASE;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;

/**
 * Created by tdinu on 02-Jun-17.
 */
public class DatabaseInit {

    public void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS PUBLIC.userinfo"  +
                "(users VARCHAR(20) not NULL, pass VARCHAR(20) not NULL, " +
                " first VARCHAR(20), last VARCHAR(20), " + " age INTEGER, " +
                " email VARCHAR(50) NOT NULL, " + " created_date TIMESTAMPTZ NOT NULL, " +
                " PRIMARY KEY ( users ))";
        try (Connection conn = DriverManager.getConnection(DATABASE.DBURL.getValue(), DATABASE.USER.getValue(), DATABASE.PASS.getValue());
             Statement stmt = conn.createStatement()){
//Create table userinfo and initiate admin admin user
            stmt.executeUpdate(sql);
            String sql2 = "select * from userinfo where users= 'admin' and pass= 'carbon14'";
             ResultSet x = stmt.executeQuery(sql2);
             if(!x.next())
                {
                Timestamp today = Timestamp.from(Instant.now());
                 String sql3 = "INSERT INTO PUBLIC.USERINFO (USERS, PASS, EMAIL, CREATED_DATE) VALUES ('admin', 'carbon14', 'admin@admin.ro', '"+today+"')";
                 stmt.executeUpdate(sql3);
             }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }
}
