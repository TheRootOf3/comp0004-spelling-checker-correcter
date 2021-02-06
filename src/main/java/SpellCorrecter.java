import java.util.HashMap;

public class SpellCorrecter {

    private final StringArray lineByLineSA;
    private StringArray wordByWordSA;
    private StringArray wrongWords;
    private HashMap<String, String> wrongToCorrectMap;

    private final Dictionary dict;
    private final ReaderWriter rd;

    public SpellCorrecter(Dictionary dict){
        rd = new ReaderWriter();
        lineByLineSA = rd.readInputLineByLine();
        this.dict = dict;
        auxConstructor();
    }

    public SpellCorrecter(Dictionary dict, String path){
        rd = new ReaderWriter();
        lineByLineSA = rd.readFileLineByLine(path);
        this.dict = dict;
        auxConstructor();
    }

    private void auxConstructor(){
        wrongToCorrectMap = new HashMap<>();
        wrongWords = new StringArray();
        Parser pa = new Parser();
        pa.extractWords(lineByLineSA);
        wordByWordSA = pa.getParsedSA();

    }

    public void checkAllWords(){
        for (int i = 0; i < wordByWordSA.size(); i++){
            if (!dict.lookUpWordMatchingCase(wordByWordSA.get(i)))
                wrongWords.add(wordByWordSA.get(i));
        }
    }

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

    private void makePredictions (){
        WordPredicter wp = new WordPredicter();
        for (int i = 0; i < wrongWords.size(); i++) {
            wrongToCorrectMap.put(wrongWords.get(i), wp.predictWord(wrongWords.get(i)));
//            System.out.println(wrongWords.get(i) + " " + wrongToCorrectMap.get(wrongWords.get(i)));
        }
    }

    private void replaceWrongWords(){
        String tmpLine = null;
        for (int i = 0; i < lineByLineSA.size(); i++){
            for (HashMap.Entry<String, String> entry : wrongToCorrectMap.entrySet()){
                tmpLine = lineByLineSA.get(i).replaceAll(entry.getKey(), entry.getValue());
                lineByLineSA.set(i, tmpLine);
            }
        }
    }
}
