import java.io.*;

public class Fusion {

    private static final String FILENAME [] = {"./CompareA","./CompareB","./CompareOriginal"};

    String merge[] = new String[36];
    BufferedReader br = null;
    String compareA [] = new String[36];
    String compareB [] = new String[36];
    String compareOriginal [] = new String[36];


    public Fusion(){

        try {

            for(int i = 0;i<FILENAME.length;i++) {

                br = new BufferedReader(new FileReader(FILENAME[i]));

                switch(i)
                {
                    case 0:// Remplissage du tableau compareA qui represente le contenu du fichier CompareA
                        for (int j = 0; j < 36; j++) {
                            compareA[j] = br.readLine();
                            if (compareA[j] == null){
                                compareA[j] = "";
                            }
                        }
                        break;
                    case 1:// Remplissage du tableau compareB qui represente le contenu du fichier CompareB
                        for (int j = 0; j < 36; j++) {
                            compareB[j] = br.readLine();
                            if (compareB[j] == null){
                                compareB[j] = "";
                            }
                        }
                        break;
                    case 2:// Remplissage du tableau compareB qui represente le contenu du fichier CompareOriginal
                        for (int j = 0; j < 36; j++) {
                            compareOriginal[j] = br.readLine();
                            if (compareOriginal[j] == null){
                                compareOriginal[j] = "";
                            }
                        }
                        break;
                    default:
                        System.out.println("Aucun fichier a lire !");
                }
            }

            //Comparaison des differents tableaux pour effectuer la fusion dans le tableu "merge" qui represente le contenu du fichier CompareSortie
            for (int i = 0; i < 36; i++) {
                if (compareOriginal[i].equals(compareA[i]) && compareOriginal[i].equals(compareB[i])) {
                    merge[i] = compareA[i];
                } else if ((compareA[i] != null) && (!(compareOriginal[i].equals(compareA[i])))) {
                    merge[i] = compareA[i];
                } else if ((compareB[i] != null) && (!(compareOriginal[i].equals(compareB[i])))){
                    merge[i] = compareB[i];
                }
            }
            //
            FileWriter fr = new FileWriter("./CompareSortie");
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter out = new PrintWriter(br);

            for(int i = 0;i<36;i++){
                out.write(merge[i]);
                out.write("\n");
            }
            out.close();
            System.out.println("La fusion a bien été effectuée !");


        } catch (IOException e) {

            e.printStackTrace();

        }


    }

    public static void main(String[] args) {

        Fusion f = new Fusion();
    }

}
