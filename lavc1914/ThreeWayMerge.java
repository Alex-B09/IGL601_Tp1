import java.io.*;

public class ThreeWayMerge {

    private FileReader fA;
    private FileReader fB;
    private FileReader fOriginal;

    public ThreeWayMerge(String fA, String fB, String fOriginal){

        try {

            this.fA = new FileReader(fA);
            this.fB = new FileReader(fB);
            this.fOriginal = new FileReader(fOriginal);

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        }
    }

    /*
     * @Def : Réalise un "merge" entre 2 fichier par rapport a un fichier original (3-Way-Merge)
     * @Input : Path vers le fichier de sortie
     * @Output : Nothing, mais il créé un fichier de "merge" des 3 fichiers
     */
    public void mergeFiles(String wayoutputFile) {

        BufferedReader buffReadA = new BufferedReader(fA);
        BufferedReader buffReadB = new BufferedReader(fB);
        BufferedReader buffReadOriginal = new BufferedReader(fOriginal);
        String lineA, lineB, lineOriginal, out;

        try {

            PrintWriter outputFile = new PrintWriter(wayoutputFile, "UTF-8");

            while((lineA = buffReadA.readLine()) != null | (lineB = buffReadB.readLine()) != null | (lineOriginal = buffReadOriginal.readLine()) != null){

                out = compare(lineA, lineB, lineOriginal);
                outputFile.println(out);

            }

            outputFile.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    /*
     * @Def : Fonction comparant 3 chaines de caractère celon les critères du 3-Way-Merge
     * @Input : 3 Chaines de caractères
     * @Output : La chaine la plus légitime d'être sauvegardée
     */
    public String compare(String a, String b, String o){

        if (a != null && !a.isEmpty()){

            if  (b != null && !b.isEmpty()) {

                if (a.equals(b)){

                    return a;

                }else if (o != null && !o.isEmpty()){

                    if(a.equals(o))
                        return b;
                    else if (b.equals(o))
                        return a;
                    else
                      return "Conflit";

                }

            } else {

                return a;
            }

        } else if (b != null && !b.isEmpty()){

            return b;

        }
        return o;
    }


    public static void main(String [ ] args) {

      if (System.getProperty("os.name").equals("Linux")){

        ThreeWayMerge twm = new ThreeWayMerge(
                System.getProperty("user.dir") + "/../CompareA",
                System.getProperty("user.dir") + "/../CompareB",
                System.getProperty("user.dir") + "/../CompareOriginal"
        );

        twm.mergeFiles(System.getProperty("user.dir") + "/../CompareSortie");

      } else {

        ThreeWayMerge twm = new ThreeWayMerge(
                "..\\CompareA",
                "..\\CompareB",
                "..\\CompareOriginal"
        );

        twm.mergeFiles("..\\CompareSortie");

      }
    }

}
