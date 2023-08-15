package main;
public class Settings {

    public static String cur_work_dir = ProjectAbsoluteAddress;
    public static int port = 8000;
    public static String ip_address = "127.0.0.1";
    public static String fileDirectories[] = {"main/templates"};
    public static String mediaDirectories[] = {"media"};
    public static String PageHandlerDirectories[] = {"functionality/PageHandlers"};
    public static int session_length = 24;
    public static String session_name = "session";
    public static String http_protocol_version = "1.1";
    public static String OK200 = "http/"+http_protocol_version+" 200 OK";
    public static String NF404 = "http/"+http_protocol_version+" 404 NOT FOUND";
    public static String dataBaseDir = "database";
    public static String DataBaseName = "database.db";
    public static String dbms = "sqlite";
}
