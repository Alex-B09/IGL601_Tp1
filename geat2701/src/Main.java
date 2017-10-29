import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            //Création des readers:
            FileReader fileA = new FileReader("..\\CompareA");
            FileReader fileB = new FileReader("..\\CompareB");
            FileReader fileOriginal = new FileReader("..\\CompareOriginal");

            //Création du 3 way merge avec les fichiers d'entrées
            ThreeWayMerge twm = new ThreeWayMerge(fileA, fileB, fileOriginal);

            //Création du 3 way merge avec un fichier de sortie
            twm.merge(new FileWriter("..\\CompareSortie"));
            // On considère les suppressions de lignes comme des modifications, il est donc normal que dans le fichier
            // de sortie certaines parties présentes dans le fichier original soient supprimés.
            // J'ai considéré que c'était ma meilleur option  considérant de ce que j'ai compris du 3-Way merge. Il s'agit
            // d'un choix délibéré.

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
