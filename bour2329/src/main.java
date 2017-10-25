import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Algorithme de fusion à trois chemins
 * 
 * Pré-condition : Le fichier CompareA, CompareB et CompareOriginal doivent être
 * présent dans le dossier parent de ce fichier Post-condition : Le résultat de
 * la fusion des trois fichiers dans le fichier de sortie nommé CompareSortie
 * 
 * @author Rémy
 * @version 1.0
 */
public class main
{
    /**
     * Classe principale
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        String lineCompA = "", lineCompB = "", lineCompO = "";
        BufferedReader brCompA, brCompB, brCompO;
        PrintWriter outCompS;

        try
        {
            // Ouverture des trois fichiers à fusionner
            brCompA = new BufferedReader(new FileReader("../CompareA"));
            brCompB = new BufferedReader(new FileReader("../CompareB"));
            brCompO = new BufferedReader(new FileReader("../CompareOriginal"));

            // Fichier de sortie
            // Si le fichier existe, son contenu sera effacé.
            // Si le fichier n'existe pas, il sera créé avec un contenu vide
            outCompS = new PrintWriter("../CompareSortie", "UTF-8");

            // Lecture d'une ligne de chaque fichier
            while (lineCompA != null || lineCompB != null || lineCompO != null)
            {
                lineCompA = brCompA.readLine();
                lineCompB = brCompB.readLine();
                lineCompO = brCompO.readLine();

                if (lineCompO != null)
                {
                    // Egalite des trois lignes
                    if (lineCompO.equals(lineCompA) && lineCompO.equals(lineCompB))
                        outCompS.write(lineCompO);
                    else
                    {
                        if (lineCompA != null)
                        {
                            if (lineCompO.equals(lineCompA))
                            {
                                if (lineCompB != null)
                                    // Ajoute B car O et A sont égaux. La
                                    // différence est donc forcément sur B.
                                    outCompS.write(lineCompB);
                                else
                                    System.out.println(
                                            "Erreur : conflit entre le fichier CompareA et CompareB \nCompareA : "
                                                    + lineCompA + "\nCompareB : " + lineCompB);
                            }
                            else
                            {
                                if (lineCompB != null)
                                {
                                    if (lineCompO.equals(lineCompB))
                                        // Ajoute A car O et B sont égaux et O
                                        // et A
                                        // sont différent donc la modification
                                        // est A
                                        outCompS.write(lineCompA);
                                    else
                                        System.out.println(
                                                "Erreur : conflit entre le fichier CompareA et CompareB \nCompareA : "
                                                        + lineCompA + "\nCompareB : " + lineCompB);
                                }
                                else
                                    // Ajoute A car O et A sont différent et B
                                    // est nul
                                    outCompS.write(lineCompA);
                            }
                        }
                        else
                        {
                            if (lineCompB != null)
                                // Ajoute B car A est nul et B n'est pas nul
                                outCompS.write(lineCompB);
                            else
                                // Ajoute O car A et B sont nuls
                                outCompS.write(lineCompO);
                        }
                    }
                }
                else
                {
                    if (lineCompA != null)
                    {
                        if (lineCompB == null)
                            // Ajoute A car O et B sont nuls
                            outCompS.write(lineCompA);
                        else
                        {
                            if (lineCompA.equals(lineCompB))
                                // Ajoute A car la modification sur A et B sont identiques et O est nul
                                outCompS.write(lineCompA);
                            else
                                System.out
                                        .println("Erreur : conflit entre le fichier CompareA et CompareB \nCompareA : "
                                                + lineCompA + "\nCompareB : " + lineCompB);
                        }
                    }
                    else if (lineCompB != null)
                        // Ajoute B car O et A sont nuls
                        outCompS.write(lineCompB);
                }
                // Saut de ligne
                outCompS.write("\r\n");
            }
            outCompS.close();
        }
        catch (IOException e)
        {
            System.out.println("Erreur : L'ouverture des fichiers a engendré une erreur.");
        }
    }
}