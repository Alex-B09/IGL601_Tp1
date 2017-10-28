import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try{
            ArrayList<ArrayList<String>> CompareFiles = new ArrayList<ArrayList<String>>();
            ArrayList<String> CompareSortie = new ArrayList<String>();

            File currentDirFile = new File("CompareSortie");
            String currentDir = currentDirFile.getAbsoluteFile().getParentFile().getParentFile().getAbsolutePath() + "\\";


            //region Lecture des fichiers
            FileReader fileReader = new FileReader(currentDir + "CompareOriginal");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            CompareFiles.add(new ArrayList<String>());
            while (bufferedReader.ready()) {
                CompareFiles.get(0).add(bufferedReader.readLine());
            }
            fileReader = new FileReader(currentDir + "CompareA");
            bufferedReader = new BufferedReader(fileReader);
            CompareFiles.add(new ArrayList<String>());
            while (bufferedReader.ready()) {
                CompareFiles.get(1).add(bufferedReader.readLine());
            }
            fileReader = new FileReader(currentDir + "CompareB");
            bufferedReader = new BufferedReader(fileReader);
            CompareFiles.add(new ArrayList<String>());
            while (bufferedReader.ready()) {
                CompareFiles.get(2).add(bufferedReader.readLine());
            }
            //endregion


            //Récupération du fichier contenant le plus de lignes
            int maxOfNums = Math.max(CompareFiles.get(0).size(), Math.max(CompareFiles.get(1).size(), CompareFiles.get(2).size()));

            //Initialisation de la liste pout le fichier de sortie
            for(int k=0; k < maxOfNums;k++){
                CompareSortie.add(new String());
            }

            for(int i=0; i < maxOfNums; i++){
                for (int j = 0 ; j < 3; j++){
                    //Si la liste correspondant au fichier "i" est aussi grand que le plus grand
                    if(CompareFiles.get(j).size() > i){
                        //Si la ligne n'est pas vide
                        if(!CompareFiles.get(j).get(i).isEmpty()){
                            //Si dans la liste de sortie la ligne est vide ou existe dejà avec le même contenu
                            if (CompareSortie.get(i).isEmpty() || CompareSortie.get(i).equals(CompareFiles.get(j).get(i))){
                                CompareSortie.set(i,CompareFiles.get(j).get(i));
                            }
                            else if (!CompareSortie.get(i).equals(CompareFiles.get(j).get(i))){
                                //Si conflit on garde compareA (i = 1)
                                if(i == 1 ){
                                    CompareSortie.set(i,CompareFiles.get(j).get(i));
                                }
                            }
                        }
                    }
                }
            }

            //Ecriture de l'Array List dans un fichier

            FileWriter writer = new FileWriter(currentDirFile.getCanonicalPath());
            for(String str: CompareSortie) {
                writer.write(str + "\n");
            }
            writer.close();

        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
