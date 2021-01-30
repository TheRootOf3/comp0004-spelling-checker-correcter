public class Reader {

    public Parser readUserFile(String path){
        FileInput fin = new FileInput(path);
        Parser pa = new Parser();
        while(fin.hasNextLine())
            pa.extractWords(fin.nextLine());
        return pa;
    }

    public Parser readDict(String path){
        FileInput fin = new FileInput(path);
        Parser pa = new Parser();
        while(fin.hasNextLine())
            pa.extractWords(fin.nextLine());
        return pa;
    }

    public Parser readInput(){
        Input input = new Input();
        Parser pa = new Parser();
        pa.extractWords(input.nextLine());
        return pa;
    }

}
