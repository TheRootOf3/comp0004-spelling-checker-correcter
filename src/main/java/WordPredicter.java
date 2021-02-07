// Instances of this class are used to predict and choose a single word. Here is also implemented the interface for
// accepting or choosing the correct word by the user.

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.DefaultCharInputReader;

import java.util.HashMap;

public class WordPredicter {
    private final Dictionary dict;
    private final HashMap<String, Double> wordsFreq;
    private final StringArray suggestions;
    private String originalWord;
    private String bestString;
    private double bestValue;

    public WordPredicter(HashMap<String, Double> wordsFreq, Dictionary dict){
        this.wordsFreq = wordsFreq;
        this.dict = dict;
        suggestions = new StringArray();
    }


    public String predictWord(String word){
        originalWord = word;
        bestString = word;
        bestValue = 0;

//        word = word.toLowerCase();

//        Predicting 4 different situations. Assuming that error in spelling occurs as a result of a missclick rather than intentional action.
        if (word.length() > 1) { //word must be longer than one letter to be checked
            predict_ExtraLetter(word);
            predict_MissingLetter(word);
            predict_WrongLetter(word);
            predict_ChangedLetters(word);
        }

//        UI block. Shows wrong word, its possible correction and gets
        System.out.println("--- Wrong word: " + originalWord);
        if (suggestions.isEmpty()) {
            System.out.println("Haven't found any suggestions for " + originalWord);
            System.out.println();
            return originalWord + "(wrong)"; //if there are no suggestions just annotate the word with "(wrong)" at the end
        }
        else {
            System.out.println("Showing all suggestions to replace it:");
            for (int i = 0; i < suggestions.size(); i++)
                System.out.println((i + 1) + ": " + originalWord + " -> " + suggestions.get(i));
        }
        if (!originalWord.equals(bestString)) {
            System.out.println("\nBest match found by word frequency analysis:");
            System.out.println(originalWord + " -> " + bestString);
        }
        else
            System.out.println("\nHaven't found best match.");

        return decision(!originalWord.equals(bestString)); //if there are suggestions then let user make a decision
    }

//    User decision
    private String decision(boolean isThereABestMatch){
        System.out.println("\nChoose option:");
        if(isThereABestMatch)
            System.out.println("option 0 - Accept best match");
        System.out.println("option -1 - Do not replace it, leave: " + originalWord);
        System.out.println("options from 1 to " + suggestions.size() + " - Choose your option from the list above");
        int option = -2;

        while (option > suggestions.size() || option < -1) {
            option = ReaderWriter.readInt(option);
        }

        if (option == 0){
            return bestString;
        }
        else if (option == -1)
            return originalWord;
        else
            return suggestions.get(option - 1);

    }

//    Scoring a word, looking if it is in a wordFreq HashMap. If so, then check the scoring
//    Used to suggest best correction.
    private void scoreWordForBestMatch(String s){
        if(wordsFreq.get(s) != null) { //if word is in wordsFreq, then score it
            if (wordsFreq.get(s) > bestValue) {
                bestValue = wordsFreq.get(s);
                bestString = s;
            }
        }
    }

// Checking if suggestion in dictionary, if so then include in suggestions StringArray
    private void checkWord(String s){
            if (!dict.lookUpWord(s)) //if word not in dictionary then reject it
                return;
            if (!suggestions.contains(s)) //prevent duplicates
                suggestions.add(s);
    }

//    Checking all cases were the letter is missing
    private void predict_MissingLetter(String s){
        String newWord;
        for (int j = 0; j < 26; j++) {
            newWord = (char) (j + 97) + s;
            checkWord(newWord);
            checkWord(newWord.toLowerCase());
            scoreWordForBestMatch(newWord.toLowerCase());
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (s.length() == 1)
                    newWord = s.substring(0, i + 1) + (char)(j + 97);
                else
                    newWord = s.substring(0, i + 1) + (char)(j + 97) + s.substring(i + 1);
                checkWord(newWord);
                checkWord(newWord.toLowerCase());
                scoreWordForBestMatch(newWord.toLowerCase());
            }
        }
    }
    //    Checking all cases were the letter is wrong
    private void predict_WrongLetter(String s){
        String newWord;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                newWord = s.substring(0, i) + (char)(j + 97) + s.substring(i + 1);
                checkWord(newWord);
                checkWord(newWord.toLowerCase());
                scoreWordForBestMatch(newWord.toLowerCase());
            }
        }
    }
    //    Checking all cases were there is a missing letter
    private void predict_ExtraLetter(String s){
        String newWord;
        for (int i = 0; i < s.length(); i++) {
            newWord = s.substring(0, i) + s.substring(i + 1);
            checkWord(newWord);
            checkWord(newWord.toLowerCase());
            scoreWordForBestMatch(newWord.toLowerCase());
        }
    }

    //    Checking all cases were two letters have been swapped
    private void predict_ChangedLetters(String s){
        String newWord;
        for (int i = 0; i < s.length() - 1; i++){
            String twoLetters = s.substring(i, i + 2);
            newWord = s.substring(0, i) + twoLetters.charAt(1) + twoLetters.charAt(0) + s.substring(i + 2);
            checkWord(newWord);
            checkWord(newWord.toLowerCase());
            scoreWordForBestMatch(newWord.toLowerCase());
        }
    }

}
