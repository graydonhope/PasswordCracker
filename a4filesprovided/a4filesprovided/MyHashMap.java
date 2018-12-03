/* Simple implementation of a hash table using linear probing for collisions. The hash map will have a String for a key and 
 a String for the value. */

 import java.util.*;

public class MyHashMap{

    private int N;
    private int numberOfKeys;
    private Entry[] map;
    private int numberOfProbes;
    private int numberOfDisplacement;

    public MyHashMap(){
        // Default size for hash table
        N = 16;
        map = new Entry[N];
        numberOfProbes = 0;
        numberOfKeys = 0;
    }

    public MyHashMap(int hashMapSize){
        N = hashMapSize;
        map = new Entry[N];
        numberOfProbes = 0;
        numberOfKeys = 0;
    }


    public int size(){
        return this.numberOfKeys;
    }

    public String get(String key){
        // Returns null if not found
        // No collision
        if(key == null){
            throw new IllegalArgumentException("key cannot be null");
        }
      
        if(map[hashFunction(key)] != null){
            if(map[hashFunction(key)].getKey().equals(key)){
                return map[hashFunction(key)].getValue();
            }
            // Finding correct key through linear probing
            else{
                for(int i = 0; i < N; i++){
                    if(map[( hashFunction(key) + i ) % N ] != null){
                        return map[(hashFunction(key) + i) % N ].getValue();
                    }
                    else{
                        return null;
                    }
                }
            }
        }
        else{
            return null;
        }
        return null;
    }

    public int hashFunction(String key) {
        if(key == null){
            throw new IllegalArgumentException("Key cannot be null");
        }
        int address=key.hashCode()%N;
        return (address>=0)?address:(address+N);
    }

    public double getLoadFactor(){
        double loadfactor = (double) this.numberOfKeys / this.N;
        return loadfactor;
    }

    public int getMapSize(){
        return this.N;
    }

    public int getNumberOfProbes(){
        return this.numberOfProbes;
    }

    public int getNumberOfDisplacements(){
        return this.numberOfDisplacement;
    }

    public double getAverageNumberOfProbes(){
        double averageProbes = (double) this.getNumberOfProbes() / this.numberOfKeys;
        return averageProbes;
    }

    public void put(String key, String value){
        if(key == null || value == null){
            throw new IllegalArgumentException();
        }
        int locationToPlace = hashFunction(key);

        if(map[locationToPlace] != null){
            // Collision
            if(map[locationToPlace].getKey().equals(key)){
                //Duplication - replace values
                this.numberOfProbes++;
                Entry newEntry = new Entry(key, value);
                map[locationToPlace] = newEntry;
            }
            else{
                int newLocation = newLocation(locationToPlace, key);
                Entry newEntry = new Entry(key, value);
                map[newLocation] = newEntry;
                this.numberOfDisplacement++;
                this.numberOfKeys++;
            }
            
        }

        else{
            // Quick add
            this.numberOfProbes++;
            Entry newEntry = new Entry(key, value);
            map[locationToPlace] = newEntry;
            this.numberOfKeys++;

        }
    }

    private int newLocation(int locationToPlace, String key){
        // Find a new location to place value due to a collision.
        // If the array is full, it will copy the value of the current map into a new array which is twice the size, and recursively call
        // the method again with the same parameters.
        if(key == null){
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < this.N; i++){
            if( map[(locationToPlace + i) % N] == null || map[(locationToPlace + i) % N].getKey().equals(key)){
                // Successful new location
                this.numberOfProbes++;
                return ((locationToPlace + i) % N);
            }
            else{
                this.numberOfProbes++;
            }
        }  
        // If it got to this point, it means it did not find a new location, so resize is required.
        resize(); 
        return newLocation(locationToPlace, key);   
    }

    private void resize(){
        int newSize = this.N * 2;
        Entry[] newArray = Arrays.copyOf(map, newSize);
        map = newArray;
        this.N = newSize;
    }   
}