package functionality;

import java.net.Socket;

import main.Settings;

import java.io.BufferedInputStream;
import java.io.IOException;

public class ClientThread extends Thread{
    Socket s;
    ClientObjFinder<Client>sessionClient;
    SessionStoreDatabase ssdb;
    ClientThread(Socket s,ClientObjFinder<Client> sessionClient,SessionStoreDatabase ssdb){
        this.s = s;
        this.sessionClient = sessionClient;
        this.ssdb = ssdb;
    }
    public void run() {
        byte msg_byte[] = new byte[50000];
        String msg_string,session;
        int session_index;
        Client clientObj;
        
        try{
            BufferedInputStream din = new BufferedInputStream(s.getInputStream());
            
            din.read(msg_byte);
        
        msg_string = new String(msg_byte);
        if(msg_string.length()==0)
            return;
        session_index = msg_string.indexOf(Settings.session_name+"=");
        if(session_index==-1){
            session = ssdb.generateSessionId();
            ssdb.createSession(session, "127.0.0.1");
            clientObj = new Client();
            clientObj.SetSocket_responce(s,msg_string);
            clientObj.addHeader("Set-Cookie:"+Settings.session_name+"="+session);
            sessionClient.CreateClientObjFinder(session, clientObj);
        }
        else{
            session = msg_string.substring(session_index+Settings.session_name.length()+1, session_index+Settings.session_name.length()+Settings.session_length+1);
            if(sessionClient.getClientObjFinder(session)==null){
                clientObj = new Client();
                clientObj.SetSocket_responce(s,msg_string);
                sessionClient.CreateClientObjFinder(session, clientObj);
            }
            else{
                clientObj = sessionClient.getClientObjFinder(session);
                clientObj.SetSocket_responce(s,msg_string);
            }
        }
        clientObj.run();
        s.close();
        }catch(IOException io){io.printStackTrace();};
    }
}
