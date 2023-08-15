package functionality;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import functionality.json.JSON;
import main.Settings;

public class Responce {
    public static ResponceData fileResponce(String filename){
        return fileResponceServer(filename, "template");
    }
    public static ResponceData fileResponceServer(String filename,String Wspace){
        ResponceData rd = new ResponceData();
        try{
            File f;
            int i = 0;
            String[] searchDir;
            
            if(Wspace.equals("template")){
                searchDir = Settings.fileDirectories;
            }
            else if(Wspace.equals("media")){
                searchDir = Settings.mediaDirectories;
            }
            else{
                searchDir = Settings.PageHandlerDirectories;
            }

            do{
                f = new File(Settings.cur_work_dir+"\\"+searchDir[i]+"\\"+filename);
                i++;
            }while(!f.exists() && i<searchDir.length);
            FileInputStream fi = new FileInputStream(Settings.cur_work_dir+"\\"+searchDir[i-1]+"\\"+filename);
            long length = f.length();
            byte b[] = new byte[(int)length];
            fi.read(b);
            rd.addStatusLine(Settings.OK200);
            if(filename.substring(filename.indexOf('.')+1).equals("html")){
                rd.addHeader("Content-Type:text/html");
            }
            rd.addHeader("Content-Length:"+length);
            rd.addBodyByte(b);
            fi.close();
        }catch(IOException io){io.printStackTrace();}
        return rd;
    }
    public static ResponceData downloadResponce(String filename){
        ResponceData rd = new ResponceData();
        try{
            File f;
            int i = 0;
            do{
                f = new File(Settings.cur_work_dir+"\\"+Settings.mediaDirectories[i]+"\\"+filename);
                i++;
            }while(!f.exists() && i<Settings.fileDirectories.length);
            FileInputStream fi = new FileInputStream(Settings.cur_work_dir+"\\"+Settings.mediaDirectories[i-1]+"\\"+filename);
            long length = f.length();
            byte b[] = new byte[(int)length];
            fi.read(b);
            rd.addStatusLine(Settings.OK200);
            rd.addHeader("Content-Type:application/octet-stream");
            rd.addHeader("Content-Length:"+(length));
            rd.addHeader("Content-Disposition:attachment;filename=\""+filename+"\"");
            rd.addBodyByte(b);
            fi.close();
        }catch(IOException io){io.printStackTrace();}

        return rd;
    }
    public static ResponceData JSONResponce(JSON json){
        ResponceData rd = new ResponceData();
        String data = json.compile();
        rd.addStatusLine(Settings.OK200);
        rd.addHeader("Content-Type:application/json");
        rd.addHeader("Content-Length:"+data.length());
        rd.addBodyByte(data.getBytes());
        return rd;
    }
}
