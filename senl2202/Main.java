/* Par Laurent Senécal-Léonard 
 * Dans le cadre du cours IGL601
 * Remis le 30 octobre 2017
 */

package threewaymerge;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		
		// Array contenant l'ensemble des lignes à écrire dans le fichier de sortie
		ArrayList<String> arrayStringAtLine = new ArrayList<String>(); 

		// Créations des variables locales et instancier les lecteurs de fichier
		FileReader cmpA = new FileReader("./CompareA");
		LineNumberReader nbA = new LineNumberReader(cmpA);
		
		FileReader cmpB = new FileReader("./CompareB");
		LineNumberReader nbB = new LineNumberReader(cmpB);
	
		FileReader cmpO = new FileReader("./CompareOriginal");
		LineNumberReader nbO = new LineNumberReader(cmpO);
		
		// Trouver le fichier ayant le plus grand nombre de lignes et garder cette valeur
		long maxNbLignes = Math.max((Files.lines(Paths.get("CompareA")).count()), Files.lines(Paths.get("CompareB")).count());
		int maxAbsolue = (int)Math.max(maxNbLignes, Files.lines(Paths.get("CompareOriginal")).count());
		
		String lineO; 		// String pour une ligne dans le fichier original
		String lineA; 		// String pour une ligne dans le fichier A
		String lineB; 		// String pour une ligne dans le fichier B
		int flag;     		// Flag qui indique si il y a une ligne différence parmis les trois fichiers  
		String valueAtFlag = null; // Valeur a assigné lorsque le flag est activé (1)
		int i = 0;
		while(i<maxAbsolue) {
			
			flag = 0;
			valueAtFlag = null;
			
			// Ajuster le numéro de ligne
			nbA.setLineNumber(i);
			nbB.setLineNumber(i);
			nbO.setLineNumber(i);
			
			// Recueillir le string de la ligne courrante pour chacun des fichiers
			lineO = nbO.readLine();
			lineA = nbA.readLine();
			lineB = nbB.readLine();
			
			// Lorsque qu'un String dans un des fichier est NULL, lui donner la valeur "vide"
			if(lineO == null) lineO = "vide";
			if(lineA == null) lineA = "vide";
			if(lineB == null) lineB = "vide";
			
			// Comparer chacune des lignes courantes des trois fichiers. Si il y a différence, flag = 1
			if(!(lineO.equals(lineA))) flag = 1;
			else if(!(lineO.equals(lineB))) flag = 1;
			else if(!(lineA.equals(lineB))) flag = 1;
			
			// Lorsqu'une différence entre les trois fichier est détectée.. A est priorisé ici 
			if(flag == 1) {
				// Si le string de la ligne du fichier A n'est pas vide, prendre sa valeur.
				if(!lineA.equals("") && !lineA.equals("vide")) {
					valueAtFlag = lineA;
				}
				// Si le string de la ligne du fichier B n'est pas vide, prendre sa valeur.
				else if(!lineB.equals("") && !lineB.equals("vide")) {
					valueAtFlag = lineB;
				}
				// Si le string de la ligne du fichier O n'est pas vide, prendre sa valeur.
				else if(!lineO.equals("") && !lineO.equals("vide")) {
					valueAtFlag = lineO;
				}
			} else valueAtFlag = lineO; // Quand le flag = 0, prendre le string de la ligne du fichier original
			
			// Insérer la ligne dans le array.
			arrayStringAtLine.add(valueAtFlag);		
			i++;
		}
		
		// Fermer les fichiers
		nbA.close();
		nbB.close();
		nbO.close();
		
		// Créer le fichier de sortie nommé "CompareSortie"
		File cmpSortie = new File("CompareSortie"); 
		cmpSortie.createNewFile();
		PrintStream cmpS = new PrintStream(cmpSortie);
		i = 0;
		// Écrire les éléments du array dans le fichier CompareSortie
		while(i < arrayStringAtLine.size()){
			cmpS.println(arrayStringAtLine.get(i));
			i++;
		}
		// Fermer le fichier CompareSortie
		cmpS.close();
	}
}
