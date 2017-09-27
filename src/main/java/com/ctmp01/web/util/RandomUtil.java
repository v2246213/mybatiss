package com.ctmp01.web.util;

import java.util.Random;

/**
 * Created by Lenovo on 2017/8/8.
 */
public class RandomUtil {

    private static final Random RANDOM = new Random();

    private static final char ALPHA[] = {'0','1','2','3','4','5','6','7','8','9'};

    public static String code(int len) {
        StringBuffer code = new StringBuffer();
        for (int i = 0; i < len; i++) {
            code.append(ALPHA[RANDOM.nextInt(ALPHA.length)]);
        }
        return code.toString();
    }

}
