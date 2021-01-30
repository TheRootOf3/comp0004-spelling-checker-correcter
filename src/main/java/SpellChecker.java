public class SpellChecker {
    private StringArray dictArray;

    public SpellChecker(String dictPath){
        Reader rd = new Reader();
        dictArray = rd.readUserFile(dictPath);
    }

    public boolean lookUpWordLinear(String word){
        return dictArray.contains(word);
    }

    public boolean lookUpWord(String word){
        return binSearch(word, 0, dictArray.size() - 1);
    }

    public boolean binSearch(String word, int start, int end){
        if (start <= end) {
            int mid = start + (end - start) / 2;

            if (word.compareTo(dictArray.get(mid)) == 0)
                return true;
            else if (word.compareTo(dictArray.get(mid)) > 0) {
                System.out.println(dictArray.get(mid + 1) + " " + dictArray.get(end));
                return binSearch(word, mid + 1, end);
            }
            else {
                System.out.println(dictArray.get(start) + " " + dictArray.get(mid - 1));
                return binSearch(word, start, mid - 1);
            }
        }
        else
            return false;
    }

}
