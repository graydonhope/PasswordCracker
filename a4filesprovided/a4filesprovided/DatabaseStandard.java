// Import packages
import java.util.HashMap;
import java.util.ArrayList;


public class DatabaseStandard implements DatabaseInterface{

    HashMap<String, String> passwordDictionary;

    public static void main(String[] args){
        PasswordCracker testCracker = new PasswordCracker();
        DatabaseStandard database1 = new DatabaseStandard();
        ArrayList<String> commonPasswords = new ArrayList<String>();
        commonPasswords.add("123456");
        commonPasswords.add("password");
        commonPasswords.add("12345678");
        commonPasswords.add("brady");
        testCracker.createDatabase(commonPasswords, database1);
        database1.printStatistics();
    }

    public DatabaseStandard(){
         //Creates initial hash map
         passwordDictionary = new HashMap<String, String>();
    }
    

    public String save(String plainPassword, String encryptedPassword){
        // Stores plainPassword and corresponding encryptedPassword in a map.
	    // if there was a value associated with this key, it is replaced, 
	    // and previous value returned; otherwise, null is returned
	    // The key is the encryptedPassword the value is the plainPassword
        String valueAtKey = passwordDictionary.get(encryptedPassword);
        passwordDictionary.put(encryptedPassword, plainPassword);
        return valueAtKey;
    }
 
	public String decrypt(String encryptedPassword){
        // returns plain password corresponding to encrypted password
        return passwordDictionary.get(encryptedPassword);
    }
	
	
    public int size(){
        // returns the number of password pairs stored in the database
        return passwordDictionary.size();
    } 
	
    public void printStatistics(){
        // print statistics based on type of Database
        int numberOfPasswords = passwordDictionary.size();
        int numberOfIndexesWhenCreated = -9999;
        System.out.println("*** Database Statistics ***" + "\n" + "Size is " + numberOfPasswords + "\n" 
        + "Initial Number of Indexes when Created " + numberOfIndexesWhenCreated + "\n" + "*** End of Database Statistics ***");

    } 

    public HashMap getPasswordDictionary(){
        return this.passwordDictionary;
    }
}