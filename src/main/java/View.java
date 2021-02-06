// This class is mostly for interacting with user

public class View {
    private final Model md; //model reference in order to print show its state

    public View(Model md){
        this.md = md;
    }

//    choosing dict source
    public int initDictType(){
        System.out.println("Do you want to use built-in dictionary?");
        System.out.println("0 - yes");
        System.out.println("1 - no");
        int option = -1;

        while (option != 0 && option != 1)
            option = ReaderWriter.readInt(option);

        return option;
    }

//    choosing builtin dict
    public int initBuiltInDictType(){
        System.out.println("Select which dictionary you want to use:");
        System.out.println("0 - UNIX \"words\" english dict (NOT RECOMMENDED)");
        System.out.println("1 - bigger, 400k words english dict (https://github.com/dwyl/english-words)");
        int option = -1;

        while (option != 0 && option != 1)
            option = ReaderWriter.readInt(option);

        return option;
    }

    public void provideTextToCheck(){
        System.out.println("Please provide text to be checked:");
    }

//  Init action type
    public int initAction(){
        System.out.println("Choose action:");
        System.out.println("0 - only check text from a terminal (One line)");
        System.out.println("1 - only check text from a text file (Multiple lines)");
        System.out.println("2 - check and correct text from a terminal (One line)");
        System.out.println("3 - check and correct text from a text file (Multiple lines)");

        int option = -1;

        while (option != 0 && option != 1 && option != 2 && option != 3)
            option = ReaderWriter.readInt(option);


        return option;
    }

//    Getting file path for other functions
    public String getFilePath(){
        System.out.println("Please provide an absolute path to your file:");
        String path = null;

        while (path == null)
            path = ReaderWriter.readString(path);

        return path;
    }

//    Show wrong words after checking
    public void showWrongWords(){
        StringArray wrongWords = md.getWrongWords();

        System.out.println("---------------------------------------------------");
        if (wrongWords.size() == 0)
            System.out.println("Nothing to correct. All words have been spelt correctly.");

        else {
            System.out.println("Wrongly spelt words: ");
            for (int i = 0; i < wrongWords.size(); i++)
                System.out.println(wrongWords.get(i));

        }
        System.out.println("---------------------------------------------------");
    }

//    Show corrected text
    public void showCorrectedText() {
        StringArray lineByLine = md.getCorrectedLines();
        System.out.println("Corrected Text:");
        for (int i = 0; i < lineByLine.size(); i++)
            System.out.println(lineByLine.get(i));
        System.out.println("---------------------------------------------------");
    }

    public void correctingCompleted() {
        System.out.println("Correcting completed. Where to save the corrected text?");
    }

    public void everythingCorrect() {
        System.out.println("Everything is correct.");
    }
}
