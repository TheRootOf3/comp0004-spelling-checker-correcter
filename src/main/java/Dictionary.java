public class Dictionary {
    private final StringArray dictArray;


    public Dictionary(String dictPath){
        Reader rd = new Reader();
        Parser pa = new Parser();
        System.out.println("-----Loading dictionary...-----");
//        pa.extractWords(rd.readFileLineByLine(dictPath));
//        dictArray = pa.getParsedSA();
        dictArray = rd.readFileWordbyWord(dictPath);
        System.out.println("-----Dictionary loaded!-----");
        System.out.println("-----Sorting dictionary...-----");
        dictArray.sort();
        System.out.println("-----Dictionary sorted!-----");
    }


    public boolean lookUpWordLinear(String word){
        return dictArray.containsMatchingCase(word);
    }

//    Makes 2 checks.
//    1) If word starts with a lower letter just checks if word is in dict
//    2) If word starts with a big letter then makes it lower and checks
    public boolean lookUpWordMatchingCase(String word){
        if (word.charAt(0) == word.toUpperCase().charAt(0))
            word = word.substring(0, 1).toLowerCase() + word.substring(1);

        return (binSearch(word, 0, dictArray.size() - 1));
    }


    private boolean binSearch(String word, int start, int end){
        if (end >= start) {
            int mid = start + (end - start) / 2;

            if (word.compareTo(dictArray.get(mid)) == 0)
                return true;
            else if (word.compareTo(dictArray.get(mid)) > 0) {
//                System.out.println(strArr.get(mid + 1) + " " + strArr.get(end) + (mid + 1) + " " + end);
                return binSearch(word, mid + 1, end);
            }
            else {
//                System.out.println(strArr.get(start) + " " + strArr.get(mid - 1) + start + " " + (mid-1));
                return binSearch(word, start, mid - 1);
            }
        }
        else
            return false;
    }

}
