import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Algo_3wm {

	public void compare(String file1, String file2, String file3, File fichierText) throws IOException {
		//Definition d'une variable pour definir une ligne vide dans un des fichiers
		String vide = "";

		try {

			//Declaration du fichier de sortie
			FileWriter CompareSortie = new FileWriter(fichierText);

			Scanner scanner1 = new Scanner(new File(file1));
			Scanner scanner2 = new Scanner(new File(file2));
			Scanner scanner3 = new Scanner(new File(file3));

			while (scanner1.hasNextLine() || scanner2.hasNextLine() || scanner3.hasNextLine()) {

				String line2 = scanner2.nextLine();
				String line3 = scanner3.nextLine();

				if (scanner1.hasNextLine()) {
					String line1 = scanner1.nextLine();

					if (line2.equals(line1) && line1.equals(line3)) {
						CompareSortie.write(line1);
					} 
					
					else if (line2.equals(vide) && line3.equals(vide)) {
						CompareSortie.write(line1);
					} 
					
					else if (line1.equals(vide) && line3.equals(vide)) {
						CompareSortie.write(line2);
					}

				} 
				
				else if (scanner1.hasNextLine() == false) {
					if (line2.equals(line3)) {
						CompareSortie.write(line2);
					}

					else if (line3.equals(vide)) {
						CompareSortie.write(line2);
					}

					else if (line2.equals(vide)) {
						CompareSortie.write(line3);
					}

				}
			}

			scanner1.close();
			scanner2.close();
			scanner3.close();
			CompareSortie.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

		Algo_3wm algo = new Algo_3wm();

		String filePath1 = "C:\\Users\\Théolise\\Desktop\\USherbrooke\\TP1_IGL601\\IGL601_Tp1\\CompareA";
		String filePath2 = "C:\\Users\\Théolise\\Desktop\\USherbrooke\\TP1_IGL601\\IGL601_Tp1\\CompareB";
		String filePath3 = "C:\\Users\\Théolise\\Desktop\\USherbrooke\\TP1_IGL601\\IGL601_Tp1\\CompareOriginal";

		File fichierTexte = new File("C:\\Users\\Théolise\\Desktop\\USherbrooke\\CompareSortie");

		algo.compare(filePath1, filePath2, filePath3, fichierTexte);

	}
}
