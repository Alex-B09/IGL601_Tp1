package algomerge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class algo {

	public static void main(String[] args) throws IOException {
		ThreeWayMerge();
	}
	
	private static void ThreeWayMerge() throws IOException {
		//Declaration des variables de classes
		File compareOriginal;
		File compareA;
		File compareB;
		String lineOriginal = null;
		String lineA = null;
		String lineB = null;

		//Creation des fichiers en memoires
		compareOriginal = new File("./CompareOriginal");
		compareA = new File("./CompareA");
		compareB = new File("./CompareB");
		
		//Instanciations des lecteurs et de l'editeur de fichier
		PrintWriter  writer = new PrintWriter(new BufferedWriter(new FileWriter("CompareSortie")));
		BufferedReader readerFileA = new BufferedReader( new FileReader(compareA));
		BufferedReader readerFileB = new BufferedReader( new FileReader(compareB));
		BufferedReader readerFileOriginal = new BufferedReader( new FileReader(compareOriginal));
		
		//Execution de l'algo tant que le fichier A ou B contient des lignes
		while ((lineA = readerFileA.readLine()) != null | (lineB = readerFileB.readLine()) != null) {
			lineOriginal = readerFileOriginal.readLine();
			
			//Cas de base, les deux fichiers sont identiques
			if(lineA != null && lineB != null && lineA.equals(lineB)) {
				writer.println(lineA);
			}
			else {
				//Le fichier A a ete modifie, on prend donc celui-ci
				if(lineA != null && lineOriginal != null && !lineA.equals(lineOriginal)) {
					writer.println(lineA);
				}
				//Le fichier B a ete modifie, on prend donc celui-ci
				else if(lineB != null && lineOriginal != null && !lineB.equals(lineOriginal)) {
					writer.println(lineB);
				}
				//Les deux fichiers ont ete modifies. On considere celui dont la date de modification est la plus récente
				else {
					if(lineA != null && compareA.lastModified() > compareB.lastModified()) {
						writer.println(lineA);
					}
					else if (lineB != null && compareA.lastModified() <= compareB.lastModified()){
						writer.println(lineB);
					}
				}
			}
		}
		//Fermeture des buffers
		writer.close();
		readerFileA.close();
		readerFileB.close();
		readerFileOriginal.close();
	}
}
