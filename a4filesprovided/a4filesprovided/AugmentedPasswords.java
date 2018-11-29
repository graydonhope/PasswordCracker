import java.util.ArrayList;
import java.lang.StringBuilder;
import java.lang.Math;

public class AugmentedPasswords{

    public static void main(String[] args){
        AugmentedPasswords augmentedPasswords = new AugmentedPasswords();
        ArrayList<String> test = augmentedPasswords.getAllPermutations("jaisinee");

    }

    public ArrayList<Integer> permutations(Character toPermutate, String word){
        //Get an array of indexes that the character occurs at. 
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int numberOfChars = 0;
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == toPermutate){
                indexes.add(i);
                numberOfChars++;
            }
        }
        return indexes;
    }

    public ArrayList<String> getAllPermutations(String word){
        ArrayList<Integer> test = permutations('i', word);
        String[] binaryPermutationsArray = binaryPermutations(test.size(), 'i', '1');
        ArrayList<String> allPermutatedStrings = allPermutations(word, test, binaryPermutationsArray);

        ArrayList<Integer> aPermutations = permutations('a', word);
        String[] binaryPermutationsArray2 = binaryPermutations(aPermutations.size(), 'a', '@');
        ArrayList<String> aPermutatedStrings = allPermutations(word, aPermutations, binaryPermutationsArray2);

        ArrayList<Integer> ePermutations = permutations('e', word);
        String[] binaryPermutationsArray3 = binaryPermutations(ePermutations.size(), 'e', '3');
        ArrayList<String> ePermutatedStrings = allPermutations(word, ePermutations, binaryPermutationsArray3);

        ArrayList<String> eAndaMix = combinePermutations(ePermutations, binaryPermutationsArray3, aPermutatedStrings);

        ArrayList<String> allLettersMixed = combinePermutations(test, binaryPermutationsArray, eAndaMix);

        ArrayList<String> myTest = addCapitalizeToEachWord(allLettersMixed);
        myTest = addYearToEachWord(myTest);

        /*
        for(int i =0; i < myTest.size(); i++){
            System.out.println(myTest.get(i));
        }
        */

        

        return myTest;


    }


    public String[] binaryPermutations(int length, char characterToSwap, char originalCharacter){
        //Create array of size 2^n, where n is the length of the word. This accounts for all permutations.
        int numberOfPermutations = (int) Math.pow(2, length);
        int arraySize = numberOfPermutations - length;
        String[] binaryPermutations = new String[numberOfPermutations];
        //int[] binaryPermutations = new int[numberOfPermutations];
        for(int i = 0; i < numberOfPermutations; i++){
            String binaryString = Integer.toBinaryString(i);
            if(binaryString.length() < length){
                int difference = length - binaryString.length();
                for(int k = 0; k < difference; k++){
                    binaryString = 0 + binaryString;
                }
            }
            binaryString = binaryString.replace('0', characterToSwap);
            binaryString = binaryString.replace('1', originalCharacter);
            binaryPermutations[i] = binaryString;
        }
        return binaryPermutations;
    }

    /**
     * Returns an ArrayList of Strings of all possible permutations given original word, and the character to swap it for.
     */
    public ArrayList<String> allPermutations(String wordToPermutate, ArrayList<Integer> indexsOfChar, String[] permutationsArray){
        ArrayList<String> permutations = new ArrayList<String>();
        StringBuilder newWord = new StringBuilder(wordToPermutate);
        for(int i = 0; i < permutationsArray.length; i++){
            for(int j = 0; j < indexsOfChar.size(); j++){
                newWord.setCharAt(indexsOfChar.get(j), permutationsArray[i].charAt(j));
            }
            permutations.add(newWord.toString());
            
        }
        return permutations;
    }

    public ArrayList<String> combinePermutations(ArrayList<Integer> indexesOfChar, String[] permutationsArray, ArrayList<String> permutatedStrings){
        //For all the strings in permutated string, also combine those permutations with the other letters.
        //Example: icai -> will be passed in 1cai, ica1, 1ca1, and then combine it with @ for all those cases: 1c@i, ic@1..
        ArrayList<String> combinePermutationsArray = new ArrayList<String>();
        for(int i = 0; i < permutatedStrings.size(); i++){
            
            StringBuilder newWord = new StringBuilder(permutatedStrings.get(i));
            for(int j = 0; j < permutationsArray.length; j++){
                for(int k = 0; k < indexesOfChar.size(); k++){
                    newWord.setCharAt(indexesOfChar.get(k), permutationsArray[j].charAt(k));
                }
                combinePermutationsArray.add(newWord.toString());
            }
        }
        return combinePermutationsArray;
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
    /**
     * Adds a capital letter to each case of the list of permutated words. Will also add lowercase if the original was capitalized.
     */
    private ArrayList<String> addCapitalizeToEachWord(ArrayList<String> permutatedLetters){
        ArrayList<String> capitalizedWords = new ArrayList<String>();
        for(int i = 0; i < permutatedLetters.size(); i++){
            capitalizedWords.add(permutatedLetters.get(i));
            if(Character.isLetter(permutatedLetters.get(i).charAt(0))){
                if(Character.isUpperCase(permutatedLetters.get(i).charAt(0))){
                    char c[] = permutatedLetters.get(i).toCharArray();
                    c[0] = Character.toLowerCase(c[0]);
                    String lowerCaseFirstChar = new String(c);
                    capitalizedWords.add(lowerCaseFirstChar);
                }
                capitalizedWords.add(capitalizeFirstChar(permutatedLetters.get(i)));
            }
        }

        return capitalizedWords;
    }

    private ArrayList<String> addYearToEachWord(ArrayList<String> permutatedWords){
        ArrayList<String> yearAdditionToWord = new ArrayList<String>();
        for(int i = 0; i < permutatedWords.size(); i++){
            yearAdditionToWord.add(permutatedWords.get(i));
            yearAdditionToWord.add(permutatedWords.get(i) + "2018");
        }

        return yearAdditionToWord;
    }
}



