public class Reader {

    public StringArray readFileWordbyWord(String path){
        Parser pa = new Parser();
        pa.extractWords(readFileLineByLine(path));

        return pa.getParsedSA();
    }

    public StringArray readInputWordByWord(){
        Parser pa = new Parser();
        pa.extractWords(readInputLineByLine());

        return pa.getParsedSA();
    }

    public StringArray readFileLineByLine(String path){
        FileInput fin = new FileInput(path);
        StringArray lineByLineSA = new StringArray();

        while (fin.hasNextLine())
            lineByLineSA.add(fin.nextLine());

        return lineByLineSA;
    }

    public StringArray readInputLineByLine(){
        Input input = new Input();
        StringArray lineByLineSA = new StringArray();
        lineByLineSA.add(input.nextLine());
        return lineByLineSA;

    }

}
