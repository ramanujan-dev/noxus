package main;

import functionality.UrlDefine;

public class Url extends UrlDefine {
    public Url(){
        add("", new Home());
        add("favicon.ico", new Favicon());
    }    
}
