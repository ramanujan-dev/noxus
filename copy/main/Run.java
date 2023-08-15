package main;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import functionality.MainServer;



public class Run {
    public static ClassLoader cl;
    public static Method urlMethod;
    public static void main(String[] args) {
        try{

            cl = ClassLoader.getSystemClassLoader();
            urlMethod = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            System.setProperty("illegal-access", "permit");
            File f = new File(Settings.cur_work_dir+"\\database\\sqlite-jdbc-3.36.0.3.jar");
            urlMethod.invoke(cl, f.toURI().toURL());
            Class.forName("org.sqlite.JDBC");
            System.setProperty("illegal-access", "deny");
            
            }catch(NoSuchMethodException nsme){nsme.printStackTrace();}
            catch(IllegalAccessException iae){}
            catch(InvocationTargetException ite){ite.printStackTrace();}
            catch(MalformedURLException mue){mue.printStackTrace();}
            catch(ClassNotFoundException cln){cln.printStackTrace();}
        MainServer ms = new MainServer();
        ms.start();
    }
}

class Cls{}