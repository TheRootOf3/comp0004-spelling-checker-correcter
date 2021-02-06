public class Parser {
    private final StringArray parsedSA;

    public Parser(){
        parsedSA = new StringArray();
    }

    private String removeDelims(String streamLine){
//        return streamLine.replaceAll("[,.!?\"'@#$%^&/*():;\\[\\]=+_{}]", "");
        return streamLine.replaceAll("[\\p{Punct}]", ""); //better coverage of non-literals
    }


    public StringArray extractWordsFromLine(String streamLine){
        StringArray words = new StringArray();
        streamLine = removeDelims(streamLine);
        String word;
        int index = 0;
        while (index < streamLine.length()){

            word = "";

            while (index < streamLine.length() && streamLine.charAt(index) != ' '){

                word = word + streamLine.charAt(index);
                index++;
            }

            if (!word.equals(""))
                words.add(word);

            index++;
        }
        return words;
    }

    public void extractWords(StringArray lineByLineSA){
        for (int i = 0; i < lineByLineSA.size(); i++) {
            StringArray tmpWords = extractWordsFromLine(lineByLineSA.get(i));
            for (int j = 0; j < tmpWords.size(); j++)
                parsedSA.add(tmpWords.get(j));
        }
    }

    public StringArray getParsedSA(){
        return parsedSA;
    }
}
