import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Un algorithme qui effectue un 3-Way merge, une ligne à la fois,
 * entre un fichier original "CompareOriginal" et 2 fichiers "CompareA" et "CompareB".
 * présents dans le même dossier que la classe Java.
 * Un fichier texte "CompareSortie" est généré dans ce même dossier.
 * 
 * @author barm2529
 *
 */
public class ThreeWayMergeMain {

	public static void main(String[] args) {

		//Le main contient l'algorithme de 3-way merge en soi, les autres fonctions ne servent qu'a simplifier le code.

		//On récupère le contenu ligne-par-ligne, de chaque document.
		ArrayList<String> contentOriginalFile = getLinesOfFile("CompareOriginal");
		ArrayList<String> contentFile1 = getLinesOfFile("CompareA");
		ArrayList<String> contentFile2 = getLinesOfFile("CompareB");
		ArrayList<String> contentMergedFile = new ArrayList<>();

		//On calcule le nombre de lignes du plus grand fichier entre A et B
		int maxSize = getMaxSize(contentFile1, contentFile2);

		System.out.println("Debut du processus de 3-way merge.");
		if(maxSize>0) {

			//On choisit arbitrairement de prendre le plus grand fichier
			//comme reference en cas de conflits, puisqu'aucune interaction humaine n'est acceptee.
			ArrayList<String> contentLongFile=contentFile1;
			ArrayList<String> contentShortFile=contentFile2;
			if(maxSize==contentFile2.size()) {
				contentLongFile=contentFile2;
				contentShortFile=contentFile1;
			}

			System.out.println("Debut de la fusion.");
			//On fusionne les deux fichiers en un seul, ligne par ligne.
			for(int i=0; i<maxSize; i++) {

				//Si on a pas atteint la fin du fichier le plus court
				if(i<contentShortFile.size()) {

					//Si les deux fichiers ont la même valeur sur cette ligne
					if(contentLongFile.get(i).equals(contentShortFile.get(i))){

						//On ajoute simplement cette valeur commune au fichier final.
						contentMergedFile.add(contentLongFile.get(i));

					//Sinon
					}else {

						//Si la valeur du fichier long est identique a l'original
						if(contentLongFile.get(i).equals(contentOriginalFile.get(i))){

							//On remplace par celle du fichier court
							contentMergedFile.add(contentShortFile.get(i));

						//Si la valeur du fichier court est identique a l'original
						}else if(contentShortFile.get(i).equals(contentOriginalFile.get(i))) {

							//On remplace par celle du fichier long
							contentMergedFile.add(contentLongFile.get(i));

						//Sinon, alors aucun des deux fichiers n'est identique a l'original
						}else {

							//Choix arbitraire : le fichier long ecrase le fichier court.
							contentMergedFile.add(contentLongFile.get(i));
						}
					}
				//On a fini de lire le fichier court, on ajoute donc les lignes restantes dans le fichier long.
				}else {
					contentMergedFile.add(contentLongFile.get(i));
				}
			}
			
			System.out.println("Fin de la fusion !");
			//On ecrit les nouvelles valeurs dans le fichier de sortie.
			writeMergedFile(contentMergedFile);
			
			System.out.println("Fin du processus de 3-way merge !");
		}else {
			System.out.println("Erreur : les fichiers A et B sont vides.");
		}


	}


	/**
	 * Renvoie une ArrayList de String representant les lignes
	 * d'un document texte, pour lequel on connait le chemin d'acces.
	 */
	public static ArrayList<String> getLinesOfFile(String filePath) {
		ArrayList<String> lines = new ArrayList<>();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));

			String line;
			while ((line=bufferedReader.readLine()) != null) {
				lines.add(line);
			}

		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			try {
				bufferedReader.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

		return lines;
	}

	/**
	 * Renvoie la taille maximale entre deux ArrayList.
	 * Renvoie 0 si les deux ArrayList ont la valeur null;
	 * 
	 */
	public static int getMaxSize(ArrayList<String> contentA, ArrayList<String> contentB) {
		int maxSize=0;
		if(contentA!=null && contentB!=null) {
			maxSize=contentA.size();
			if(contentB.size()>maxSize) {
				maxSize=contentB.size();
			}
		}
		return maxSize;
	}

	/**
	 * Ecrit le fichier final, ligne par ligne,
	 * a partir de l'ArrayList en parametre.
	 */
	public static void writeMergedFile(ArrayList<String> mergedFileContent) {
		int mergedFileSize = mergedFileContent.size();
		try{
			PrintWriter writer = new PrintWriter("CompareSortie", "UTF-8");

			for(int i=0; i<mergedFileSize; i++) {
				writer.println(mergedFileContent.get(i));
			}
			writer.close();
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
