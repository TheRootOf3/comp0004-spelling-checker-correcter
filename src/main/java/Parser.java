public class Parser {
    private StringArray parsedSA;
    private StringArray parsedSAl;
    private StringArray parsedSAs;

    public Parser(){
        parsedSA = new StringArray();
        parsedSAl = new StringArray();
        parsedSAs = new StringArray();

    }

    private String removeDelims(String streamLine){
        return streamLine.replaceAll("[,.]", "");
    }

    public void extractWords(String streamLine){
        streamLine = removeDelims(streamLine);
        String word;
        int index = 0;
        while (index < streamLine.length()){

            word = "";

            while (index < streamLine.length() && streamLine.charAt(index) != ' '){

                word = word + streamLine.charAt(index);
                index++;
            }

            if (word != "")
                parsedSA.add(word);

            if (word != ""){
                if (word.charAt(0) == word.toUpperCase().charAt(0))
                    parsedSAl.add(word);
                else
                    parsedSAs.add(word);
            }
            index++;

        }
    }

    public StringArray getParsedSA(){
        return parsedSA;
    }
    public StringArray getParsedSAl(){
        return parsedSAl;
    }
    public StringArray getParsedSAs(){
        return parsedSAs;
    }
}
