public class Entry{
    String key;
    String value;

    public Entry(String key, String value){
        if(key == null || value == null){
            throw new IllegalArgumentException();
        }
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
        if(newKey == null){
            throw new IllegalArgumentException("New key cannot be null");
        }
        this.key = newKey;
    }

    public void setValue(String newValue){
        if(newValue == null){
            throw new IllegalArgumentException("New value cannot be null");
        }
        this.value = newValue;
    }
}