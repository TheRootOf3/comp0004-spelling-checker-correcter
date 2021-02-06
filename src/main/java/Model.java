public class Model {
    private Dictionary dict;
    private final ReaderWriter rd;
    private SpellCorrecter sc;

    public Model(){
        rd = new ReaderWriter();
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
        sc = new SpellCorrecter(dict);
        sc.checkAllWords();
    }

    public void checkWords(String path){
        sc = new SpellCorrecter(dict, path);
        sc.checkAllWords();
    }

    public void correctWords(){
        sc = new SpellCorrecter(dict);
        sc.checkAllWords();
        sc.predictCorrectWords();
    }

    public void correctWords(String path){
        sc = new SpellCorrecter(dict, path);
        sc.checkAllWords();
        sc.predictCorrectWords();
    }

    public int saveToFile(String path){
        return rd.writeToFile(path, sc.getCorrectedLineByLine());
    }



    public StringArray getWrongWords(){
        return sc.getWrongWords();
    }

    public StringArray getCorrectedLines(){
        return sc.getCorrectedLineByLine();
    }


}
