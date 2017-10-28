import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Algorithme de fusion à trois chemins.
 * 
 * En raison de l'absence de manipulation humaine sur le programme, la gestion
 * des conflits a été réglées de manière très simple. Si les trois fichiers ont
 * une version différente à une position donnée, le fichier CompareA le
 * remporte.
 * 
 * Pré-condition : Le fichier CompareA, CompareB et CompareOriginal doivent être
 * présent dans le dossier parent de ce fichier
 * 
 * Post-condition : Le résultat de la fusion des trois fichiers dans le fichier
 * de sortie nommé CompareSortie
 * 
 * @author Rémy BOUTELOUP - Matricule 17 132 265 - CIP bour2329
 * @version 1.0
 */
public class Merge
{
    private String lineCompA, lineCompB, lineCompO;
    private BufferedReader brCompA, brCompB, brCompO;
    private PrintWriter outCompS;

    /**
     * Contructeur par défaut
     * 
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    private Merge() throws FileNotFoundException, UnsupportedEncodingException
    {
        lineCompA = "";
        lineCompB = "";
        lineCompO = "";

        // Ouverture des trois fichiers à fusionner
        brCompA = new BufferedReader(new FileReader("../CompareA"));
        brCompB = new BufferedReader(new FileReader("../CompareB"));
        brCompO = new BufferedReader(new FileReader("../CompareOriginal"));

        // Fichier de sortie
        // Si le fichier existe, son contenu sera effacé.
        // Si le fichier n'existe pas, il sera créé avec un contenu vide
        outCompS = new PrintWriter("../CompareSortie", "UTF-8");
    }

    /**
     * Lecture d'une ligne dans chacun des fichiers
     * 
     * @throws IOException
     */
    private void readLines() throws IOException
    {
        lineCompA = brCompA.readLine();
        lineCompB = brCompB.readLine();
        lineCompO = brCompO.readLine();
    }

    /**
     * Production du fichier CompareSortie à partie des 3 fichiers de départs
     * 
     * @throws IOException
     */
    private void runMerge() throws IOException
    {
        // Lecture d'une ligne dans chacun des fichiers
        readLines();

        // Tant que les fichiers n'ont pas été entierement parcourus
        while (lineCompA != null || lineCompB != null || lineCompO != null)
        {
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
                                // Ajoute A car B est nul.
                                outCompS.write(lineCompO);
                        }
                        else
                            // Ajoute A car O et A sont différents, inutile de
                            // savoir l'état de B car c'est A qui l'enporte en
                            // cas de conflit
                            outCompS.write(lineCompA);
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
                    // Ajoute A car A l'emporte sur B
                    outCompS.write(lineCompA);
                else
                    // Ajoute B car O et A sont nuls.
                    outCompS.write(lineCompB);
            }
            // Saut de ligne
            outCompS.write("\r\n");

            // Lecture d'une ligne dans chacun des fichiers
            readLines();
        }
        outCompS.close();
    }

    /**
     * Classe principale. Initialisation des attributs et lancement du 3 way
     * merge.
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        Merge merge;
        try
        {
            merge = new Merge();
            merge.runMerge();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Erreur : L'ouverture des fichiers a engendré une erreur.");
        }
        catch (UnsupportedEncodingException e)
        {
            System.out.println("Erreur : Le caractère d'encodage n'est pas supporté.");
        }
        catch (IOException e)
        {
            System.out.println("Erreur : Erreur lors de la lecture d'un des fichiers.");
        }
    }
}