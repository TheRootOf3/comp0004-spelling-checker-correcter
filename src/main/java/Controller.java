public class Controller {

    private Model md;
    private View vw;

    public Controller(){
        md = new Model();
        vw = new View(md);
    }

    public void initDict(){
        if (vw.initDictType() == 0)
            initBuiltInDict();
        else
            initUserDict();
    }

    private void initBuiltInDict(){
        md.initializeDict(vw.initBuiltInDictType());
    }

    private void initUserDict(){
        md.initializeDict(vw.initUserDict());
    }

    public void initWords(){
        if (vw.initWordsType() == 1)
            getWordsFromFile();
        else
            getWordsFromTerminal();

        vw.showWrongWords();
    }

    private void getWordsFromFile(){
        md.checkWordsFromFile(vw.getWordsFromFile());
    }

    private void getWordsFromTerminal(){
        md.checkWordsFromTerminal();
    }

//    public void chooseAction(){
//        if (vw.chooseAction() == 1)
//            initDictType();
//    }

}
