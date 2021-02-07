// This is a final class serving several static methods for interacting with user and dealing with files.


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class ReaderWriter {

//    Reading single integer from the terminal input. If failed to read, then return a default value.
    public static int readInt(int defaultIfFailed){
        int inputInt = defaultIfFailed; // means incorrect input
        try {
            Scanner in = new Scanner(System.in);
            inputInt = in.nextInt();
        } catch (InputMismatchException e)
        {
            System.out.println("Provide correct option");
        }
        return inputInt;
    }
    //    Reading single line from the terminal input. If failed to read, then return a default value.
    public static String readString(String defaultIfFailed){
        String inputString = defaultIfFailed; // means incorrect input
        try {
            Scanner in = new Scanner(System.in);
            inputString = in.nextLine();
        } catch (InputMismatchException e)
        {
            System.out.println("Provide correct option");
        }
        return inputString;
    }

    //    Returning an already parsed SA from a file
    public static StringArray readFileWordbyWord(String path){
        Parser pa = new Parser();
        pa.extractWords(readFileLineByLine(path));

        return pa.getParsedSA();
    }
    //    Returning an already parsed SA from a terminal input
    public static StringArray readInputWordByWord(){
        Parser pa = new Parser();
        pa.extractWords(readInputLineByLine());

        return pa.getParsedSA();
    }

    //    Returning SA of lines from a file
    public static StringArray readFileLineByLine(String path) {
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

    //    Returning SA of lines from a terminal input
    public static StringArray readInputLineByLine(){
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
    //    Returning HashMap of words and associated frequencies.
    public static HashMap<String, Double> readWordsFreq(String path){
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
    // Saving corrected text to file
    public static void writeToFile(String path, StringArray dataToWrite){
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
