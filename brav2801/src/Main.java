import java.io.File;
import java.io.IOException;

public class Main {

    public static final String PATH = "..\\";

    public static void main(final String... args){

        File compareA = new File(PATH + "CompareA");
        File compareB = new File(PATH + "CompareB");
        File compareOriginal = new File(PATH + "CompareOriginal");


        ThreeWayMerge twm = new ThreeWayMerge(compareA, compareB, compareOriginal);

        try {
            twm.loadFiles();
            twm.merge();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
