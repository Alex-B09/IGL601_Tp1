import util.Util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ThreeWayMerge {

    private final String COMPARE_SORTIE_PATH = "CompareSortie";

    private File compareA;
    private File compareB;
    private File compareOriginal;

    private List<String> linesCompareA;
    private List<String> linesCompareB;
    private List<String> linesCompareOriginal;

    public ThreeWayMerge(File a, File b, File original) {
        compareA=a;
        compareB=b;
        compareOriginal=original;
    }

    public void loadFiles() throws IOException {
        linesCompareA = Files.lines(compareA.toPath(), StandardCharsets.UTF_8).collect(Collectors.toList());
        linesCompareB = Files.lines(compareB.toPath(), StandardCharsets.UTF_8).collect(Collectors.toList());
        linesCompareOriginal = Files.lines(compareOriginal.toPath(), StandardCharsets.UTF_8).collect(Collectors.toList());
    }

    public void merge() throws IOException {
        int maxSize = Util.getSizeOfTheBiggestList(linesCompareA, linesCompareB, linesCompareOriginal);
        List<String> linesCompareSortie = new LinkedList<>();

        for (int i = 0; i < maxSize; i++) {

            String originalLine = "";
            String ALine = "";
            String BLine = "";

            //on vérifie qu'on ne dépasse pas la taille d'un des fichiers
            if(i < linesCompareOriginal.size())
              originalLine = linesCompareOriginal.get(i);

            if(i < linesCompareA.size())
                ALine = linesCompareA.get(i);

            if(i < linesCompareB.size())
                BLine = linesCompareB.get(i);

            linesCompareSortie.add(compare(ALine, BLine, originalLine));
        }

        Path file = Paths.get(COMPARE_SORTIE_PATH);
        Files.write(file, linesCompareSortie,  StandardCharsets.UTF_8);
    }

    private String compare(String a, String b, String o){
        //Si a est différent d'original mais pas vide
        if(!a.isEmpty() && !a.equals(o) && b.equals(o)){
            return a;
        }
        //Si b est différent d'original mais pas vide
        else if(!b.isEmpty() && a.equals(o) && !b.equals(o)){
            return b;
        }
        //Si les deux (a et b) sont différents d'original
        else if(!a.equals(o) && !b.equals(o)){
            return a; //on suppose que CompareA a toujours raison, sinon CONFLIT
        }
        //Si les trois sont pareils
        else {
            return o;
        }
    }
}
