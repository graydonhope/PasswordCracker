import java.util.Arrays;

public class Test{
    public static void main(String[] args){
        MyHashMap hashMap = new MyHashMap(2);
        hashMap.put("hi", "1");
        hashMap.put("Ok", "2");
        hashMap.put("a", "3");
        hashMap.put("sdfd", "4");
        hashMap.put("wer", "5");
        hashMap.put("let", "6666");
        System.out.println(hashMap.get("wer"));


        
    }
}
