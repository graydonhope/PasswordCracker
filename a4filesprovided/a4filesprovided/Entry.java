public class Entry{
    String key;
    String value;

    public Entry(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey(){
        return this.key;
    }

    public String getValue(){
        return this.value;
    }

    public void setKey(String newKey){
        this.key = newKey;
    }

    public void setValue(String newValue){
        this.value = newValue;
    }
}