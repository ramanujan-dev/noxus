package functionality;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.Settings;

public class SessionStoreDatabase{
    Connection con;
    PreparedStatement pss;
    PreparedStatement psc;
    public SessionStoreDatabase(){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:"+Settings.dbms+":"+Settings.cur_work_dir+"\\"+Settings.dataBaseDir+"\\"+Settings.DataBaseName);
            pss = con.prepareStatement("select * from session_id where session=?");
            psc = con.prepareStatement("insert into session_id values(?,?)");
        }catch(SQLException se){se.printStackTrace();}
        catch(ClassNotFoundException cnfe){cnfe.printStackTrace();}
    }
    public String checkSession(String session){
        try{
        pss.setString(1, session);
        ResultSet rs =  pss.executeQuery();
        if(rs.next()){
            return rs.getString("ipaddr");
        }
        }catch(SQLException se){se.printStackTrace();}
        return null;
    }
    public void createSession(String session,String ipaddr){
        try{
        psc.setString(1, session);
        psc.setString(2, ipaddr);
        psc.executeUpdate();
        }catch(SQLException se){se.printStackTrace();}
    }
    public String generateSessionId(){
        String session;
        do{
            session = "";
            for(int i =0;i<Settings.session_length;i++){
                int rn = 33+((int)(Math.random()*92));
                session+=(char)rn;
            }
        }while(checkSession(session)!=null);
        return session;
    }
}