package functionality;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import main.Settings;
import main.Url;

public class Client {
    String responce;
    Socket s;
    String requestType;
    String url;
    String header="";
    RequestData reqD;
    

    void SetSocket_responce(Socket s,String response){
        this.s = s;
        this.responce = response;
        int fromIndex = responce.indexOf(" ");
        requestType = responce.substring(0, fromIndex);
        reqD = new RequestData();
        url = response.substring(response.indexOf("/")+1,response.indexOf(" ", fromIndex+1));
        parseResponse();
        if(requestType.equals("POST")){
            
        }
    }

    void parseResponse(){
        String lines[] = this.responce.split("\\r?\\n");
        System.out.println("Recieved "+this.requestType+" "+(url==""?"/":url)+" from "+this.s.getRemoteSocketAddress());
        int i = 1;
        for(;i<lines.length;i++){
            if(lines[i].equals("")){
                i++;
                break;
            }
            String headerSet[] = lines[i].split(":");
            reqD.addHeader(headerSet[0], headerSet[1]);
        }
        if(requestType.equals("POST")){
            int c = i;
            if(reqD.header("Content-Type").equals("application/x-www-form-urlencoded")){
                for(;i<lines.length;i++){
                    String postSet[] = lines[i].split("=");
                    reqD.addPost(postSet[0], postSet[1]);
                }
            }
                String body = "";
                for(;c<lines.length;c++){
                    body+=lines[c];
                }
                reqD.addPost("body", body);
            
        }
        else{
            
        }

    }

    void addHeader(String header){
        this.header+=header.trim()+'\n';
    }
    public void run(){
        try{
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            for(CallFunction i:Url.urls){
                if(i.toString().equals(this.url.trim())){
                    ResponceData rsd = i.start(reqD);
                    if(header!=""){
                        rsd.addHeader(this.header);
                        this.header = "";
                    }
                    dout.write(rsd.returnResponceByte());
                    dout.close();
                    return;
                }
            } 

            
            File file = new File(Settings.ProjectAbsoluteAddress+"/"+Settings.mediaDirectories[0]+"/"+this.url.trim()) ;
            if(file.exists()){
                 
                ResponceData rsd = Responce.fileResponceServer(this.url.trim(),"media");
                    if(header!=""){
                        rsd.addHeader(this.header);
                        this.header = "";
                    }
                    dout.write(rsd.returnResponceByte());
                    dout.close();
                    return;
            }
            ResponceData rsd = Responce.fileResponceServer("notfound.html","");
            if(header!=""){
            rsd.addHeader(this.header);
                this.header = "";
            }
            dout.write(rsd.returnResponceByte());
            dout.close();

            
        }catch(IOException io){io.printStackTrace();}
    }
    
}
