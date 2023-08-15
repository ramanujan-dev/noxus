package main;
import functionality.RequestData;
import functionality.Responce;
import functionality.ResponceData;
import functionality.UrlFunctionCaller;
class Home implements UrlFunctionCaller{
    public ResponceData run(RequestData request) {
        ResponceData rd = Responce.fileResponce("welcome.html");
        return rd;
    }
}

class Favicon implements UrlFunctionCaller{
    public ResponceData run(RequestData request){
        ResponceData rd = new ResponceData();
        rd.addStatusLine(Settings.NF404);
        return rd;
    }
}