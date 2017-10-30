import java.io.*;

public class Merge {
    private String cheminfichierA;
    private String cheminfichierB;
    private String cheminfichierC;
    private String cheminresultat;
    private final String VIDE ="[|NULL|]";


    public Merge(String cheminfichierA, String cheminfichierB, String cheminfichierC, String cheminresultat){
        this.cheminfichierA = cheminfichierA;
        this.cheminfichierB = cheminfichierB;
        this.cheminfichierC = cheminfichierC;
        this.cheminresultat = cheminresultat;
        comparaisonfichier();
    }

    //méthode de comparaison des 3 fichiers

    public void comparaisonfichier() {

        String ligneA;
        String ligneB;
        String ligneC;

        BufferedReader readerfichierA;
        BufferedReader readerfichierB;
        BufferedReader readerfichierC;
        BufferedWriter writerficher;

        try {
            readerfichierA = new BufferedReader(new FileReader(cheminfichierA));
            readerfichierB = new BufferedReader(new FileReader(cheminfichierB));
            readerfichierC = new BufferedReader(new FileReader(cheminfichierC));
            writerficher = new BufferedWriter(new FileWriter(cheminresultat));


            //lecture des trois fichiers ligne par ligne tantque une des 3 lignes n'est pas null
            do{
                ligneA = readerfichierA.readLine();
                if(ligneA==null){
                    ligneA=VIDE;
                }

                ligneB = readerfichierB.readLine();
                if(ligneB==null){
                    ligneB=VIDE;
                }

                ligneC = readerfichierC.readLine();
                if(ligneC==null){
                    ligneC=VIDE;
                }

                //ecriture dans le fichier resultat
                String ecrire = comparaisonResultat(ligneA,ligneB,ligneC);
                if(!ecrire.equals(VIDE)) {
                    writerficher.write(ecrire);
                    writerficher.newLine();
                }
            }while ((!ligneA.equals(VIDE))||(!ligneB.equals(VIDE))||(!ligneC.equals(VIDE)));

            readerfichierA.close();
            readerfichierB.close();
            readerfichierC.close();
            writerficher.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// méthode de comparaison de 3 lignes
    public  String comparaisonResultat(String ligneA, String ligneB, String ligneC){
        System.out.println("ComparaisonResultat>> \n ligneA: "+ligneA+"\n ligneB: "+ligneB+"\n ligneC: "+ligneC);
        String ligneresultat;
        //Si au moins deux ligne sont égales alors on retoure cette ligne
        if(ligneA.equals(ligneB)){

            if(!ligneA.equals(VIDE)){
                return ligneA;
            }
            // ces deux lignes sont vides alors nous retournons ligne C
            return ligneC;
        }
        if(ligneA.equals(ligneC)){
            if(!ligneA.equals(VIDE)){
                return ligneA;
            }
            // ces deux lignes sont vides alors nous retournons ligne B
            return ligneB;
        }
        if(ligneB.equals(ligneC)){
            if(!ligneB.equals(VIDE)){
                return ligneB;
            }
            // ces deux lignes sont vides alors nous retournons ligne A
            return ligneA;
        }


        //Si les trois lignes sont différentes

        // Si la ligne A n'est pas vide on retroune celle-ci
        if(!ligneA.equals(VIDE)){
            return ligneA;
        }
        // Si la ligne B n'est pas vide on retroune celle-ci
        if(!ligneB.equals(VIDE)){
            return ligneB;
        }
        // Si la ligne C n'est pas vide on retroune celle-ci
        if(!ligneC.equals(VIDE)){
            return ligneC;
        }
        return "null";
    }
}
