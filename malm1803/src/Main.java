import java.io.*;

public class Main {

    public static void main(String [] args) throws IOException {

        //Ouverture des 3 fichiers de lecture
        BufferedReader O = new BufferedReader(new FileReader("CompareOriginal"));
        BufferedReader A = new BufferedReader(new FileReader("CompareA"));
        BufferedReader B = new BufferedReader(new FileReader("CompareB"));

        //Ouverture du fichier d'écriture
        BufferedWriter S = new BufferedWriter((new FileWriter("CompareSortie")));

        //Tant que les 3 fichiers de lecture ne sont pas vide, on les parcoure ligne par ligne
        while (true) {
            String partOne = O.readLine();
            String partTwo = A.readLine();
            String partThree = B.readLine();

            if (partOne == null && partTwo == null && partThree == null)
                break;

            //On écrit le résultat de la comparaison dans le fichier d'écriture
            S.write(compare(partOne,partTwo,partThree)+"\n");
        }
        S.flush();
    }


    //Fonction permettant d'effectuer le 3-way merge
    private static String compare(String O, String A, String B){


        if(O==null)
            if(A==null)
                return B;


        if(A==null){
            if (B==null) return O;
            else return B;
        }

        //Cas où B est différents de l'origine
        if(O.equals(A)&&!O.equals(B)) return B;

        //Dans le cas d'un où A est différents et d'un conflits entre A,O et B on retourne A
        return A;
    }

}
