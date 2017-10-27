package ca.udes.tp.igl601;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ThreeWayMerge {

	private static final String compareA = "sourceFiles\\CompareA";
	private static final String compareB = "sourceFiles\\CompareB";
	private static final String compareOriginal = "sourceFiles\\CompareOriginal";
	private static final String compareSortie = "sourceFiles\\CompareSortie.txt";
	
	public static boolean equals3(String a, String b, String original) {
		boolean eq = false;
		
		if(a == null || b == null || original == null) {
			eq = false;
		} else if(a.equals(b) && a.equals(original)) {
			eq = true;
		} else {
			eq = false;
		}
		
		return eq;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Creation of BufferedReaders, FileReaders, BufferedWriter and FileWriter to read and write files
		BufferedReader brCompareA = null;
		FileReader frCompareA = null;
		
		BufferedReader brCompareB = null;
		FileReader frCompareB = null;
		
		BufferedReader brCompareOriginal = null;
		FileReader frCompareOriginal = null;
		
		BufferedWriter bwCompareSortie = null;
		FileWriter fwCompareSortie = null;
		
		try {
			// Instanciate BufferedReaders, FileReaders, BufferedWriter and FileWriter to read and write files
			frCompareA = new FileReader(compareA);
			brCompareA = new BufferedReader(frCompareA);
			
			frCompareB = new FileReader(compareB);
			brCompareB = new BufferedReader(frCompareB);
			
			frCompareOriginal = new FileReader(compareOriginal);
			brCompareOriginal = new BufferedReader(frCompareOriginal);
			
			fwCompareSortie = new FileWriter(compareSortie);
			bwCompareSortie = new BufferedWriter(fwCompareSortie);
			
			// Creation of the Strings corresponding to each line of the files
			String lineCompareA = "";
			String lineCompareB = "";
			String lineCompareOrignal = "";
			
			while(((lineCompareOrignal = brCompareOriginal.readLine()) != null)
					|| ((lineCompareA = brCompareA.readLine()) != null)
					|| ((lineCompareB = brCompareB.readLine()) != null)) {		// as long as the end of each file hasn't been reached
				
				lineCompareA = brCompareA.readLine();
				lineCompareB = brCompareB.readLine();
				
				System.out.println("fileO "+lineCompareOrignal+" fileA "+lineCompareA+" fileB "+lineCompareB);

				if(equals3(lineCompareA, lineCompareB, lineCompareOrignal)) {	// if this line is the same in all 3 files, or one of the lines is null
					bwCompareSortie.write(lineCompareOrignal);					// we keep the original line
				} else if((lineCompareA == null) || (lineCompareOrignal.equals(lineCompareA) && !lineCompareOrignal.equals(lineCompareB))) {	// if this line is the same in file A and Original but not in B
					bwCompareSortie.write(lineCompareB);						// we keep the line that has changed (here line from file B)
				} else if((lineCompareB == null) || (!lineCompareOrignal.equals(lineCompareA) && lineCompareOrignal.equals(lineCompareB))) {	// if this line is the same in file B and Original but not in A
					bwCompareSortie.write(lineCompareA);						// we keep the line that has changed (here line from file A)
				} else if(!lineCompareA.equals(lineCompareB)) {					// if this line is different in files A and B, and different from the original
					bwCompareSortie.write(lineCompareB);						// we choose that file B overwrites the others
				}
				
				bwCompareSortie.newLine();		// we go back to the line after reading and writing each line
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			ArrayList<BufferedReader> brArray = new ArrayList<>();
			ArrayList<FileReader> frArray = new ArrayList<>();
			
			brArray.add(brCompareOriginal);
			brArray.add(brCompareA);
			brArray.add(brCompareB);
			
			frArray.add(frCompareOriginal);
			frArray.add(frCompareA);
			frArray.add(frCompareB);
			
			try {
				
				for(BufferedReader br : brArray) {
					br.close();
				}
				
				for(FileReader fr : frArray) {
					fr.close();
				}
				
				bwCompareSortie.close();
				fwCompareSortie.close();
				
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
