package tp1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ThreeWayMerge
{
    private BufferedReader readerComparaisonA, readerComparaisonB, readerComparaisonOriginal;
    private BufferedWriter writer = null;
    private FileWriter fileWriter = null;
    
    /**
     * Constructeur de confort
     */
    public ThreeWayMerge() {
        // Initialisation des variables
        
        try
        {
            readerComparaisonA = new BufferedReader(new FileReader("CompareA"));
            readerComparaisonB = new BufferedReader(new FileReader("CompareB"));
            readerComparaisonOriginal = new BufferedReader(new FileReader("CompareOriginal"));

            fileWriter = new FileWriter("CompareSortie");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        writer = new BufferedWriter(fileWriter);
    }
    
    /**
     * Methode permettant de merge 3 fichiers
     */
    public void merge()
    {      
        String currentLineA = null, currentLineB = null, currentLineOriginal = null;

        try
        {
            // Boucle qui parcourt le fichier original tant qu'il y a de ligne
            do
            {
                currentLineOriginal = readerComparaisonOriginal.readLine();
                currentLineA = readerComparaisonA.readLine();
                currentLineB = readerComparaisonB.readLine();

                // Si fin fichier A et B
                if (currentLineA == null && currentLineB == null)
                {
                    if (currentLineOriginal != null)
                        writer.write(currentLineOriginal + "\n");
                    continue;
                }
                // Si fin fichier A mais pas B
                else if (currentLineA == null && currentLineB != null)
                {
                    writer.write(currentLineB + "\n");
                    continue;
                }
                // Si fin fichier B mais pas A
                else if (currentLineA == null && currentLineB != null)
                {
                    writer.write(currentLineA + "\n");
                    continue;
                }

                try
                {
                    // A = B
                    if (currentLineA.equals(currentLineB))
                    {
                        writer.write(currentLineA + "\n");
                        continue;
                    }
                }
                catch (NullPointerException e)
                {
                }
                try
                {
                    // A != B mais A non nul
                    if (!currentLineA.equals("") && currentLineB.equals(""))
                    {
                        writer.write(currentLineA + "\n");
                        continue;
                    }
                }
                catch (NullPointerException e)
                {
                }
                try
                {
                    // A != B mais B non nul
                    if (currentLineA.equals("") && !currentLineB.equals(""))
                    {
                        writer.write(currentLineB + "\n");
                        continue;
                    }
                }
                catch (NullPointerException e)
                {
                }
                try
                {
                    // A != B Conflit alors on prends par défaut la valeur de A
                    if (!currentLineA.equals(currentLineB))
                    {
                        writer.write(currentLineA + "\n");
                        continue;
                    }
                }
                catch (NullPointerException e)
                {
                }

                try
                {
                    if (currentLineA.equals("") && currentLineB.equals("") && !currentLineOriginal.equals(""))
                        writer.write(currentLineOriginal + "\n");
                }
                catch (NullPointerException e)
                {
                    if (currentLineA == null && currentLineB == null)
                        writer.write(currentLineOriginal + "\n");
                }
            }
            while (currentLineOriginal != null || currentLineA != null || currentLineB != null);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (writer != null)
                    writer.close();
                if (fileWriter != null)
                    fileWriter.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Methode principale
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        ThreeWayMerge mergeObject = new ThreeWayMerge();       
        mergeObject.merge();   
    }
}
