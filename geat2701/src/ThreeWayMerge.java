import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Cette classe permet de faire 3-Way merge. Pour merger, appeler la fonction merge(FileWriter fichierSortie).
 * Nous considérons ici que la suppression d'une ligne est considéré comme un changement.
 */
public class ThreeWayMerge {
    private FileReader fichierA;
    private FileReader fichierB;
    private FileReader fichierOriginal;

    /**
     * Le constructeur de la classe.
     * @param fichierA un fichier qui doit être fusionner
     * @param fichierB un autre fichier qui doit être fusionner
     * @param fichierOriginal le fichier original des fichiers A et B
     */
    public ThreeWayMerge(FileReader fichierA, FileReader fichierB, FileReader fichierOriginal) {
        this.fichierA = fichierA;
        this.fichierB = fichierB;
        this.fichierOriginal = fichierOriginal;
    }

    /**
     * Cette méthode permet d'effectuer un merge dans un fichier de sortie spécifique.
     * Note: il est possible de faire plusieurs merges avec la même instance, mais il faut que le FileWriter en paramètre
     * soit différent.
     * @param fichierSortie le fichier de sortie.
     */
    public void merge(FileWriter fichierSortie){
        try {
            BufferedReader brA = new BufferedReader(fichierA);
            BufferedReader brB = new BufferedReader(fichierB);
            BufferedReader brO = new BufferedReader(fichierOriginal);

            String ligneA;
            String ligneB;
            String ligneO;

            String ligneS;

            int i = 0; //numéro de ligne (pour le code d'erreur)

            //Parcours les trois fichiers
            while((ligneA = brA.readLine()) != null | (ligneB = brB.readLine()) != null | (ligneO = brO.readLine()) != null){ //Si la ligne existe sur au moins un fichier

                i++;
                ligneA = makeStringNotNull(ligneA);
                ligneB = makeStringNotNull(ligneB);
                ligneO = makeStringNotNull(ligneO);

                if(ligneO.equals(ligneA) && ligneO.equals(ligneB)){ //si toutes les lignes sont égales
                    ligneS = ligneO;
                }else if(ligneO.equals(ligneA) && !ligneO.equals(ligneB)){ //Si la ligne du fichier B a été modifié
                    ligneS = ligneB;
                }else if(!ligneO.equals(ligneA) && ligneO.equals(ligneB)){ //Si la ligne du fichier A a été modifié
                    ligneS = ligneA;
                }else{ //Si les lignes du fichier A et du fichier B ont été modifiés

                    if(ligneA.equals(ligneB)){ //On vérifie si ce sont les mêmes lignes. Si c'est le cas, il n'y a pas de conflits
                        ligneS = ligneA;
                    }else{ // Cas de conflit
                        // Comme c'est par résolution automatique, on va envoyer les deux modifications dans le fichier de sortie
                        //Et donner la possibilité à l'utilisateur de résoudre l'incident:
                        ligneS = "/!\\ Conflit !     #1["+ligneA+"]         #2["+ligneB+"]";
                        System.err.println("Conflit à la ligne n°"+i);
                    }
                }
                ligneS += "\n";
                fichierSortie.write(ligneS);
            }



            //Fermeture des buffers
            brA.close();
            brB.close();
            brO.close();

            fichierSortie.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remplace un string null par un string vide
     * @param ligne que l'on souhaite remplacer si null
     * @return un string non null
     */
    private String makeStringNotNull(String ligne){
        if(ligne==null)
            return "";
        else
            return ligne;
    }
}
