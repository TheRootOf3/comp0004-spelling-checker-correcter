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

}
