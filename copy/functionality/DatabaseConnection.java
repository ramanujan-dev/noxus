package functionality;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import main.Settings;

public class DatabaseConnection {
    
    public static Connection getConnection(){
        try{
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:"+Settings.dbms+":"+Settings.cur_work_dir+"\\"+Settings.dataBaseDir+"\\"+Settings.DataBaseName);
        return con;
        }catch(SQLException se){se.printStackTrace();}
        catch(ClassNotFoundException cnfe){cnfe.printStackTrace();}
        return null;
    }
}
