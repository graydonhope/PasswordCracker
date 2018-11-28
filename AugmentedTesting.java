import java.util.ArrayList;
import java.lang.StringBuilder;
import java.lang.Math;

public class AugmentedTesting{
    public static void main(String[] args){


        
        char character =  'i';
        String word = "jaisinee";
        ArrayList<Integer> test = permutations(character, word);
        String[] binaryPermutationsArray = binaryPermutations(test.size(), 'i', '1');
        ArrayList<String> allPermutatedStrings = allPermutations(word, test, binaryPermutationsArray);


        ArrayList<Integer> aPermutations = permutations('a', word);
        String[] binaryPermutationsArray2 = binaryPermutations(aPermutations.size(), 'a', '@');
        ArrayList<String> aPermutatedStrings = allPermutations(word, aPermutations, binaryPermutationsArray2);

        ArrayList<Integer> ePermutations = permutations('e', word);
        String[] binaryPermutationsArray3 = binaryPermutations(ePermutations.size(), 'e', '3');
        ArrayList<String> ePermutatedStrings = allPermutations(word, ePermutations, binaryPermutationsArray3);

        ArrayList<String> totalPermutations = new ArrayList<String>();
        totalPermutations.addAll(allPermutatedStrings); totalPermutations.addAll(aPermutatedStrings); totalPermutations.addAll(ePermutatedStrings);

        for(int i = 0; i < totalPermutations.size(); i++){
            System.out.println(totalPermutations.get(i));
        }




        /*
        for(int i = 0; i < test.size(); i ++){
            System.out.println(test.get(i));
        }
        
        for(int i = 0; i < binaryPermutationsArray.length; i++){
            System.out.println(binaryPermutationsArray[i]);
        }
        */
        
    }

    public static ArrayList<Integer> permutations(Character toPermutate, String word){
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


    public static String[] binaryPermutations(int length, char characterToSwap, char originalCharacter){
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
    public static ArrayList<String> allPermutations(String wordToPermutate, ArrayList<Integer> indexsOfChar, String[] permutationsArray){
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
}



