
public class Run {
    public static void main(String[] args) {
        Run run = new Run();
        StringArray sa = new StringArray();

//        Reader rd = new Reader("C:\\Users\\aszab\\OneDrive\\STUDIA\\2020_2021_Modules\\COMP0004\\Coursework1\\src\\main\\resources\\words");
        Reader rd = new Reader();
//        run.showStringArray(rd.readUserFile("C:\\Users\\aszab\\OneDrive\\STUDIA\\2020_2021_Modules\\COMP0004\\Coursework1\\src\\main\\resources\\words"));
//        run.showStringArray(rd.readInput().getParsedSA());
        sa = rd.readInput().getParsedSA();
        SpellChecker sc = new SpellChecker("C:\\Users\\aszab\\OneDrive\\STUDIA\\2020_2021_Modules\\COMP0004\\Coursework1\\src\\main\\resources\\words");

//        long startTime;
//        startTime = System.nanoTime();
//        System.out.println(sc.lookUpWord("Flour"));
//        System.out.println(System.nanoTime() - startTime);
//
//        startTime = System.nanoTime();
//        System.out.println(sc.lookUpWordLinear("Flour"));
//        System.out.println(System.nanoTime() - startTime);

//        System.out.println(sc.lookUpWord("really"));

        for (int i = 0; i < sa.size(); i++)
            System.out.println(sa.get(i) + " " + sc.lookUpWord(sa.get(i)));



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
