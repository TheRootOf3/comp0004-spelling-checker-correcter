import org.junit.jupiter.params.ParameterizedTest;

public class SpellCorrecter {

    private StringArray lineByLineSA;
    private StringArray wordByWordSA;
    private StringArray wrongWords;
    private StringArray predictedCorrectWords;
    private Dictionary dict;


    public SpellCorrecter(Dictionary dict){
        Reader rd = new Reader();
        lineByLineSA = rd.readInputLineByLine();
        this.dict = dict;
        auxConstructor();
    }

    public SpellCorrecter(Dictionary dict, String path){
        Reader rd = new Reader();
        lineByLineSA = rd.readFileLineByLine(path);
        this.dict = dict;
        auxConstructor();
    }

    private void auxConstructor(){
        wrongWords = new StringArray();
        predictedCorrectWords = new StringArray();
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
        for (int i = 0; i < wrongWords.size(); i++)
            predictedCorrectWords.add("TEST");

        replaceWrongWords();
    }

    private void replaceWrongWords(){
        String tmpLine = null;
        for (int i = 0; i < lineByLineSA.size(); i++){
            for (int j = 0; j < predictedCorrectWords.size(); j++) {
                tmpLine = lineByLineSA.get(i).replaceAll(wrongWords.get(j), predictedCorrectWords.get(j));
                lineByLineSA.set(i, tmpLine);
            }
        }
    }

    public StringArray getCorrectedLineByLine(){
        return lineByLineSA;
    }

    public StringArray getWrongWords(){
        return wrongWords;
    }


}
