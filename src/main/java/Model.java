public class Model {
    private Dictionary dict;
    private final Reader rd;
    private final StringArray wrongWords;

    public Model(){
        rd = new Reader();
        wrongWords = new StringArray();
    }

    public void initializeDict(int type){
        if (type == 0)
            dict = new Dictionary("./src/main/resources/words");
        else if (type == 1)
            dict = new Dictionary("./src/main/resources/words400k");
    }

    public void initializeDict(String path){
        dict = new Dictionary(path);
    }

    public void checkWords(){
        StringArray wordsToCheck = rd.readInput();
        checkAllWords(wordsToCheck);
    }

    public void checkWords(String path){
        StringArray wordsToCheck = rd.readFile(path);
        checkAllWords(wordsToCheck);
    }

    private void checkAllWords(StringArray wordsToCheck){
//        newWrongWords();

        for (int i = 0; i < wordsToCheck.size(); i++){
            if (!dict.lookUpWordMatchingCase(wordsToCheck.get(i)))
                wrongWords.add(wordsToCheck.get(i));
        }
    }

    public StringArray getWrongWords(){
        return wrongWords;
    }

//    private void newWrongWords(){
//        wrongWords = new StringArray();
//    }

}
