import java.util.HashMap;

public class WordPredicter {
    private final HashMap<String, Double> wordsFreq;
    private String bestString;
    private double bestValue;

    public WordPredicter(){
        ReaderWriter rd = new ReaderWriter();
        wordsFreq = rd.readTrigramsFreq("./src/main/resources/dict_freq.txt"); //File from SymSpell github page https://github.com/wolfgarbe/SymSpell
    }


    public String predictWord(String word){
        bestString = word + "(wrong)";
        bestValue = 0;

        word = word.toLowerCase();

        predict_ExtraLetter(word);
        predict_MissingLetter(word);
        predict_WrongLetter(word);

        return bestString;
    }

    private void scoreWord(String s){
        if(wordsFreq.get(s) == null)
            return;
        if(wordsFreq.get(s) > bestValue){
            bestValue = wordsFreq.get(s);
            bestString = s;
        }
    }

    private void predict_MissingLetter(String s){
        String newWord;
        for (int j = 0; j < 26; j++) {
            newWord = (char) (j + 97) + s;
            scoreWord(newWord);
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                newWord = s.substring(0, i + 1) + (char)(j + 97) + s.substring(i + 1);
                scoreWord(newWord);
            }
        }
    }

    private void predict_WrongLetter(String s){
        String newWord;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                newWord = s.substring(0, i) + (char)(j + 97) + s.substring(i + 1);
                scoreWord(newWord);
            }
        }
    }

    private void predict_ExtraLetter(String s){
        String newWord;
        for (int i = 0; i < s.length(); i++) {
            newWord = s.substring(0, i) + s.substring(i + 1);
            scoreWord(newWord);
        }
    }

}
