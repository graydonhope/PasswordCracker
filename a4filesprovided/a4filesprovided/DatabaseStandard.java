// Import packages
import java.util.HashMap;
import java.util.ArrayList;


public class DatabaseStandard implements DatabaseInterface{

    HashMap<String, String> passwordDictionary;
    Tester tester = new Tester();

    public static void main(String[] args){
        /*
        PasswordCracker testCracker = new PasswordCracker();
        DatabaseStandard database1 = new DatabaseStandard();
        //ArrayList<String> commonPasswords = Tester.readCommonPasswords("C:\\Users\\grayd\\OneDrive\\Documents\\University of Ottawa\\Second Year\\CSI 2110\\PasswordCracker\\a4filesprovided\\a4filesprovided\\commonPwd10K.txt");

        //testCracker.createDatabase(commonPasswords, database1);
        database1.printStatistics();
        String code = new String("F35D55B3ACF667911A679B44918F5D88B2400823");
        String discoveredPassword = testCracker.crackPassword(code, database1);
        System.out.println("Decrypt: " + code + " Password: " + discoveredPassword);
        */
    }

    public DatabaseStandard(){
         //Creates initial hash map
         passwordDictionary = new HashMap<String, String>(188011);
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
        int numberOfIndexesWhenCreated = 188011;
        System.out.println("*** Database Statistics ***" + "\n" + "Size is " + numberOfPasswords + "\n" 
        + "Initial Number of Indexes when Created " + numberOfIndexesWhenCreated + "\n" + "*** End of Database Statistics ***");

    } 

    public HashMap getPasswordDictionary(){
        return this.passwordDictionary;
    }
}