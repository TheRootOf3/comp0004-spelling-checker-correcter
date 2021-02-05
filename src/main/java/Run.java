import java.util.HashMap;
import java.util.Map;

public class Run {
    public static void main(String[] args) {
        Reader rd = new Reader();
        HashMap<String, Double> hm = rd.readTrigramsFreq("./src/main/resources/english_trigrams.txt");
        for (Map.Entry<String, Double> entry : hm.entrySet())
            System.out.println(entry.getKey() + " " + entry.getValue());

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
