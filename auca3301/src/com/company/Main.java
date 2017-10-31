package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        FileReader fileReader;
        BufferedReader bufferedReader;
        ArrayList<String> nameInputFile = new ArrayList<>(); // Contient les noms des trois fichiers d'entrée
        ArrayList<ArrayList<String>> compFile = new ArrayList<>(); // Contient les caractères des 3 fichiers d'entrée
        int i=0;
        ArrayList<String> fileOut = new ArrayList<>(); //Contenu à écrire dans le fichier de sortie

        nameInputFile.add("CompareOriginal");
        nameInputFile.add("CompareA");
        nameInputFile.add("CompareB");

        //Pour chaque fichier d'entrée on stocke les caractères de compFile
        for (String string : nameInputFile) {
            fileReader = new FileReader(string);
            bufferedReader = new BufferedReader(fileReader);
            compFile.add(new ArrayList<String>());
            while (bufferedReader.ready()) {
                compFile.get(i).add(bufferedReader.readLine());
            }
            i++;
        }

        //Permet de connaître la liste de compFile qui a le plus de caractères
        int max = Math.max(compFile.get(0).size(),Math.max(compFile.get(1).size(),compFile.get(2).size()));

        //Fusion des fichiers
        for(int k=0;k<max;k++) {
            fileOut.add(new String());
            for(int l=0;l<3;l++) {
                if(compFile.get(l).size()>k) {
                    if(!compFile.get(l).get(k).isEmpty()) {
                        if(fileOut.get(k).isEmpty()||fileOut.get(k).equals(compFile.get(l).get(k))) {
                            fileOut.set(k,compFile.get(l).get(k));
                        }
                        else if (!fileOut.get(k).equals(compFile.get(l).get(k))) {
                            if(l==1){
                                fileOut.set(k,compFile.get(l).get(k));
                            }
                        }
                    }
                }
            }
        }
        //Ecriture dans le fichier de sortie
        FileWriter outFile = new FileWriter("CompareSortie");
        for (String s : fileOut) {
            outFile.write(s+"\n");
        }
        outFile.close();
    }
}
