import java.util.HashMap;
import java.util.Map;

public class SpellCorrecter {

    private StringArray lineByLineSA;
    private StringArray wordByWordSA;
    private HashMap<String, String> words;
    private StringArray wrongWords;
    private Dictionary dict;
    private Reader rd;
    private HashMap<String, Double> wordsFreq;
    private String bestMatchS;
    private double bestMatchI;

    public SpellCorrecter(Dictionary dict){
        rd = new Reader();
        lineByLineSA = rd.readInputLineByLine();
        this.dict = dict;
        auxConstructor();
    }

    public SpellCorrecter(Dictionary dict, String path){
        rd = new Reader();
        lineByLineSA = rd.readFileLineByLine(path);
        this.dict = dict;
        auxConstructor();
    }

    private void auxConstructor(){
        words = new HashMap<>();
        wrongWords = new StringArray();
        Parser pa = new Parser();
        pa.extractWords(lineByLineSA);
        wordByWordSA = pa.getParsedSA();
        wordsFreq = rd.readTrigramsFreq("./src/main/resources/dict_freq.txt");

    }

    public void checkAllWords(){
        for (int i = 0; i < wordByWordSA.size(); i++){
            if (!dict.lookUpWordMatchingCase(wordByWordSA.get(i)))
                wrongWords.add(wordByWordSA.get(i));
        }
    }

    private void createHashMap (){

        for (int i = 0; i < wrongWords.size(); i++) {
            bestMatchS = wrongWords.get(i);
            bestMatchI = 0;
            predict_ExtraLetter(wrongWords.get(i));
            predict_MissingLetter(wrongWords.get(i));
            predict_WrongLetter(wrongWords.get(i));

            words.put(wrongWords.get(i), bestMatchS);
            System.out.println(bestMatchS + " " + (double)bestMatchI);
        }
    }

    public void predictCorrectWords(){
        createHashMap();
        replaceWrongWords();
    }

    private void replaceWrongWords(){
        String tmpLine = null;
        for (int i = 0; i < lineByLineSA.size(); i++){
            for (HashMap.Entry<String, String> entry : words.entrySet()){
                tmpLine = lineByLineSA.get(i).replaceAll(entry.getKey(), entry.getValue());
                lineByLineSA.set(i, tmpLine);
            }
        }
    }

    private void scoreWord(String s){
        s = s.toLowerCase();
        if(wordsFreq.get(s) == null)
            return;
        if(wordsFreq.get(s) > bestMatchI){
            bestMatchI = wordsFreq.get(s);
            bestMatchS = s;
        }
    }


//    public void testP(){
//        for (int i = 0; i < wrongWords.size(); i++)
//            predict_MissingLetter(wrongWords.get(i));
//    }


    private void predict_MissingLetter(String s){
        String newWord = null;

        for (int j = 0; j < 26; j++) {
            newWord = (char) (j + 97) + s;
            scoreWord(newWord);
        }


        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                newWord = s.substring(0, i + 1) + (char)(j + 97) + s.substring(i + 1);
                scoreWord(newWord);
//                System.out.println(newWord);
            }
        }
    }

    private void predict_WrongLetter(String s){
        String newWord = null;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                newWord = s.substring(0, i) + (char)(j + 97) + s.substring(i + 1);
                scoreWord(newWord);
//                System.out.println(newWord);
            }
        }
    }

    private void predict_ExtraLetter(String s){
        String newWord = null;
        for (int i = 0; i < s.length(); i++) {
            newWord = s.substring(0, i) + s.substring(i + 1);
            scoreWord(newWord);
//            System.out.println(newWord);
        }



    }





    public StringArray getCorrectedLineByLine(){
        return lineByLineSA;
    }

    public StringArray getWrongWords(){
        return wrongWords;
    }


}
