/**
 * Vincent PELOTON
 * CIP : pelv2610
 * MAtricule : 17 132 309
 */

import java.io.*;


public class ThreeWayMerge {

    private File fileA;
    private File fileB;
    private File origin;
    private File output;


    public ThreeWayMerge(){
        fileA = new File("..\\CompareA");
        fileB = new File("..\\CompareB");
        origin = new File("..\\CompareOriginal");
        output = new File("CompareSortie");
    }


    public static void main(String[ ] args) throws IOException {
        ThreeWayMerge merge=new ThreeWayMerge();
        try {
            merge.mergeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mergeFile() throws IOException{
        BufferedReader brFileA = new BufferedReader(new FileReader(fileA));//Pour parcourir le fichier A
        BufferedReader brFileB = new BufferedReader(new FileReader(fileB));//Pour parcourir le fichier B
        BufferedReader brFileOrigin = new BufferedReader(new FileReader(origin));//Pour parcourir le fichier Original

        String lineA;
        String lineB;
        String lineOrigin;

        PrintWriter fw = new PrintWriter(output);//Pour ecrire le fichier de sortie
        while((lineA = brFileA.readLine()) != null | (lineB = brFileB.readLine()) != null | (lineOrigin = brFileOrigin.readLine()) != null){//Tant que l'on pas atteint la fin de chaque fichier

            if(lineA!=null && !lineA.isEmpty()){//Si on a pas fini de parcourir le fichier A et la ligne n'est pas vide
                if(lineB!=null && !lineB.isEmpty()){//Si on a pas fini de parcourir le fichier B et la ligne n'est pas vide
                    if(lineA.equals(lineB)){//si la ligne du fichier A est la même que celle du fichier B
                        fw.println(lineA);
                    }
                    else if(lineOrigin!=null && !lineOrigin.isEmpty()){//Sinon on compare avec l'original
                        if(lineA.equals(lineOrigin)){
                            fw.println(lineB);
                        }
                        else if(lineB.equals(lineOrigin)) {
                            fw.println(lineA);
                        }
                    }
                }
                else{
                    fw.println(lineA);
                }
            }
            else if(lineB!=null && !lineB.isEmpty()){//Si on a fini de parcourir le fichier A ou que la ligne est vide
                                                     //Mais que l'on a pas fini de parcourir le fichier B et que sa ligne n'est pas vide
                fw.println(lineB);
            }
            else if(lineOrigin!=null && !lineOrigin.isEmpty()){//Si on a fini de parcourir le fichier A ou que la ligne est vide
                                                               //Et que l'on a fini de parcourir le fichier B ou que la ligne est vide
                fw.println(lineOrigin);
            }

        }
        brFileA.close();
        brFileB.close();
        brFileOrigin.close();
        fw.close();

    }

}
