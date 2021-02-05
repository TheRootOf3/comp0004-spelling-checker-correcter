public class Reader {

    public StringArray readFile(String path){
        FileInput fin = new FileInput(path);
        Parser pa = new Parser();

        while(fin.hasNextLine())
            pa.extractWords(fin.nextLine());

        return pa.getParsedSA();
    }

    public StringArray readInput(){
        Input input = new Input();
        Parser pa = new Parser();

        pa.extractWords(input.nextLine());

        return pa.getParsedSA();
    }

    public String readFileToString(String path){
        FileInput fin = new FileInput(path);
        String stream = "";
        while (fin.hasNextLine())
            stream = stream += fin.nextLine();
        return stream;
    }

    public String readInputToString(){
        Input input = new Input();
        return input.nextLine();

    }

}
