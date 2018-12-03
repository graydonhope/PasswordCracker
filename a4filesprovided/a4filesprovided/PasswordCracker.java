// Importing Packages
import java.util.ArrayList;
import java.io.*; 
import java.util.HashMap;


public class PasswordCracker{

    Sha1 sha1Encrypter;
    AugmentedPasswords augmentPasswords = new AugmentedPasswords();
    ArrayList<String> augmentedPasswords = new ArrayList<String>();


    
    public void createDatabase(ArrayList<String> commonPasswords, DatabaseInterface database){
        augmentedPasswords = augmentPasswords(commonPasswords);
        sha1Encrypter = new Sha1();
        int passwordListLength = augmentedPasswords.size();
        for(int i = 0; i < passwordListLength; i++){
            // Hash password using SHA256 encyption. Add the hashed password and original password to the dictionary (Key being the hashed password)
            try{
                String hashedPassword = sha1Encrypter.hash(augmentedPasswords.get(i));
                database.save(augmentedPasswords.get(i), hashedPassword);
            }            
            catch(UnsupportedEncodingException e){
               e.printStackTrace();
               System.out.println("Unable to save password into dictionary");
            }
        }
    }


    public String crackPassword(String encryptedPassword, DatabaseInterface database){    
        // Uses database to crack encrypted password, returning the originial password
        if(database.decrypt(encryptedPassword) == null){
            return "";
        }
        else{
            return database.decrypt(encryptedPassword);
        }
    }


    private ArrayList<String> augmentPasswords(ArrayList<String> commonPasswords){
        // This is the main part of the program. If the password is not in the database, it will be null.
        //At each password in the List, send to each method and add in the augmented word

        for(int i = 0; i < commonPasswords.size(); i++){
            augmentedPasswords.addAll(augmentPasswords.getAllPermutations(commonPasswords.get(i)));
        }

        return augmentedPasswords;
    }

    private String capitalizeFirstChar(String commonPassword){
        //Capitalizes the first letter
        if(commonPassword == null){
            throw new IllegalArgumentException();
        }

        else {
            return commonPassword.substring(0,1).toUpperCase() + commonPassword.substring(1);     
        }
    }

    private ArrayList<String> replaceA(ArrayList<String> commonPasswords){

        if(commonPasswords == null){
            throw new IllegalArgumentException();
        }

        else{
            for(int i = 0; i < commonPasswords.size(); i++){
                String password = commonPasswords.get(i);
                String replacedChars = password.replace('a', '@');
                replacedChars = replacedChars.replace('A', '@');
                commonPasswords.add(i, replacedChars);
            }    
        }
        return commonPasswords;
    }

    
    private ArrayList<String> replaceI(ArrayList<String> commonPasswords){

        if(commonPasswords == null){
            throw new IllegalArgumentException();
        }

        else{
            for(int i = 0; i < commonPasswords.size(); i++){
                String password = commonPasswords.get(i);
                String replacedChars = password.replace('i', '1');
                replacedChars = replacedChars.replace('I', '1');
                commonPasswords.add(i, replacedChars);
            }    
        }
        return commonPasswords;
    }


    private ArrayList<String> replaceE(ArrayList<String> commonPasswords){

        if(commonPasswords == null){
            throw new IllegalArgumentException();
        }

        else{
            for(int i = 0; i < commonPasswords.size(); i++){
                String password = commonPasswords.get(i);
                String replacedChars = password.replace('e', '3');
                replacedChars = replacedChars.replace('E', '3');
                commonPasswords.add(i, replacedChars);
            }    
        }
        return commonPasswords;
    }

    private ArrayList<String> addYear(ArrayList<String> commonPasswords){
        
        if(commonPasswords == null){
            throw new IllegalArgumentException();
        }

        else{
            for(int i = 0; i < commonPasswords.size(); i++){
                String password = commonPasswords.get(i);
                String addYearToPassword = password + "2018";
                commonPasswords.add(i, addYearToPassword);
            }    
        }
        return commonPasswords;
    }
}