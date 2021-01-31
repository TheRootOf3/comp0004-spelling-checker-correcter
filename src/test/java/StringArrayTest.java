import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringArrayTest {

    private StringArray sa;

    @BeforeEach
    void setup(){
        sa = new StringArray();
    }

    @Test
    @DisplayName("Size of 0")
    void size0() {

        assertEquals(0, sa.size());

    }

    @Test
    @DisplayName("Size of 1")
    void size1() {
        sa.add("abc");
        assertEquals(1, sa.size());

    }

    @Test
    @DisplayName("Get index")
    void get() {
        String[] array = {"a", "b", "c", "d", "e"};
        for (int i = 0; i < array.length; i++)
            sa.add(array[i]);

        for (int i = 0; i<array.length; i++)
            assertEquals(0, array[i].compareTo(sa.get(i)));
    }

    @Test
    @DisplayName("Constructor2")
    void constr2() {
        String[] array = {"a", "b", "c", "d", "e"};
        for (int i = 0; i < array.length; i++)
            sa.add(array[i]);

        StringArray newSa = new StringArray(sa);
        for (int i = 0; i<array.length; i++)
            assertEquals(0, array[i].compareTo(newSa.get(i)));
    }

    @Test
    @DisplayName("Dictionary presence test")
    void dictLookUp() {
        Dictionary dict = new Dictionary("./src/main/resources/words");
        Reader rd = new Reader();
        StringArray sa = rd.readFile("./src/main/resources/words");
        for (int i = 0; i < sa.size(); i++) {
            System.out.println(i + "/" + sa.size());
            assertTrue(dict.lookUpWord(sa.get(i)));
        }
    }

//    @Test
//    void isEmpty() {
//    }
//
//    @Test
//    void get() {
//    }
//
//    @Test
//    void set() {
//    }
//
//    @Test
//    void add() {
//    }
//
//    @Test
//    void insert() {
//    }
//
//    @Test
//    void remove() {
//    }
//
//    @Test
//    void contains() {
//    }
//
//    @Test
//    void containsMatchingCase() {
//    }
//
//    @Test
//    void indexOf() {
//    }
//
//    @Test
//    void indexOfMatchingCase() {
//    }
//
//    @Test
//    void getRealSize() {
//    }
}