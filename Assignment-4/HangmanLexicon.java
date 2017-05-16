/*
 * File: HangmanLexicon.java
 * -------------------------
 * Creates an ArrayList from HangmanLexicon.txt.
 * 
 */

import acm.util.*;
import java.io.*;
import java.util.*;
 
public class HangmanLexicon {
 
    private ArrayList<String> hangmanArrayList = new ArrayList<String>();
 
    public HangmanLexicon() {
    	//Creates a buffered reader, then populates the hangmanArrayList with HangmanLexicon.txt
        try {
            BufferedReader wordReader = new BufferedReader(new FileReader("HangmanLexicon.txt"));
            while(true) {
                String word = wordReader.readLine();
                if(word == null) break;
                hangmanArrayList.add(word);
            }
            wordReader.close();
        } 
        //Catches exceptions...
        catch (IOException ex) {
        	throw new ErrorException(ex);
        }
    }
 
/** Returns the word at the specified index. */
    public String getWord(int index) {
        return hangmanArrayList.get(index);
    }
 
    /** Returns the number of words in the lexicon. */
    public int getWordCount() {
        return hangmanArrayList.size();
    }
}
