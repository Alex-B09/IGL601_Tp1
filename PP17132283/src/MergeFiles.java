import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MergeFiles {

    private File fileCompareA = null;
    private File fileCompareB = null;
    private File fileCompareOriginal = null;
    private File fileCompareOuptut = null;

    public MergeFiles(){

    }


    /** Copier les données du fichier dans une liste
     *
     * @param file : Fichier à lire
     */
    private List<String> copyFiletoArray(File file){
        List<String> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader((new FileReader(file)))) {

            //Récupération des données du fichier dans la liste
            list = br.lines().collect(Collectors.toList());

            //Fermeture du fichier à lire
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    /** Fonction de génération d'un fichier : créer le fichier dans le cas où il n'existe pas
     *
     * @param nameFile : nom du fichier
     * @return un fichier
     */
    private File genFile(String nameFile) throws IOException {

        File file = new File(nameFile);

        //Création d'un fichier s'il n'éxiste pas
        if( !file.exists() ){
            file.createNewFile();
        }
        return file;
    }

    /** Copier une liste dans un fichier de sortie
     *
     * @param list : liste qui contient les données
     * @param file : fichier dans lequel les données doivent etre écrites
     */
    private void copyArrayToFile(List<String> list, File file){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            //ecriture des données de la liste dans le fichier (avec une nouvelle ligne pour chaque donnée)
            for (String s : list) {
                writer.write(s);
                writer.newLine();
            }

            //Flush et fermuture du fichier à écrire
            writer.flush();
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param nameFileA : nom du fichier A
     * @param nameFileB : nom du fichier B
     * @param nameFileOriginal : nom du fichier Original
     * @param nameFileOutput : nom du fichier de Sortie
     * @throws Exception : exception pour la création du fichier de sortie et pour les index des listes
     */
    public void Merge(String nameFileA, String nameFileB, String nameFileOriginal, String nameFileOutput) throws Exception{

        //Création des fichier pour la lecture ou l'écriture
        fileCompareA = new File(nameFileA);
        fileCompareB = new File(nameFileB);
        fileCompareOriginal = new File(nameFileOriginal);
        fileCompareOuptut = genFile(nameFileOutput);

        //index de navigation des listes
        int index = 0;

        //Création des tableaux
        List<String> listFileA;
        List<String> listFileB;
        List<String> listFileOriginal;
        List<String> listFileOutput = new ArrayList<>();

        //Copie les fichier dans des listes (taille des listes = plus grand nombre de ligne des fichiers)
        listFileA = copyFiletoArray(fileCompareA);
        listFileB = copyFiletoArray(fileCompareB);
        listFileOriginal = copyFiletoArray(fileCompareOriginal);

        //Parcours des listes pour créer la liste des doonées à sortir
        while( index < listFileA.size() || index < listFileB.size() || index < listFileOriginal.size()){

            if(index < listFileA.size() && index < listFileB.size() && index < listFileOriginal.size()){ //index < A && B && Orig
                if( listFileA.get(index).equals(listFileB.get(index)) ) //Test A=B => Output:A
                    listFileOutput.add(listFileA.get(index));
                else if( listFileA.get(index).equals(listFileOriginal.get(index))) //Test A=Original => Output:B
                    listFileOutput.add(listFileB.get(index));
                else if( listFileB.get(index).equals(listFileOriginal.get(index))) //Test B=Original => Output:A
                    listFileOutput.add(listFileA.get(index));
            } else if(index >= listFileA.size() && index < listFileB.size() && index < listFileOriginal.size()){ //A < index < B && Orig
                listFileOutput.add(listFileB.get(index));
            } else if(index < listFileA.size() && index >= listFileB.size() && index < listFileOriginal.size()){ //B < index < A && Orig
                listFileOutput.add(listFileA.get(index));
            } else if(index < listFileA.size() && index < listFileB.size() && index >= listFileOriginal.size()){ //Orig < index < A && B
                System.out.println("CONFLIT ENTRE DEUX VERSIONS : CHOIX DE L'UTILISATEUR !");
            }

            //incrémentation de l'index
            index++;
        }

        //Copie de la liste des données de sortie (Récupérer auparavant : voir ci-dessus) dans un fichier
        copyArrayToFile(listFileOutput,fileCompareOuptut);
    }
}
