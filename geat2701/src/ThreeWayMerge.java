import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ThreeWayMerge {
    private FileReader fichierA;
    private FileReader fichierB;
    private FileReader fichierOriginal;


    public ThreeWayMerge(FileReader fichierA, FileReader fichierB, FileReader fichierOriginal) {
        this.fichierA = fichierA;
        this.fichierB = fichierB;
        this.fichierOriginal = fichierOriginal;
    }

    
    public void merge(FileWriter fichierSortie){
        try {
            BufferedReader brA = new BufferedReader(fichierA);
            BufferedReader brB = new BufferedReader(fichierB);
            BufferedReader brO = new BufferedReader(fichierOriginal);

            String ligneA;
            String ligneB;
            String ligneO;

            String ligneS;

            //Parcours les trois fichiers
            while((ligneA = brA.readLine()) != null | (ligneB = brB.readLine()) != null | (ligneO = brO.readLine()) != null){ //Si la ligne existe sur au moins un fichier

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
                        ligneS = "#Conflit!      #1["+ligneA+"]         #2["+ligneB+"]";
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
