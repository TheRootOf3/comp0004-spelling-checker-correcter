// Instance of this class is responsible for checking or/and correcting text.

import java.util.HashMap;

public class SpellCorrecter {

    private final StringArray lineByLineSA;
    private StringArray wordByWordSA;
    private StringArray wrongWords;
    private HashMap<String, String> wrongToCorrectMap;
    private HashMap<String, Double> wordsFreq;

    private final Dictionary dict;

    public SpellCorrecter(Dictionary dict){
        lineByLineSA = ReaderWriter.readInputLineByLine();
        this.dict = dict;
        auxConstructor();
    }

    public SpellCorrecter(Dictionary dict, String path){
        lineByLineSA = ReaderWriter.readFileLineByLine(path);
        this.dict = dict;
        auxConstructor();
    }

    private void auxConstructor(){
        wrongToCorrectMap = new HashMap<>();
        wrongWords = new StringArray();
        Parser pa = new Parser();
        pa.extractWords(lineByLineSA);
        wordByWordSA = pa.getParsedSA();
        wordsFreq = ReaderWriter.readWordsFreq("./src/main/resources/dict_freq"); //File from SymSpell github page https://github.com/wolfgarbe/SymSpell
    }

    //  Method for checking all words
    public void checkAllWords(){
        for (int i = 0; i < wordByWordSA.size(); i++){
            if (!dict.lookUpWord(wordByWordSA.get(i)))
                wrongWords.add(wordByWordSA.get(i));
        }
    }

    //  Method for correcting all words
    public void predictCorrectWords(){
        makePredictions();
        replaceWrongWords();
    }

    public StringArray getWrongWords(){
        return wrongWords;
    }

    public StringArray getCorrectedLineByLine(){
        return lineByLineSA;
    }

    public HashMap<String, String> getWrongToCorrectMap() {
        return wrongToCorrectMap;
    }

//    Creating instances of WordPredicter class and trying to find suggestions and best match for each word
    private void makePredictions (){
        for (int i = 0; i < wrongWords.size(); i++) {
            WordPredicter wp = new WordPredicter(wordsFreq, dict);
            wrongToCorrectMap.put(wrongWords.get(i), wp.predictWord(wrongWords.get(i)));
        }
    }

//  Replacing wrong words with their corrections
    private void replaceWrongWords(){
        String tmpLine = null;
        for (int i = 0; i < lineByLineSA.size(); i++){
            for (HashMap.Entry<String, String> entry : wrongToCorrectMap.entrySet()){
//                Replace only word within boundaries. Do not want to "correct" other words containing the same phrase!
                tmpLine = lineByLineSA.get(i).replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue());
                lineByLineSA.set(i, tmpLine);
            }
        }
    }
}
