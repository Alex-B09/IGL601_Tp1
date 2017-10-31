/* Fait par: Jonathan Clavet-Grenier | CIP: claj2606 | Matricule: 16 070 207 
   ALGORITHME: On priorise toujours le fichier A. Si le fichier A a supprimé des lignes, on va garder les lignes supprimées, car on les considère comme des "éléments".
*/

using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace ThreeWayMergeConsoleApplication
{
    class Program
    {
        static void Main(string[] args)
        {
            /*************************************** SI LE PROGRAMME PLANTE, CHANGEZ LES PATHS ICI. ***************************************/
            const string PATH_FILE_ORIGINAL = "../CompareOriginal";
            const string PATH_FILE_A = "../CompareA";
            const string PATH_FILE_B = "../CompareB";
            const string PATH_OUTPUT_FILE = "../CompareSortie";

            int lineIndex = 0;

            List<string> lstContentFileOriginal = File.ReadAllText(PATH_FILE_ORIGINAL).Replace("\r", "").Split('\n').ToList();
            List<string> lstContentFileA = File.ReadAllText(PATH_FILE_A).Replace("\r", "").Split('\n').ToList();
            List<string> lstContentFileB = File.ReadAllText(PATH_FILE_B).Replace("\r", "").Split('\n').ToList();
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
                }
            }

            for (; lineIndex < lstContentFileA.Count; ++lineIndex)
                // Situation 4: Le fichier A est plus gros que le fichier original, donc peu importe la valeur des lignes dans le fichier A, on les ajoute dans
                //              le fichier de sortie.
                lstContentOutputFile.Add(lstContentFileA[lineIndex]);

            File.WriteAllText(PATH_OUTPUT_FILE, string.Join("\n", lstContentOutputFile));                        
        }
    }
}
