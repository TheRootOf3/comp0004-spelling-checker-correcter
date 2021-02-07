// Instance of this class is used to remove unnecessary characters from an input such as files or system.in stream
// and transform it into a StringArray data structure.

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
//        Checking the whole line
        while (index < streamLine.length()){
            word = "";
//            Adding char to a "word" string if its not a space or end of line. Forming a word.
            while (index < streamLine.length() && streamLine.charAt(index) != ' '){

                word = word + streamLine.charAt(index);
                index++;
            }

//              If not an empty word then add it to a parsedSA.
            if (!word.equals(""))
                words.add(word);
            index++;
        }
        return words;
    }

//  Extract words from the parameter line by line.
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
