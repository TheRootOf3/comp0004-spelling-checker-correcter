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
        md.initializeDict(vw.getFilePath());
    }

    public void actionSelection() {
        int option = vw.initWordsType();
        while (option > 3 || option < 0) {
            option = vw.initWordsType();
        }

        switch (option) {
            case 0:
                checkTerminal();
                break;

            case 1:
                checkFile();
                break;

            case 2:
                correctTerminal();
                break;

            case 3:
                correctFile();
                break;

            default:
                break;
        }
    }

    private void checkTerminal(){
        vw.provideTextToCheck();
        md.checkWords();
        vw.showWrongWords();
    }

    private void checkFile(){
        md.checkWords(vw.getFilePath());
        vw.showWrongWords();
    }

    private void correctTerminal(){
        vw.provideTextToCheck();
        md.correctWords();
        vw.showWrongWords();
        vw.showCorrectedText();
    }

    private void correctFile(){
        md.correctWords(vw.getFilePath());
        vw.showWrongWords();
        vw.showCorrectedText();
        vw.correctingCompleted();
        int savingResult = md.saveToFile(vw.getFilePath());
        if (savingResult == -1)
            vw.writingToFileError();
    }

}
