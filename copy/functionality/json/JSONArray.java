package functionality.json;

import java.util.ArrayList;

public class JSONArray {
    ArrayList<JSONElement> json_array = new ArrayList<>();
    public void add(String value){
        JSONElement je = new JSONElement(value);
        add(je);
    }
    public void add(int value){
        JSONElement je = new JSONElement(value);
        add(je);
    }
    public void add(float value){
        JSONElement je = new JSONElement(value);
        add(je);
    }
    public void add(){
        JSONElement je = new JSONElement();
        add(je);
    }
    public void add(boolean value){
        JSONElement je = new JSONElement(value);
        add(je);
    }
    public void add(JSONArray value){
        JSONElement je = new JSONElement(value.json_array);
        add(je);
    }
    public void add(JSON value){
        JSONElement je = new JSONElement(value);
        add(je);
    }
    void add(JSONElement je){
        json_array.add(je);
    }
}
