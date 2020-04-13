import java.util.ArrayList;
import java.util.HashMap;

/**
 * This program implements an application that
 * simply displays values of given text divided to characters
 * and words contains this characters
 * to the standard output.
 *
 * @author Bartłomiej Sarata
 * @version 1.0
 * @since 2019-08-07
 */
public class Controller {

    /**
     * Main method display
     *
     * @param args get text from command line
     * @return void
     */
    public static void main(String[] args) {
        //text from given mail
        String text = "ala ma kota, kot koduje w Javie kota";
        // if command line arg is not null
        if ((args != null) && (args.length > 0)) {
            text = args[0];
        }

        // Creating a HashMap containing Character and Integer
        // as a key and occurrences as  a value
        HashMap<Character, Integer> charCountMap
                = new HashMap<>();
        // Creating a HashMap containing String and Integer
        // as a key and occurrences as  a value
        HashMap<String, Integer> checkWorldMap = new HashMap<>();

        // Creating a HashMap containing String and ArrayList<String>
        // as a key and occurrences as  a value
        HashMap<Character, ArrayList<String>> compareCharWithWordMap = new HashMap<>();

        //call checkWorld method
        checkWorldMap = checkWorld(text);
        //call characterCount method
        charCountMap = characterCount(text);
        //call compareCharWithWordMap method
        compareCharWithWordMap = compareCharWithWord(charCountMap, checkWorldMap);
        //display values from method
        compareCharWithWordMap.forEach((key, value) -> {
            System.out.println(key + " " + value);
        });

    }

    /**
     * This method is used to display values of characters, single repeted
     *
     * @param text This is the first paramter used to get value of given text to check
     * @return hashMap
     */
    static HashMap characterCount(String text) {

        // Creating a HashMap containing char
        // as a key and occurrences as  a value
        HashMap<Character, Integer> hashMap
                = new HashMap<Character, Integer>();

        //pattern to delete in text given from mail
        text = text.replace(",", "");
        text = text.replaceAll("\\s", "");

        // Converting given string to char array
        char[] strArray = text.toCharArray();

        // checking each char of strArray
        for (char c : strArray) {
            c = Character.toLowerCase(c);
            if (hashMap.containsKey(c)) {

                // If char is present in hashMap,
                // incrementing it's count by 1
                hashMap.put(c, hashMap.get(c) + 1);
            } else {

                // If char is not present in hashMap,
                // putting this char to hashMap with 1 as it's value
                hashMap.put(c, 1);
            }
        }
        //return hashMap
        return hashMap;
    }

    /**
     * This method is used to display values of words, single repeted
     *
     * @param text This is the first paramter used to get value of given text to check
     * @return hashMap
     */
    static HashMap checkWorld(String text) {
        // Creating a HashMap containing char
        // as a key and occurrences as  a value
        HashMap<String, Integer> hashMap = new HashMap<>();
        //pattern to delete in text given from mail
        text = text.replace(",", "");
        //check each word and put seperated by " " to hashMap container
        for (String word : text.split(" ")) {
            Integer i = hashMap.get(word);
            if (i == null) {
                hashMap.put(word, 1);
            } else {
                hashMap.put(word, i + 1);
            }
        }
        //return hashMap
        return hashMap;
    }

    /**
     * This method is used to display values character and words, that contain this character
     *
     * @param characters This is the first paramter used to display values of characters single repeted
     * @param words      This is second paramter  used to display values of words single repeted
     * @return hashMap
     */
    static HashMap compareCharWithWord(HashMap characters, HashMap words) {
        // Creating a HashMap containing String and ArrayList<String>
        // as a key and occurrences as  a value
        HashMap<Character, ArrayList<String>> hashMap = new HashMap<>();

        //comapre single char with words
        characters.forEach((key, value) -> {
            // Creating a ArrayList containing String
            ArrayList<String> arrayList = new ArrayList<String>();
            words.forEach((key2, value2) -> {
                //if word contain character set next value to hashMap
                if (key2.toString().contains(key.toString())) {
                    arrayList.add((String) key2);
                }
                hashMap.put((Character) key, arrayList);
            });

        });
        //return hashMap
        return hashMap;
    }
}