package utils;
import java.sql.*;

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
            //STEP 2: Register JDBC driver
            Class.forName("org.h2.jdbcx.JdbcDataSource");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql = "IF NOT EXISTS (SELECT * FROM " + DB_NAME +
                    "WHERE NAME = 'userinfo')" + "CREATE TABLE userinfo " +
                    "(id INTEGER not NULL, " + " first VARCHAR(255), " +
                    " last VARCHAR(255), " + " age INTEGER, " +
                    " email VARCHAR(255), " + " created_date DATE NOT NULL, " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
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
