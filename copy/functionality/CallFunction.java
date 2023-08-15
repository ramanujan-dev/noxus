package functionality;

public class CallFunction{
    String url;
    UrlFunctionCaller urlfunction;
    public CallFunction(String url,UrlFunctionCaller urlfunction){
        this.url = url;
        this.urlfunction = urlfunction;
    }
    public String toString(){
        return url;
    }
    ResponceData start(RequestData request){
        return urlfunction.run(request);
    }
}
