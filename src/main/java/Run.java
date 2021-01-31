
public class Run {
    public static void main(String[] args) {
        Run run = new Run();
        StringArray sa = new StringArray();

//        Reader rd = new Reader("C:\\Users\\aszab\\OneDrive\\STUDIA\\2020_2021_Modules\\COMP0004\\Coursework1\\src\\main\\resources\\words");
        Reader rd = new Reader();

        Dictionary dict = new Dictionary("./src/main/resources/words");
//        SpellChecker sc = new SpellChecker("C:\\Users\\aszab\\OneDrive\\STUDIA\\2020_2021_Modules\\COMP0004\\Coursework1\\src\\main\\resources\\words");

//        long startTime;
//        startTime = System.nanoTime();
//        System.out.println(sc.lookUpWord("plyers"));
//        System.out.println(System.nanoTime() - startTime);
//
//        sa = rd.readUserFile("C:\\Users\\aszab\\OneDrive\\STUDIA\\2020_2021_Modules\\COMP0004\\Coursework1\\src\\main\\resources\\words").getParsedSA();
//        run.showStringArray(sa);
//
//        startTime = System.nanoTime();
//        sa.sort();
//        System.out.println(System.nanoTime() - startTime);
        System.out.println(dict.lookUpWord("dogs"));


//        startTime = System.nanoTime();
//        System.out.println(sc.lookUpWordLinear("Flour"));
//        System.out.println(System.nanoTime() - startTime);

//        System.out.println(sc.lookUpWord("really"));


        sa = rd.readInput();
        for (int i = 0; i < sa.size(); i++)
            System.out.println(sa.get(i) + " " + dict.lookUpWord(sa.get(i)));

//        sa.add("c");
//        sa.add("a");
//        sa.add("b");
//
//        run.showStringArray(sa);
//        sa.sort();
//        run.showStringArray(sa);


//        for (int i = 0; i < 10; i++) {
//            sa.add(String.valueOf(i));
//            run.showStringArray(sa);
//        }
//        sa.insert(0,"2");
//        run.showStringArray(sa);
//        sa.add("abcDs");
//        run.showStringArray(sa);
//
//        sa.remove(5);
//        run.showStringArray(sa);


    }

    private void showStringArray(StringArray a){

        for (int i = 0; i < a.size(); i++)
            System.out.println(i + ": " + a.get(i));

        System.out.println("Size: " + a.size());
        System.out.println("Struct size: " + a.getRealSize());
        System.out.println("Filled ratio: " + a.calculateRatio());
        System.out.println();

    }




}
