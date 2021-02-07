// Controller class is used to communicate between model and view.

public class Controller {

    private final Model md;
    private final View vw;

//    Create new Model and View
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
        int option = vw.initAction();
        while (option > 3 || option < 0) {
            option = vw.initAction();
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

        checkingResult();
    }

    private void checkFile(){
        md.checkWords(vw.getFilePath());

        checkingResult();
    }

    private void correctTerminal(){
        vw.provideTextToCheck();
        md.correctWords();

//        Check if there is anything to correct. If not then show it
        if (md.getWrongWords().size() != 0)
            vw.showCorrectedText();
        else
            vw.everythingCorrect();
    }

    private void correctFile() {
        md.correctWords(vw.getFilePath());

//        Check if there is anything to correct. If not then show it
        if (md.getWrongWords().size() != 0) {
            vw.showCorrectedText();
            vw.correctingCompleted();
            md.saveToFile(vw.getFilePath());
        }
        else
            vw.everythingCorrect();

    }

    private void checkingResult(){
//        Check if there is anything to correct. If not then show it
        if (md.getWrongWords().size() != 0)
            vw.showWrongWords();
        else
            vw.everythingCorrect();
    }

}
