// This is a main Model class. It is used to initialize and keep dictionary and create SpellCorrecter instance.

import java.util.HashMap;

public class Model {
    private Dictionary dict;
    private SpellCorrecter sc;

    public void initializeDict(int type){
        if (type == 0)
            dict = new Dictionary("./src/main/resources/words");
        else if (type == 1)
            dict = new Dictionary("./src/main/resources/words400k");
    }

    public void initializeDict(String path){
        dict = new Dictionary(path);
    }

    //    From terminal
    public void checkWords(){
        sc = new SpellCorrecter(dict);
        sc.checkAllWords();
    }

    //    From file
    public void checkWords(String path){
        sc = new SpellCorrecter(dict, path);
        sc.checkAllWords();
    }

    //    From terminal
    public void correctWords(){
        sc = new SpellCorrecter(dict);
        sc.checkAllWords();
        sc.predictCorrectWords();
    }

    //    From file
    public void correctWords(String path){
        sc = new SpellCorrecter(dict, path);
        sc.checkAllWords();
        sc.predictCorrectWords();
    }

    public void saveToFile(String path){
        ReaderWriter.writeToFile(path, sc.getCorrectedLineByLine());
    }

    public StringArray getWrongWords(){
        return sc.getWrongWords();
    }

    public StringArray getCorrectedLines(){
        return sc.getCorrectedLineByLine();
    }

    public HashMap<String, String> getWrongToCorrectMap(){
        return sc.getWrongToCorrectMap();
    }

}
