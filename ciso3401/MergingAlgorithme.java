package test2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class MergingAlgorithme {

	BufferedReader _bfo;
	BufferedReader _bfa;
	BufferedReader _bfb;
	BufferedWriter _bfs;
	FileReader     _original;
	FileReader     _readerA;
	FileReader     _readerB;
	FileWriter     _sortie;
	
	
	public MergingAlgorithme (String co, String ca, String cb, String cs){
		try {
			_bfo = new BufferedReader(new FileReader(co));
			_bfa = new BufferedReader(new FileReader(ca));
			_bfb = new BufferedReader(new FileReader(cb));
			try {
			_bfs = new BufferedWriter(new FileWriter(cs));
			} 
			catch (IOException e) {
				
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	@SuppressWarnings("unused")
	public void merging() throws IOException{
		
		String la = null;
		String lb = null;
		String lo = null;
		
		do {
			
			
			la = _bfa.readLine();
			lb = _bfb.readLine();
			lo = _bfo.readLine();
			
			if(lo != null) {
				if(la.equals(lo) && la.equals(lb)) _bfs.write(la + "\n");
				else {
					if(la != null) {
						if(lo.equals(la+ "\n")) {
							if(lb != null) _bfs.write(lb+ "\n");
							else _bfs.write(lo+ "\n");
						}
						else _bfs.write("Conflit à ce niveau" + "\n");
					}
					else {
						if(lb != null) _bfs.write(lb+ "\n");
						else _bfs.write(lo+ "\n");
					}
				}
			}
			
			else {
				if(la != null) _bfs.write(la + "\n");
				else _bfs.write(lb+ "\n");
			}
			
			
			close();
			
			
		}while(la != null || lo != null || lb != null);
		
	}
	
	
	
	
	
	
	
	public  void close (){
		try {
			_bfs.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		
		MergingAlgorithme mergingAlgorithme = new MergingAlgorithme("../compareOriginal", "../CompareA", "../CompareB", "../CompareSortie");
		mergingAlgorithme.merging();
		
	}

}
