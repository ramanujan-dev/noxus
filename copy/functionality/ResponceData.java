package functionality;

public class ResponceData {
    private String header = "";
    private String body = "";
    private String status_line="";
    private byte[] bodyBytes = null;
    public void addHeader(String header){
        this.header+=header.trim()+'\n';
    }
    public void addBody(String body){
        this.body+=body.trim()+'\n';
    }
    public void addBodyByte(byte[] b){
        if(bodyBytes==null){
            bodyBytes = b;
        }
        else{
            int len1 = bodyBytes.length;
            int len2 = b.length;

            byte cp[] = new byte[len1+len2];
            System.arraycopy(bodyBytes, 0, cp, 0, len1);
            System.arraycopy(b, 0, cp, len1, len2);

            bodyBytes = cp;
        }
    }
    public byte[] addByteArray(byte[] b){
        if(bodyBytes==null){
            return b;
        }
        else{
            int len1 = bodyBytes.length;
            int len2 = b.length;

            byte cp[] = new byte[len1+len2];
            System.arraycopy(b, 0, cp, 0, len2);
            System.arraycopy(bodyBytes, 0, cp, len2, len1);

            return cp;
        }
    }
    public void addStatusLine(String status_line){
        this.status_line = status_line;
    }
    public String returnHeaders(){
        return this.header+'\n';
    }
    public String returnHeaders(String status_line){
        return status_line+'\n'+this.header+'\n';
    }
    public String returnBody(){
        return this.body;
    }
    public byte[] returnBodyBytes(){
        return bodyBytes;
    }
    public byte[] returnResponceByte(){
        return addByteArray((status_line+'\n'+header+'\n').getBytes());
    }
    public String returnResponce(String status_line){
        return status_line+'\n'+header+'\n'+body;
    }
    public String returnResponce(){
        return this.status_line+'\n'+header+'\n'+body;
    }
}
