package functionality;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import main.Settings;
import main.Url;

public class MainServer extends Thread{
    public void run(){
        try{
            new Url();
            ServerSocket ss = new ServerSocket(Settings.port);
            System.out.println("Running on port : "+Settings.port);
            boolean accept = true;
            ClientThread clientT;
            ClientObjFinder<Client>sessionClient = new ClientObjFinder<Client>();
            SessionStoreDatabase ssdb = new SessionStoreDatabase();
            while (accept) {
                Socket s = ss.accept();
                clientT = new ClientThread(s,sessionClient,ssdb);
                clientT.start();
            }

            ss.close();
        }catch(IOException io){io.printStackTrace();}
    }
}

