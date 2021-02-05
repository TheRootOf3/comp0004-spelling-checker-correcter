public class Controller {

    private final Model md;
    private final View vw;

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

    public void initWords() {
        int option = vw.initWordsType();
        switch (option) {
            case 0:
                md.checkWords();
                vw.showWrongWords();
                break;

            case 1:
                md.checkWords(vw.getWordsFromFile());
                vw.showWrongWords();
                break;

            case 2:
                md.correctWords();
                vw.showWrongWords();
                vw.showCorrectedText();

                break;

            default:
                break;
        }
    }


//    public void chooseAction(){
//        if (vw.chooseAction() == 1)
//            initDictType();
//    }

}
