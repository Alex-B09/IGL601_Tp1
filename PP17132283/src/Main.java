public class Main {

    private static final String fileA = "../CompareA";
    private static final String fileB = "../CompareB";
    private static final String fileOriginal = "../CompareOriginal";
    private static final String fileOutput = "../CompareOutput";

    public static void main(String[] args) {
        MergeFiles mf = new MergeFiles();

        try {
            mf.Merge(fileA,fileB,fileOriginal,fileOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
