import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            FileReader fileA = new FileReader("..\\CompareA");
            FileReader fileB = new FileReader("..\\CompareB");
            FileReader fileOriginal = new FileReader("..\\CompareOriginal");

            ThreeWayMerge twm = new ThreeWayMerge(fileA, fileB, fileOriginal);

            twm.merge(new FileWriter("..\\CompareSortie"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
