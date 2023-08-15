package functionality;

import java.util.ArrayList;

public class UrlDefine {
    public static ArrayList<CallFunction> urls = new ArrayList<>();
    public static void add(String url,UrlFunctionCaller urlclass){
        urls.add(new CallFunction(url, urlclass));
    }
}
