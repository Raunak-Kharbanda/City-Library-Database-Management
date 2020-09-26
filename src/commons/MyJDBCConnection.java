package commons;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyJDBCConnection {        
    static Connection con = null;
    public static Connection getConnection()
    {
        if (con != null) return con;
        // get db, user, pass from settings file
        String dbName = "Db_Name?serverTimezone=UTC";
        String userName = "Enter DB user name";
        String password = "Enter DB password";
        return getConnection(dbName, userName, password);
    }

    private static Connection getConnection(String db_name,String user_name,String password)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db_name+"&user="+user_name+"&password="+password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return con;        
    }
} 