package functionality.json;

import java.util.ArrayList;



public class JSON extends JsonComArray{
    ArrayList<String> key = new ArrayList<>();
    ArrayList<JSONElement> value = new ArrayList<>();

    public void add(String key,String value){
        JSONElement je = new JSONElement(value);
        add(key,je);
    }
    public void add(String key,int value){
        JSONElement je = new JSONElement(value);
        add(key,je);
    }
    public void add(String key,float value){
        JSONElement je = new JSONElement(value);
        add(key,je);
    }
    public void add(String key){
        JSONElement je = new JSONElement();
        add(key,je);
    }
    public void add(String key,boolean value){
        JSONElement je = new JSONElement(value);
        add(key,je);
    }
    public void add(String key,JSON value){
        JSONElement je = new JSONElement(value);
        add(key,je);
    }
    public void add(String key,JSONArray value){
        JSONElement je = new JSONElement(value.json_array);
        add(key,je);
    }

    public void add(String key,JSONElement value){

        for(int i =0;i<this.key.size();i++)
            if(this.key.get(i).equals(key)){
                this.value.set(i, value);
                return;
            }
        this.key.add(key);
        this.value.add(value);
    }
    public String compile(){
        json_output = "{";
        for(int i =0;i<key.size();i++){
            json_output +="\""+this.key.get(i)+"\"";
            json_output+=":";
            this.value.get(i).addTO(this);
            if(i+1!=key.size()){
                json_output+=",";
            }
        }
        json_output += "}";
        return json_output;
    }
}
