import java.io.*;
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

            //br returns as stream and convert it into a List
            list = br.lines().collect(Collectors.toList());

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


    /**
     *
     * @param nameFileA : nom du fichier A
     * @param nameFileB : nom du fichier B
     * @param nameFileOriginal : nom du fichier Original
     * @param nameFileOutput : nom du fichier de Sortie
     * @throws Exception : exception pour la création du fichier de sortie et pour les index des listes
     */
    public void Merge(String nameFileA, String nameFileB, String nameFileOriginal, String nameFileOutput) throws Exception{

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

        while( index < listFileA.size() || index < listFileB.size() || index < listFileOriginal.size()){

            if(index < listFileA.size() && index < listFileB.size() && index < listFileOriginal.size()){
                if( listFileA.get(index).equals(listFileB.get(index)) ) //Test A=B => Output:A
                    listFileOutput.add(listFileA.get(index));
                else if( listFileA.get(index).equals(listFileOriginal.get(index))) //Test A=Original => Output:B
                    listFileOutput.add(listFileB.get(index));
                else if( listFileB.get(index).equals(listFileOriginal.get(index))) //Test B=Original => Output:A
                    listFileOutput.add(listFileA.get(index));
            } else if(index >= listFileA.size() && index < listFileB.size() && index < listFileOriginal.size()){
                listFileOutput.add(listFileB.get(index));
            } else if(index < listFileA.size() && index >= listFileB.size() && index < listFileOriginal.size()){
                listFileOutput.add(listFileA.get(index));
            } else if(index < listFileA.size() && index < listFileB.size() && index >= listFileOriginal.size()){
                System.out.println("CONFLIT ENTRE DEUX VERSIONS : CHOIX DE L'UTILISATEUR !");
            }



            //incrémentation de l'index
            index++;
        }

        listFileOutput.forEach(System.out::println);

    }

}
