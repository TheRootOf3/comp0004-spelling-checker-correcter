import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StringArrayTest {

    private StringArray sa;

    @BeforeEach
    void setup(){
        sa = new StringArray();
    }

    @Test
    void size0() {
        assertEquals(0, sa.size());
    }

    @Test
    void size1() {
        sa.add("abc");
        assertEquals(1, sa.size());
    }

    @Test
    void isEmpty() {
        assertTrue(sa.isEmpty());
    }

    @Test
    void add(){
        sa.add("1");
        assertEquals("1", sa.get(0));
    }

    @Test
    void addNull(){
        sa.add(null);
        assertNull(sa.get(0));
    }

    @Test
    void addInsertSetNulls(){
        for (int i = 0; i < 1000; i++)
            sa.add(null);

        for (int i = 0; i < 1000; i++)
            sa.insert(2,null);

        for (int i = 500; i < 1500; i++)
            sa.set(i,null);

        for (int i = 0; i < 2000; i++)
            assertNull(sa.get(i));
    }



    @Test
    void get() {
        String[] array = {"a", "b", "c", "d", "e"};
        for (String s : array)
            sa.add(s);

        for (int i = 0; i<array.length; i++)
            assertEquals(0, array[i].compareTo(sa.get(i)));
    }

    @Test
    void setEmpty(){
        sa.set(0, "abc");
        assertFalse(sa.contains("abc"));
    }


    @Test
    void set1(){
        sa.add("1");
        sa.set(0, "abc");

        assertEquals("abc", sa.get(0));
    }

    @Test
    void insertEmptyTest(){
        sa.insert(0, "abc");
        assertEquals("abc" ,sa.get(0));
    }


    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4})
    void insertTest(int index){
        String[] array = {"a", "b", "c", "d", "e"};
        for (String s : array)
            sa.add(s);
        sa.insert(index, "!");
        assertEquals("!", sa.get(index));
    }

    @Test
    void constr2() {
        String[] array = {"a", "b", "c", "d", "e"};
        for (String s : array)
            sa.add(s);

        StringArray newSa = new StringArray(sa);
        for (int i = 0; i<array.length; i++)
            assertEquals(0, array[i].compareTo(newSa.get(i)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "ABC", "Abc"})
    void containsTest(String s){
        sa.add("abc");
        assertTrue(sa.contains(s));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "cba", "whatever", "1", "123", "."})
    void notContainsTest(String s){
        sa.add("abc");
        assertFalse(sa.contains(s));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc"})
    void containsMatchingCaseTest(String s){
        sa.add("abc");
        assertTrue(sa.containsMatchingCase(s));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABC", "Abc", "", " ", "cba", "whatever", "1", "123", "."})
    void notContainsMatchingCaseTest(String s){
        sa.add("abc");
        assertFalse(sa.containsMatchingCase(s));
    }

    @Test
    void remove() {
        String[] array = {"a", "b", "c", "d", "e"};
        for (String s : array)
            sa.add(s);

        for (int i = 0; i < array.length; i++)
            sa.remove(0);

        assertEquals(0, sa.size());
    }


    @Test
    void indexOf1() {
        String[] array = {"a", "b", "c"};
        for (String s : array)
            sa.add(s);
        assertEquals(0, sa.indexOf("a"));
        assertEquals(0, sa.indexOf("A"));
    }
    @Test
    void indexOf2() {
        String[] array = {"a", "b", "c"};
        for (String s : array)
            sa.add(s);
        assertEquals(1, sa.indexOf("b"));
        assertEquals(1, sa.indexOf("B"));

    }
    @Test
    void indexOf3() {
        String[] array = {"a", "b", "c"};
        for (String s : array)
            sa.add(s);
        assertEquals(2, sa.indexOf("c"));
        assertEquals(2, sa.indexOf("C"));

    }

    @Test
    void indexOfFalse() {
        String[] array = {"a", "b", "c"};
        for (String s : array)
            sa.add(s);
        assertEquals(-1, sa.indexOf("d"));
        assertEquals(-1, sa.indexOf("D"));

    }

    @Test
    void indexOfMC1() {
        String[] array = {"a", "b", "c"};
        for (String s : array)
            sa.add(s);
        assertEquals(0, sa.indexOfMatchingCase("a"));
        assertEquals(-1, sa.indexOfMatchingCase("A"));

    }
    @Test
    void indexOfMC2() {
        String[] array = {"a", "b", "c"};
        for (String s : array)
            sa.add(s);
        assertEquals(1, sa.indexOfMatchingCase("b"));
        assertEquals(-1, sa.indexOfMatchingCase("B"));

    }
    @Test
    void indexOfMC3() {
        String[] array = {"a", "b", "c"};
        for (String s : array)
            sa.add(s);
        assertEquals(2, sa.indexOfMatchingCase("c"));
        assertEquals(-1, sa.indexOfMatchingCase("C"));

    }
    @Test
    void indexOfMCFalse() {
        String[] array = {"a", "b", "c"};
        for (String s : array)
            sa.add(s);
        assertEquals(-1, sa.indexOfMatchingCase("d"));
        assertEquals(-1, sa.indexOfMatchingCase("D"));
    }

    @Test
    void increaseSizeBorder(){
        String[] array = {"a", "b", "c", "a", "b", "c", "a", "b", "c", "a"};
        for (String s : array)
            sa.add(s);

        assertEquals(10, sa.getRealSize());
    }

    @Test
    void increaseSize(){
        String[] array = {"a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b"};
        for (String s : array)
            sa.add(s);

        assertEquals(20, sa.getRealSize());
    }

    @Test
    void decreaseSizeBorder(){
        String[] array = {"a", "b", "c", "a", "b", "c", "a", "b", "c", "a"};
        for (String s : array)
            sa.add(s);
        for (int i = 0; i < 6; i++)
            sa.remove(0);

        assertEquals(10, sa.getRealSize());
    }

    @Test
    void decreaseSize(){
        String[] array = {"a", "b", "c", "a", "b", "c", "a", "b", "c", "a"};
        for (String s : array)
            sa.add(s);
        for (int i = 0; i < 7; i++)
            sa.remove(0);

        assertEquals(5, sa.getRealSize());
    }


//    @Test
//    @DisplayName("Dictionary presence test")
//    void dictLookUp() {
//        Dictionary dict = new Dictionary("./src/main/resources/words");
//        Reader rd = new Reader();
//        StringArray sa = rd.readFile("./src/main/resources/words");
//        for (int i = 0; i < sa.size(); i++) {
//            System.out.println(i + "/" + sa.size());
//            assertTrue(dict.lookUpWord(sa.get(i)));
//        }
//    }
    @Test
    void testBadIndexGet1(){
        sa.add("abc");
        assertNull(sa.get(-3));
    }

    @Test
    void testBadIndexGet2(){
        sa.add("abc");
        assertNull(sa.get(5));
    }

    @Test
    void testBadIndexGet3(){
//        sa is empty
        assertNull(sa.get(0));
    }

}