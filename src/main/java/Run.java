
public class Run {
    public static void main(String[] args) {
        Run run = new Run();
        StringArray sa = new StringArray();
        sa.add("1");
        run.showStringArray(sa);
        sa.set(0, "2");
        run.showStringArray(sa);

    }

    public void showStringArray(StringArray a){

        for (int i = 0; i < a.size(); i++)
            System.out.println(i + ": " + a.get(i));

        System.out.println("Size: " + a.size());
        System.out.println("Struct size: " + a.getRealSize());
        System.out.println("Filled ratio: " + a.calculateRatio());
        System.out.println();
    }




}
