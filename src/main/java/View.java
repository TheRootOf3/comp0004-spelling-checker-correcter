public class View {
    private Model md;
    private Input in;

    public View(Model md){
        this.md = md;
        in = new Input();
    }

    public int initDictType(){
        System.out.println("Do you want to use built-in dictionary?");
        System.out.println("0 - yes");
        System.out.println("1 - no");
        String tmpStr = "";

        while (!tmpStr.equals("0") && !tmpStr.equals("1"))
            tmpStr = in.nextLine();

        return Integer.parseInt(tmpStr);
    }

    public int initBuiltInDictType(){
        System.out.println("Select which dictionary you want to use:");
        System.out.println("0 - UNIX \"words\" english dict");
        System.out.println("1 - bigger, 400k words english dict");
        String inStr = "";

        while (!inStr.equals("0") && !inStr.equals("1"))
            inStr = in.nextLine();

        return Integer.parseInt(inStr);
    }

    public String initUserDict(){
        System.out.println("Please provide an absolute path to your dictionary:");
//        String path = in.nextLine();

        return in.nextLine();
    }

    public int initWordsType(){
        System.out.println("Select source of your text to be check:");
        System.out.println("0 - text from a terminal (One line)");
        System.out.println("1 - text from a text file (Multiple lines)");
        String inStr = "";

        while (!inStr.equals("0") && !inStr.equals("1"))
                inStr = in.nextLine();

        return Integer.parseInt(inStr);
    }


    public String getWordsFromFile(){
        System.out.println("Please provide an absolute path to your file:");
//        String path = in.nextLine();

        return in.nextLine();
    }

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

    public int chooseAction(){
        System.out.println("Choose action");
        System.out.println("0 - check another text");
        System.out.println("1 - change dictionary");
        String inStr = "";

        while (!inStr.equals("0") && !inStr.equals("1"))
            inStr = in.nextLine();

        return Integer.parseInt(inStr);
    }
}
