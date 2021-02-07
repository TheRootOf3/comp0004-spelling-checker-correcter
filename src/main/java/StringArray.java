// Main data structure used in the program. Implemented as in the coursework note

public class StringArray {

    private int size;
    private String[] array;

//    Must-have methods

    public StringArray() {
        size = 0;
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
        if (index > size - 1 || index < 0)
            return null;
        else
            return array[index];
    }

    public void set(int index, String s) {
        if (index <= size - 1 && index >= 0)
            array[index] = s;
    }

    public void add(String s) {
        if (calculateRatio() == 1)
            increaseArraySize();

        size++;
        array[size - 1] = s;
    }

    public void insert(int index, String s) {
        // inserts only if index valid or (StringArray empty and index 0)
        if ((index < size && index >= 0) || (size == 0 && index == 0)) {
            if (calculateRatio() == 1)
                increaseArraySize();

            if (size - index >= 0)
                System.arraycopy(array, index, array, index + 1, size - index);

            size++;
            array[index] = s;
        }

    }

    public void remove(int index) {
        if (index > size - 1 || index < 0)
            return;

        if (size - 1 - index >= 0)
            System.arraycopy(array, index + 1, array, index, size - 1 - index);

        size--;
        if (calculateRatio() <= 0.3) // Decreasing percentage
            decreaseArraySize();
    }

    public boolean contains(String s) {
        s = s.toLowerCase();
        return (indexOf(s) != -1);
    }

    public boolean containsMatchingCase(String s) {
        return (indexOfMatchingCase(s) != -1);
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

        System.arraycopy(array, 0, tmpArray, 0, array.length);

        array = tmpArray;
    }

    private void decreaseArraySize() {
        String[] tmpArray = new String[array.length / 2];
        if (size >= 0)
            System.arraycopy(array, 0, tmpArray, 0, size);


        array = tmpArray;
    }

    private double calculateRatio(){
        return ((double) size / (double) array.length);
    }


    //    Developmental method

    public int getRealSize() {
        return array.length;
    }

    //    Sorting algo & methods

    private void merge(int start, int middle, int end) {
        int sub1 = middle - start + 1;
        int sub2 = end - middle;

        String[] left = new String[sub1];
        String[] right = new String[sub2];

        if (sub1 >= 0)
            System.arraycopy(array, start, left, 0, sub1);

        if (sub2 >= 0)
            System.arraycopy(array, middle + 1, right, 0, sub2);

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