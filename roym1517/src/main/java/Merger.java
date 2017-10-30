import java.io.*;

/**
 * @author roym1517
 */
public class Merger {

    // List of file to load
    private static File fileA = new File("CompareA");
    private static File fileB = new File("CompareB");
    private static File fileOrig = new File("CompareOriginal");

    private static File fileMerged = new File("CompareSortie");

    public static void main(String[] args) {

        System.out.println("Opening files");
        try (
                BufferedReader readerA = new BufferedReader(new FileReader(fileA));
                BufferedReader readerB = new BufferedReader(new FileReader(fileB));
                BufferedReader readerOrig = new BufferedReader(new FileReader(fileOrig));
                BufferedWriter writerMerged = new BufferedWriter(new FileWriter(fileMerged))
        ) {
            System.out.println("Files open");
            boolean filesFinished = false;

            boolean fileAFinished = false;
            boolean fileBFinished = false;
            boolean fileOrigFinished = false;

            String currentLineA = "";
            String currentLineB = "";
            String currentLineOrig = "";

            String currentLineMerged;

            Integer numberMergedLines = 0;

            do {
                // ****************************************************************************************************

                // Ignore finished files
                if (!fileAFinished) {
                    currentLineA = readerA.readLine();

                    // If line is null (EOF), false
                    fileAFinished = (currentLineA == null);
                }

                if (!fileBFinished) {
                    currentLineB = readerB.readLine();

                    // If line is null (EOF), false
                    fileBFinished = (currentLineB == null);
                }

                if (!fileOrigFinished) {
                    currentLineOrig = readerOrig.readLine();

                    // If line is null (EOF), false
                    fileOrigFinished = (currentLineOrig == null);
                }

                // ****************************************************************************************************

                if (fileAFinished && fileBFinished && fileOrigFinished) {
                    // All the file have been parsed, quit
                    filesFinished = true;
                } else {
                    // Still some work to do

                    if (!fileAFinished && !fileBFinished && !fileOrigFinished) {
                        // Still 3 file to work on, A B & Orig
                        currentLineMerged = doThreeWayMerge(currentLineA, currentLineB, currentLineOrig);
                    } else if (!fileAFinished && !fileBFinished) {
                        // 2 file to work on, A & B
                        // For the TP, this will never happen
                        currentLineMerged = doTwoWayMerge(currentLineA, currentLineB);
                    } else if (!fileAFinished && !fileOrigFinished) {
                        // 2 file to work on, A & Orig
                        // Orig is always the loser
                        currentLineMerged = currentLineA;
                    } else if (!fileBFinished && !fileOrigFinished) {
                        // 2 file to work on, B & Orig
                        // Orig is always the loser
                        currentLineMerged = currentLineB;
                    } else if (!fileAFinished) {
                        // 1 file to work on, A
                        currentLineMerged = currentLineA;
                    } else if (!fileBFinished) {
                        // 1 file to work on, B
                        currentLineMerged = currentLineB;
                    } else {
                        // 1 file to work on, Orig
                        currentLineMerged = currentLineOrig;
                    }

                    // Write the string in the file
                    writerMerged.write(currentLineMerged);
                    writerMerged.newLine();

                    numberMergedLines++;
                }

            } while (!filesFinished);

            // Flush the file, for good measure
            writerMerged.flush();

            System.out.printf("%d lines has been merged\n", numberMergedLines);

            // This is a try-with-resources, no need to close the reader/writer
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Closing files");
    }

    private static String doThreeWayMerge(String a, String b, String orig) {

        if (a.equals(b) && b.equals(orig)) {
            // "a" "b" & "orig" are all the same, no conflict
            return orig;
        } else if (!a.equals(b) && a.equals(orig)) {
            // "b" is the only one modified, no conflict
            return b;
        } else if (!a.equals(b) && b.equals(orig)) {
            // "a" is the only one modified, no conflict
            return a;
        } else if (a.equals(b) && !a.equals(orig)) {
            // "a" & "b" are modified, but are the same value, no conflict
            return a;
        } else {
            // True conflict, "a" is always the winner, because is the number 1 of the Alphabet
            return a;
        }
    }

    private static String doTwoWayMerge(String a, String b) {
        if (a.equals(b)) {
            // a & b are the same, no conflict
            return a;
        } else {
            // True conflict, a is always the winner, because is the number 1 of the Alphabet
            return a;
        }
    }

}
