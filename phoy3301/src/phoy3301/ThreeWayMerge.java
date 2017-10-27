package phoy3301;
import java.io.File;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ThreeWayMerge {
	public static void main(String[] args) {
		// Creation du tableau contenant le fichier compareA
		File compA = new File("CompareA"); 		
		String tabA[] = new String[36];
		tabA=fileReader(compA);
		
		// Creation du tableau contenant le fichier compareB
		File compB = new File("CompareB"); 		
		String tabB[] = new String[36];
		tabB=fileReader(compB);
		
		// Creation du tableau contenant le fichier compareOriginal
		File compOri = new File("CompareOriginal"); 	
		String tabOri[] = new String[36];
		tabOri=fileReader(compOri);

		// Ressemblances entre le  tableau A et l'original 
		String sameAOri[]=tabNull36();
		sameAOri=compareTable(tabA, tabOri, "s"); 
		
		// Ressemblances entre le tableau B et l'original 
		String sameBOri[] = tabNull36();
		sameBOri=compareTable(tabB, tabOri,"s"); 
		
		// Differences entre le tableau A  et l'original 
		String diffAOri[] = tabNull36();
		diffAOri=compareTable(tabA, tabOri,"d"); 
		
		// Differences entre le tableau B  et l'original 
		String diffBOri[] = tabNull36();
		diffBOri=compareTable(tabB, tabOri,"d"); 
						
		// Ressemblances entre sameAOri et sameBOri  
		String ssame[] = tabNull36();
		ssame=compareTable(sameBOri, sameAOri,"s2");  
		
		// Ressemblances entre diffAOri et diffBOri 
		String sdiff[] = tabNull36();
		sdiff=compareTable(diffAOri, diffBOri,"s2"); 
		
		// Differences entre diffAOri et diffBOri = Differences entre sameAOri et sameBOri 
		String diff[] = tabNull36();
		diff=compareTable(diffAOri, diffBOri,"d2");
		
		// Fusion de ssame et ddiff
		String fusion[] =tabNull36();
		fusion=merging(ssame, diff, sdiff);
		displayTable(fusion);
		System.out.println("Fusion effectuée");
		
		File file = new File("CompareSortie"); 
	    try {
			PrintWriter output = new PrintWriter(file);
			for (int i=0;i<=35;i++) {
				output.println(fusion[i]);		
			}			
			output.close();
		} catch (IOException e) {
			System.out.printf("ERROR %s\n", e);
		}
	}
	
/////////////////////////////////////////////////////////////////////////METHODES///////////////////////////////////////////////////////////////////

	
	static String[]  fileReader(File f) {
		String tab[]=tabNull36();
		tab=fillTable(tab, f);
		return tab;
	}
	
	static String[] tabNull36 () {
		String[] tab=null;
		tab= new String[36];
		return tab;
	}
		
	static String[] fillTable (String[] tab, File f) {
		int i = 0;
		try {
			FileInputStream fi = new FileInputStream(f);
	        Scanner scanner = new Scanner(fi);
            while(scanner.hasNextLine() ){
                	tab[i]=scanner.nextLine();
            	i=i+1;
            }          
            scanner.close();           
		}
        catch(IOException e) {
			System.out.printf("ERROR %s\n", e);
		}		
        return tab;
	}

	static void displayTable (String[] tab) {
		for (int i=0; i<=35;i++) {
			System.out.println(tab[i]);
		}
	}
	
	static String[] compareTable (String[] tab1, String[] tab2, String string) {
		String tabResult[] = null;
		tabResult=new String[36];
		
		for (int i=0;i<=35;i++) {
			if (tab1[i]==null)
				tab1[i]=" ";
			if (tab2[i]==null)
				tab2[i]=" ";
			
			if (tab1[i].equals(tab2[i]) && string.equals("s") )
				tabResult[i] = tab1[i];		
			if (!tab1[i].equals(tab2[i]) && string.equals("d") ) 
				tabResult[i] = tab1[i];	
			
			if (tab1[i].equals(tab2[i]) && string.equals("s2")) {
				if (tab2[i] != " ")
					tabResult[i] = tab2[i];
				else if (tab1[i] != " ")
					tabResult[i] = tab1[i];
			}			
			if (!tab1[i].equals(tab2[i]) && string.equals("d2")) {
				if (tab2[i] != " ")
					tabResult[i] = tab2[i];
				else if (tab1[i] != " ")
					tabResult[i] = tab1[i];
			}			
		}
		return tabResult;
	}
	
	static String[] merging (String[] tab1, String[] tab2, String[] tab3) {
		String result[] = tabNull36();
		for (int i=0;i<=35;i++) {
			if (tab1[i]==null)
				tab1[i]=" ";
			if (tab2[i]==null)
				tab2[i]=" ";
			if (tab3[i]==null)
				tab3[i]=" ";
			
			if (!tab3[i].equals(" "))
				System.out.println("Erreur de conflit detecte");
			
			if (tab1[i].equals(" ") || tab2[i].equals(null) )
				result[i]=tab2[i];
			else if (tab2[i].equals(" ") || tab2[i].equals(null) )
				result[i]=tab1[i];
		}		
		return result;		
	}
}
	


