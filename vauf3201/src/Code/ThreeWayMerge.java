/*==================================================================/

    Implémentation du 3-way merge en Java
    
    Créateur: Frédéric Vaugeois
    Version: 1.0.0
    
    Paramètres d'entrée:
        1- Nom du fichier source (CompareSource)
        2- Nom du fichier A (CompareA)
        3- Nom du fichier B (CompareB)
        
    Sorties du programme:
        1- Fichier d'output (CompareSortie):
            - Résultat du 3-way merge
            - Les lignes en conflit restent identiques à la source
            
            
    Références:
    
        BufferedReader: 
            - https://docs.oracle.com/javase/7/docs/api/java/io/BufferedReader.html
            
        BufferedWriter:
            - https://docs.oracle.com/javase/7/docs/api/java/io/BufferedWriter.html
            
        3-way merge:
            - http://www.drdobbs.com/tools/three-way-merging-a-look-under-the-hood/240164902
            - https://www.araxis.com/merge/documentation-windows/three-way-file-comparison-and-merging.en
            - https://en.wikipedia.org/wiki/Merge_(version_control)

/==================================================================*/

package Code;

import java.io.*;

public class ThreeWayMerge
{
    public static void main(String[] args) throws IOException
    {
        // Déclaration des variables
        String sourceLine;
        String f1Line;
        String f2Line;

        try
        {
            // On ouvre les trois fichiers
            FileInputStream source = new FileInputStream(args[0]);
            FileInputStream f1 = new FileInputStream(args[1]);
            FileInputStream f2 = new FileInputStream(args[2]);
            
            // On crée le fichier d'output
            FileOutputStream outputFile = new FileOutputStream("CompareSortie");

            // On crée un Reader de ces trois fichiers
            BufferedReader bSource = new BufferedReader(new InputStreamReader(source));
            BufferedReader bF1 = new BufferedReader(new InputStreamReader(f1));
            BufferedReader bF2 = new BufferedReader(new InputStreamReader(f2));
            
            // On crée "l'écriveur" du fichier d'output
            Writer output = new BufferedWriter(new OutputStreamWriter(outputFile, "UTF-8"));

            // Tant qu'on n'est pas rendu à la dernière ligne des fichiers (on met les trois
            // fichiers dans la condition car il se peut que l'un finisse avant l'autre...)
            while ((sourceLine = bSource.readLine()) != null 
                    && (f1Line = bF1.readLine()) != null
                    && (f2Line = bF2.readLine()) != null)
            {

                // Si la valeur de la ligne pour les fichiers F1 et F2 est différente
                if ((!(f1Line.contains(f2Line))) || (!(f2Line.contains(f1Line))))
                {
                    // Si le fichier 1 a la même valeur que dans le fichier source
                    if (f1Line.contains(sourceLine) && sourceLine.contains(f1Line))
                    {
                        // Alors celui qui a fait un changement est le fichier 2, donc
                        // c'est la changement qu'il a fait qu'on conserve. On imprime
                        // donc la ligne du fichier 2 dans le fichier d'output
                        output.write(f2Line + "\n");
                    }
                    // Sinon, si le fichier 2 a la même valeur que dans le fichier source
                    else if (f2Line.contains(sourceLine) && sourceLine.contains(f2Line))
                    {
                        // Même logique, mais avec le fichier 1
                        output.write(f1Line + "\n");
                    }
                    // Sinon, si les deux fichiers sont différents et qu'ils sont tous les
                    // deux différents de la source pour cette ligne
                    else
                    {
                        // On garde la valeur originale (parce que c'est moi qui décide...)
                        output.write(sourceLine + "\n");
                    }
                }
                // Sinon
                else
                {
                    // On met la valeur qui était là à la source
                    output.write(sourceLine + "\n");
                }
            }
            // On ferme les fichiers
            bSource.close();
            bF1.close();
            bF2.close();
            output.close();
        }
        // Gestion des erreurs
        catch (IOException e)
        {
            // On imprime l'erreur
            System.out.println(e);
        }
    }

}
