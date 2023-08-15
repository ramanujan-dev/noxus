package functionality.json;
import java.util.ArrayList;


public class JSONElement{
    String type;
    String dataS;
    int dataI;
    Boolean dataB;
    JSON dataJ;
    Float dataF;

    ArrayList<JSONElement> dataJE = null;
    
    public JSONElement(String element){
        this.dataS = element;
        this.type = "str";
    }
    public JSONElement(int element){
        this.dataI = element;
        this.type = "int";
    }
    public JSONElement(Boolean element){
        this.dataB = element;
        this.type = "bol";

    }
    public JSONElement(JSON element){
        this.dataJ = element;
        this.type = "jso";
    }
    public JSONElement(float element){
        this.dataF = element;
        this.type = "flt";
    }
    public JSONElement(){
        this.type = "nul";
    }
    public JSONElement(ArrayList<JSONElement> element){
        this.dataJE = element;
        this.type = "arr";
    }
    public boolean equals(JSONElement j){
        JsonComArray j1 = new JsonComArray();
        JsonComArray j2 = new JsonComArray();
        this.addTO(j1);
        j.addTO(j2);
        if(j1.json_output.equals(j2.json_output)){
            return true;
        }
        return false;
    }
    public void addTO(JsonComArray j){
        if(type.equals("str")){
            j.addToJson("\""+dataS+"\"");
        }
        else if(type.equals("int")){
            j.addToJson(""+dataI);
        }
        else if(type.equals("flt")){
            j.addToJson(""+dataF);
        }
        else if(type.equals("bol")){
            j.addToJson(""+dataB);
        }
        else if(type.equals("nul")){
            j.addToJson("null");
        }
        else if(type.equals("jso")){
            j.addToJson(dataJ.compile());
        }
        else if(type.equals("arr")){
            JsonComArray arr = new JsonComArray();
            arr.addToJson("[");
            for(int i =0;i<dataJE.size();i++){
                dataJE.get(i).addTO(arr);
                if(i+1!=dataJE.size())
                    arr.addToJson(",");
            }
            arr.addToJson("]");
            j.addToJson(arr.json_output);
        }   
    }
}

