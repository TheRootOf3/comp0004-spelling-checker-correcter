import java.util.HashMap;

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
        fin.close();
        return lineByLineSA;
    }

    public StringArray readInputLineByLine(){
        Input input = new Input();
        StringArray lineByLineSA = new StringArray();
        lineByLineSA.add(input.nextLine());
        input.close();
        return lineByLineSA;

    }

    public HashMap<String, Double> readTrigramsFreq(String path){
        HashMap<String, Double> hm= new HashMap<>();
        FileInput fin = new FileInput(path);
        String[] tmpPair;

        while (fin.hasNextLine()) {
            tmpPair = fin.nextLine().split(" ");
            hm.put(tmpPair[0].toLowerCase(), Double.parseDouble(tmpPair[1]));
        }

        fin.close();
        return hm;
    }

}
