public class StringArray {

    private int size;
    private String[] array;

    public StringArray() {
        array = new String[10];
    }

    public StringArray(StringArray a) {
        array = new String[a.size()];
        size = a.size;
        for (int i = 0; i < a.size(); i++)
            array[i] = a.get(i);
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public String get(int index) {
        if (index > size - 1)
            return null;
        else
            return array[index];
    }

    public void set(int index, String s) {
        if (index > size - 1)
            return;
        else
            array[index] = s;
    }

    public void add(String s) {
        if (calculateRatio() == 1)
            increaseArraySize();

        size++;
        array[size - 1] = s;
    }

    public void insert(int index, String s) {
        if (index > size - 1)
            return;

        if (calculateRatio() == 1)
            increaseArraySize();

        for (int i = size - 1; i >= index; i--)
            array[i + 1] = array[i];

        size++;
        array[index] = s;

    }

    public void remove(int index) {
        if (index > size - 1)
            return;

        for (int i = index; i < size - 1; i++)
            array[i] = array[i + 1];

        size--;
        if (calculateRatio() <= 0.3) // Decreasing percentage
            decreaseArraySize();
    }

    public boolean contains(String s) {
        s = s.toLowerCase();
        for (int i = 0; i < size; i++) {
            if (s.compareTo(array[i].toLowerCase()) == 0)
                return true;
        }
        return false;
    }

    public boolean containsMatchingCase(String s) {
        for (int i = 0; i < size; i++) {
            if (s.compareTo(array[i]) == 0)
                return true;
        }
        return false;
    }

    public int indexOf(String s) {
        s = s.toLowerCase();
        for (int i = 0; i < size; i++) {
            if (s.compareTo(array[i].toLowerCase()) == 0)
                return i;
        }
        return -1;
    }

    public int indexOfMatchingCase(String s) {
        for (int i = 0; i < size; i++) {
            if (s.compareTo(array[i]) == 0)
                return i;
        }
        return -1;
    }

    private void increaseArraySize() {
        String[] tmpArray = new String[array.length * 2];
        for (int i = 0; i < array.length; i++)
            tmpArray[i] = array[i];

        array = tmpArray;
    }

    private void decreaseArraySize() {
        String[] tmpArray = new String[array.length / 2];
        for (int i = 0; i < size; i++)
            tmpArray[i] = array[i];

        array = tmpArray;
    }

//    private double calculateRatio(){
//        return ((double) size / (double) array.length);
//    }


    //    Developmental method
    public int getRealSize() {
        return array.length;
    }

    public double calculateRatio() {
        return ((double) size / (double) array.length);
    }

    //    Sorting algo
    private void merge(int start, int middle, int end) {
        int sub1 = middle - start + 1;
        int sub2 = end - middle;

        String left[] = new String[sub1];
        String right[] = new String[sub2];

        for (int i = 0; i < sub1; i++) {
            left[i] = array[start + i];
        }
        for (int i = 0; i < sub2; i++)
            right[i] = array[middle + i + 1];

        int index1 = 0;
        int index2 = 0;
        int indexArr = start;

        while (sub1 > index1 && sub2 > index2){
            if (left[index1].compareTo(right[index2]) <= 0)
                array[indexArr++] = left[index1++];
            else
                array[indexArr++] = right[index2++];
        }

        while (index1 < sub1)
            array[indexArr++] = left[index1++];

        while (index2 < sub2)
            array[indexArr++] = right[index2++];

    }

    private void mSort(int start, int end){
        if (end > start){
            int middle = (start + end) / 2;
            mSort(start, middle);
            mSort(middle + 1, end);

            merge(start, middle, end);
        }
    }

    public void sort() {
        mSort(0, size - 1);
    }


}