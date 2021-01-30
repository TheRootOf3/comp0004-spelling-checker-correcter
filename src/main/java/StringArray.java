public class StringArray {

    private int size;
    private String[] array;

    public StringArray (){
        array = new String[10];
    }

    public StringArray (StringArray a){
        array = new String[a.size()];
        size = a.size;
        for (int i = 0; i < a.size(); i++)
            array[i] = a.get(i);
    }


    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public String get(int index){
        if (index > size - 1)
            return null;
        else
            return array[index];
    }

    public void set(int index, String s){
        if (index > size - 1)
            return;
        else
            array[index] = s;
    }

    public void add(String s){
        if (calculateRatio() == 1)
            increaseArraySize();

        size++;
        array[size - 1] = s;
    }

    public void insert(int index, String s){
        if (index > size - 1)
            return;

        if (calculateRatio() == 1)
            increaseArraySize();

        for (int i = size - 1; i >= index; i--)
            array[i+1] = array[i];

        size++;
        array[index] = s;

    }

    public void remove(int index){
        if (index > size - 1)
            return;

        for (int i = index; i < size - 1; i++)
            array[i] = array[i+1];

        size--;
        if (calculateRatio() <= 0.3) // Decreasing percentage
            decreaseArraySize();
    }

    public boolean contains(String s){
        s = s.toLowerCase();
        for (int i = 0; i < size; i++){
            if (s.compareTo(array[i].toLowerCase()) == 0)
                return true;
        }
        return false;
    }

    public boolean containsMatchingCase(String s){
        for (int i = 0; i < size; i++){
            if (s.compareTo(array[i]) == 0)
                return true;
        }
        return false;
    }

    public int indexOf(String s){
        s = s.toLowerCase();
        for (int i = 0; i < size; i++){
            if (s.compareTo(array[i].toLowerCase()) == 0)
                return i;
        }
        return -1;
    }

    public int indexOfMatchingCase(String s){
        for (int i = 0; i < size; i++){
            if (s.compareTo(array[i]) == 0)
                return i;
        }
        return -1;
    }

    private void increaseArraySize(){
        String[] tmpArray = new String[array.length*2];
        for (int i = 0; i < array.length; i++)
            tmpArray[i] = array[i];

        array = tmpArray;
    }

    private void decreaseArraySize(){
        String[] tmpArray = new String[array.length/2];
        for (int i = 0; i < size; i++)
            tmpArray[i] = array[i];

        array = tmpArray;
    }

//    private double calculateRatio(){
//        return ((double) size / (double) array.length);
//    }


//    Developmental method
    public int getRealSize(){
        return array.length;
    }

    public double calculateRatio(){
        return ((double) size / (double) array.length);
    }

////    Sorting algos
//    public void mergeSort(){
//
//    }

}
