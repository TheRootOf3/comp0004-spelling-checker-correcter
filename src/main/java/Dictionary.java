// This class is used to store a dictionary and its lookUp feature. It also provides a binary Search capability.

public class Dictionary {
    private final StringArray dictArray;


    public Dictionary(String dictPath){
        Parser pa = new Parser();
        System.out.println("-----Loading dictionary...-----");
        dictArray = ReaderWriter.readFileWordbyWord(dictPath);
        System.out.println("-----Dictionary loaded!-----");
        System.out.println("-----Sorting dictionary...-----");
        dictArray.sort();
        System.out.println("-----Dictionary sorted!-----");
    }

    public boolean lookUpWordLinear(String word){
        return dictArray.containsMatchingCase(word);
    }

//    Since every word can start with the big letter (beginning of a sentence), checks if word starts with upper.
//    If so, checks it, if not found changes it to lower case and then checks. If word starts with lower case, just checks.
    public boolean lookUpWord(String word){
        if (word.charAt(0) == word.toUpperCase().charAt(0)){
            if ((binSearch(word, 0, dictArray.size() - 1)))
                return true;
            else if (word.length() == 1) //check for words of length 1
                word = word.toLowerCase();
            else
                word = word.substring(0, 1).toLowerCase() + word.substring(1); //this throws IndexOutOfBoundsException for words of length 1
        }

        return (binSearch(word, 0, dictArray.size() - 1));
    }


    private boolean binSearch(String word, int start, int end){
        if (end >= start) {
            int mid = start + (end - start) / 2;

            if (word.compareTo(dictArray.get(mid)) == 0)
                return true;
            else if (word.compareTo(dictArray.get(mid)) > 0) {
                return binSearch(word, mid + 1, end);
            }
            else {
                return binSearch(word, start, mid - 1);
            }
        }
        else
            return false;
    }

}
