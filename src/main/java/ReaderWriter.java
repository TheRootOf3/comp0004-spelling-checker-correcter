import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
public class ReaderWriter {

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

    public StringArray readFileLineByLine(String path) {
        StringArray lineByLineSA = new StringArray();

        try{
            File fin = new File(path);
            Scanner in = new Scanner(fin);

            while (in.hasNextLine())
                lineByLineSA.add(in.nextLine());
        }catch (FileNotFoundException e){
            System.out.println("Error when reading a file: " + e.getMessage());
            System.exit(0);
        }

        return lineByLineSA;
    }

    public StringArray readInputLineByLine(){
        StringArray lineByLineSA = new StringArray();
        Scanner in = new Scanner(System.in);
        try {
            lineByLineSA.add(in.nextLine());
        } catch (InputMismatchException e)
        {
            System.out.println("Error when reading text :"+ e.getMessage());
            System.exit(0);
        }
        return lineByLineSA;

    }

    public HashMap<String, Double> readTrigramsFreq(String path){
        HashMap<String, Double> hm = new HashMap<>();
        String[] tmpPair;

        try{
            File fin = new File(path);
            Scanner in = new Scanner(fin);

            while (in.hasNextLine()) {
                tmpPair = in.nextLine().split(" ");
                hm.put(tmpPair[0].toLowerCase(), Double.parseDouble(tmpPair[1]));
            }
        }catch (FileNotFoundException e){
            System.out.println("Error when reading a file: " + e.getMessage());
            System.exit(0);
        }

        return hm;
    }

    public void writeToFile(String path, StringArray dataToWrite){
        try {
            FileWriter fw = new FileWriter(path);
            for (int i = 0; i < dataToWrite.size(); i++)
                fw.write(dataToWrite.get(i) + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error when writing to a file: " + e.getMessage());
            System.exit(0);
        }
    }
}
