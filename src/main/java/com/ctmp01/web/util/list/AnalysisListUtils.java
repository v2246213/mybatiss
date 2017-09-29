package com.ctmp01.web.util.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/21.
 */
public class AnalysisListUtils {

    public static List<String> subList(List<String> list0, int pExcludeIndex) {
        List<String> list1 = new ArrayList<String>(list0.size() - 1);
        for (int i = 0; i < list0.size(); i++) {
            if (i != pExcludeIndex) {
                list1.add(list0.get(i));
            }
        }
        return list1;
    }

    public static List<String> cloneList(List<String> list0) {
        List<String> list1 = new ArrayList<String>(list0.size());
        for (int i = 0; i < list0.size(); i++) {
            list1.add(list0.get(i));
        }
        return list1;
    }

    public static double[] convert(List<Double> listDouble) {
        double[] d = new double[listDouble.size()];
        for (int i = 0; i < listDouble.size(); i++) {
            d[i] = listDouble.get(i);
        }
        return d;
    }

    public static String join(List list0, String connSymbol) {
        if (list0 == null) {
            return "";
        }

        StringBuffer oStringBuffer = new StringBuffer();
        for (int i = 0; i < list0.size(); i++) {
            String s = list0.get(i).toString();
            if (i > 0) {
                oStringBuffer.append(connSymbol);
            }
            oStringBuffer.append(s);
        }
        return oStringBuffer.toString();
    }
}
