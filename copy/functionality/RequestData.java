package functionality;

import java.util.ArrayList;

public class RequestData {
    ArrayList<String> keyPost = new ArrayList<>();
    ArrayList<String> valuePost = new ArrayList<>();
    ArrayList<String> keyHeader = new ArrayList<>();
    ArrayList<String> valueHeader = new ArrayList<>();

    void addHeader(String key,String value){
        keyHeader.add(key.strip());
        valueHeader.add(value.strip());
    }
    void addPost(String key,String value){
        keyPost.add(key.strip());
        valuePost.add(value.strip());
    }

    public String header(String key){
        for(int i =0;i<keyHeader.size();i++){
            if(keyHeader.get(i).equals(key)){
                return valueHeader.get(i);
            }
        }
        return null;
    }
    public String post(String key){
        for(int i =0;i<keyPost.size();i++){
            if(keyPost.get(i).equals(key)){
                return valuePost.get(i);
            }
        }
        return null;
    }

}
