package functionality;
import java.util.ArrayList;

import main.Settings;

public class ClientObjFinder<T> {
    ArrayList<Character> nextClientChar = new ArrayList<>();
    ArrayList<ClientObjFinder<T>> nextClient = new ArrayList<>();
    T clientObj = null;
    
    public ClientObjFinder<T> isPresent(char c){
        int index =  nextClientChar.indexOf(c);
        if(index!=-1)
            return nextClient.get(index);
        return null;
    }
    public String generateSessionId(){
        String session;
        do{
            session = "";
            for(int i =0;i<Settings.session_length;i++){
                int rn = 33+((int)(Math.random()*92));
                session+=(char)rn;
            }
        }while(getClientObjFinder(session)!=null);
        return session;
    }
    public T getClientObjFinder(String balanceSessionId){
        ClientObjFinder<T> ele = isPresent(balanceSessionId.charAt(0));
        if(ele==null){
            return null;
        }
        else{
            if(balanceSessionId.length()==1){
                return ele.clientObj;
            }
            return ele.getClientObjFinder(balanceSessionId.substring(1));
        }
    }
    public T CreateClientObjFinder(String balanceSessionId,T clientObj){
        ClientObjFinder<T> ele = isPresent(balanceSessionId.charAt(0));
        if(ele==null){
            if(balanceSessionId.length()==1){
                ClientObjFinder<T> n = new ClientObjFinder<T>();
                n.createClient(clientObj);
                nextClient.add(n);
                nextClientChar.add(balanceSessionId.charAt(0));
                return n.clientObj;
            }
            else{
                nextClientChar.add(balanceSessionId.charAt(0));
                ClientObjFinder<T> n = new ClientObjFinder<T>();
                T obj = n.CreateClientObjFinder(balanceSessionId.substring(1),clientObj);
                nextClient.add(n);
                return obj;
            }
        }
        else{
            if(balanceSessionId.length()==1){
                if(ele.clientObj==null)
                    ele.createClient(clientObj);
                return ele.clientObj;
            }
            return ele.CreateClientObjFinder(balanceSessionId.substring(1),clientObj);
        }
    }

    public void delete(String balanceString){

        char belong = balanceString.charAt(0);
        ClientObjFinder<T> ele = isPresent(belong);
        if(balanceString.length()==1){
            ele.clientObj = null;
            return;
        }
        ele.delete(balanceString.substring(1));
        if (nextClientChar.size()==1){
            int index =  nextClientChar.indexOf(belong);
            nextClient.remove(index);
            nextClientChar.remove(index);
        }
    }
    void createClient(T clientObj){
        this.clientObj = clientObj;
    }
}
