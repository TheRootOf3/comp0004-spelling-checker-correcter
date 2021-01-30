public class Parser {
    private StringArray parsedSA;

    public Parser(){
        parsedSA = new StringArray();
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

            index++;

        }
    }

    public StringArray getParsedSA(){
        return parsedSA;
    }
}
