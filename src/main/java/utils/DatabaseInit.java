package utils;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by tdinu on 02-Jun-17.
 */
public class DatabaseInit {
    static final String JDBC_DRIVER = "org.h2.jdbcx.JdbcDataSource";
    static final String DB_NAME = "project0";
    static final String DB_URL = "jdbc:h2:~/" + DB_NAME;
    static final String USER = "admin";
    static final String PASS = "admin";


    public void createTables() {

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.h2.jdbcx.JdbcDataSource");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
//Create table userinfo and initiate admin admin user
            String sql = "CREATE TABLE IF NOT EXISTS PUBLIC.userinfo"  +
                    "(user VARCHAR(20) not NULL, pass VARCHAR(20) not NULL, " +
                    " first VARCHAR(20), last VARCHAR(20), " + " age INTEGER, " +
                    " email VARCHAR(50) NOT NULL, " + " created_date DATE NOT NULL, " +
                    " PRIMARY KEY ( user ))";
            stmt.executeUpdate(sql);
            String sql2 = "SELECT * FROM PUBLIC.userinfo";
             ResultSet x = stmt.executeQuery(sql2);
             if(x.absolute(1)!=true)
                {
                java.sql.Date today = new java.sql.Date(new Date().getTime());
                 String sql3 = "INSERT INTO PUBLIC.USERINFO (USER, PASS, EMAIL, CREATED_DATE) VALUES ('admin', 'admin', 'admin@admin.ro', today)";
                 stmt.executeUpdate(sql3);
             }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }//end main
}
