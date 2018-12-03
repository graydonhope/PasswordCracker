import java.util.HashMap;

public class DatabaseMine implements DatabaseInterface{

    public int N; // this is a prime number that gives the number of addresses
    public MyHashMap passwordDictionary;
    public int numberOfProbes;
    
    // these constructors must create your hash tables with enough positions N
    // to hold the entries you will insert; you may experiment with primes N
    public DatabaseMine(){
        //Pick suitable N value without knowing the number of words going into the dictionary.
        this.N = 237509;
        this.numberOfProbes = 0;
        this.passwordDictionary = new MyHashMap(this.N);      // Using my object
    }

    public DatabaseMine(int N_given){
        if(N_given < 1){
            this.N = 1;
        }
        this.N = N_given;
        this.numberOfProbes = 0;
        this.passwordDictionary = new MyHashMap(this.N);   // Using my object
    }

    // Saves password to the Hashmap. Remember the order when creating hash map.
    public String save(String plainPassword, String encryptedPassword){
        if(plainPassword == null || encryptedPassword == null){
            throw new IllegalArgumentException();
        }
        String valueAtKey = passwordDictionary.get(encryptedPassword);
        passwordDictionary.put(encryptedPassword, plainPassword);
        return valueAtKey;
    }
	
	// returns plain password corresponding to encrypted password
	public String decrypt(String encryptedPassword){
        if(encryptedPassword == null){
            throw new IllegalArgumentException();
        }
        return passwordDictionary.get(encryptedPassword);
    } 

    public int getNValue(){
        return this.N;
    }

    // returns the number of password pairs stored in the database
    public int size(){
        return passwordDictionary.size();
    }

    public int hashFunction(String key) {
        if(key == null){
            throw new IllegalArgumentException();
        }
        int address=key.hashCode()%N;
        return (address>=0)?address:(address+N);
    }

    public double getLoadFactor(){
        return passwordDictionary.getLoadFactor();
    }

    public double getNumberOfProbes(){
        return passwordDictionary.getAverageNumberOfProbes();
    }

    public int getNumberOfDisplacements(){
        return passwordDictionary.getNumberOfDisplacements();
    }

    public void printStatistics() {
        // important statistics must be collected (here or during construction) and
        // printed here: size, number of indexes, load factor, average number of probes
        // and average number of displacements (see output example below)
        int numberOfPasswords = passwordDictionary.size();
        int numberOfIndexesWhenCreated = getNValue();
        double loadFactor = getLoadFactor();
        double numberOfProbes = getNumberOfProbes();
        double numberOfDisplacements = getNumberOfDisplacements();
        System.out.println("*** Database Statistics ***" + "\n" + "Size is " + numberOfPasswords + "\n" 
        + "Initial Number of Indexes when Created " + numberOfIndexesWhenCreated + "\n"
        + "Load Factor is " + loadFactor + "\n" + "Average Number of Probes is " + numberOfProbes +"\n"
        + "Number of displacements (due to collisions) " + numberOfDisplacements + "\n"
        + "*** End of Database Statistics ***");
    }
}