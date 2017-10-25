package util;

import java.util.Arrays;
import java.util.List;

public class Util {

    public static int getSizeOfTheBiggestList(List... lists){
        int maxSize = 0;

        for (List list : lists) {
            if(list.size() > maxSize){
                maxSize = list.size();
            }
        }
        return maxSize;
    }
}
