public class SpellChecker {
    private StringArray dictArray;
    private StringArray dictArrayl;
    private StringArray dictArrays;

    public SpellChecker(String dictPath){
        Reader rd = new Reader();
        dictArray = rd.readUserFile(dictPath).getParsedSA();
        Parser ps = rd.readDict(dictPath);

        dictArrayl = ps.getParsedSAl();
        dictArrays = ps.getParsedSAs();
    }

    public boolean lookUpWordLinear(String word){
        return dictArray.containsMatchingCase(word);
    }

    public boolean lookUpWord(String word){
//        return binSearch(dictArray, word, 0, dictArray.size() - 1);
        if (word.charAt(0) == word.toUpperCase().charAt(0))
            return binSearch(dictArrayl, word, 0, dictArrayl.size() - 1);
        else
            return binSearch(dictArrays, word, 0, dictArrays.size() - 1);
    }

    public boolean binSearch(StringArray strArr, String word, int start, int end){
        if (end >= start) {
            int mid = start + (end - start) / 2;

            if (word.compareTo(strArr.get(mid)) == 0)
                return true;
            else if (word.compareTo(strArr.get(mid)) > 0) {
//                System.out.println(strArr.get(mid + 1) + " " + strArr.get(end) + (mid + 1) + " " + end);
                return binSearch(strArr, word, mid + 1, end);
            }
            else {
//                System.out.println(strArr.get(start) + " " + strArr.get(mid - 1) + start + " " + (mid-1));
                return binSearch(strArr, word, start, mid - 1);
            }

        }
        else
            return false;
    }

}
