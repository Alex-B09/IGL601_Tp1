using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ThreeWayMergeConsoleApplication
{
    class Program
    {
        static void Main(string[] args)
        {
            const string PATH_FILE_ORIGINAL = "../../../../CompareOriginal";
            const string PATH_FILE_A = "../../../../CompareA";
            const string PATH_FILE_B = "../../../../CompareB";
            const string PATH_OUTPUT_FILE = "../../../../CompareSortie";

            int lineIndex = 0;
            List<string> lstContentFileOriginal = File.ReadAllLines(PATH_FILE_ORIGINAL).ToList();
            List<string> lstContentFileA = File.ReadAllLines(PATH_FILE_A).ToList();
            List<string> lstContentFileB = File.ReadAllLines(PATH_FILE_B).ToList();
            List<string> lstContentOutputFile = new List<string>();

            for (; lineIndex < lstContentFileOriginal.Count; ++lineIndex)
            {
                if (lineIndex < lstContentFileA.Count)
                {
                    if (lstContentFileOriginal[lineIndex] == lstContentFileA[lineIndex])
                    {
                        if (lineIndex < lstContentFileB.Count)
                            // Situation 1: Vu qu'on sait que le fichier A et le fichier original sont pareil, alors on sait que logiquement,
                            //               peu importe la valeur de la ligne actuel du fichier B, on peut l'ajouter au fichier de sortie (CompareOuput).
                            lstContentOutputFile.Add(lstContentFileB[lineIndex]);
                        else
                            // Situation 2 : Le fichier B a supprimé la ligne actuelle, mais pas le fichier A. Le fichier A est priorisé ici, et vu
                            //               qu'on sait que la ligne actuel du fichier A est égale à celle du fichier original, alors on ajoute la ligne actuelle
                            //               du fichier original.
                            lstContentOutputFile.Add(lstContentFileOriginal[lineIndex]);
                    }
                    else
                        // Situation 3: Le fichier A et le fichier original ne sont pas pareil, alors on met la ligne actuel du fichier A dans le fichier de sortie.
                        //              Dans ce cas, on s'en fou du fichier B, car on priorise le fichier A de toute façon.
                        lstContentOutputFile.Add(lstContentFileA[lineIndex]);
                } else if (lineIndex < lstContentFileB.Count)
                {
                    // Situation 4: Le fichier A est plus court que l'original, ce qui veut dire qu'à partir de maintenant, peu importe les valeurs du fichier B,
                    //              on peut l'ajouter dans le fichier de sortie.
                    lstContentOutputFile.Add(lstContentFileB[lineIndex]);
                }
            }

            for (; lineIndex < lstContentFileA.Count; ++lineIndex)
                // Situation 5: Le fichier A est plus gros que le fichier original, donc peu importe la valeur des lignes dans le fichier A, on les ajoute dans
                //              le fichier de sortie.
                lstContentOutputFile.Add(lstContentFileA[lineIndex]);

            for (; lineIndex < lstContentFileB.Count; ++lineIndex)
                // Situation 6: Le fichier B est plus gros que le fichier original et le fichier A, donc peu importe la valeur des lignes 
                //              dans le fichier B, on les ajoute dans le fichier de sortie.
                lstContentOutputFile.Add(lstContentFileB[lineIndex]);

            File.WriteAllLines(PATH_OUTPUT_FILE, lstContentOutputFile);            
        }
    }
}
