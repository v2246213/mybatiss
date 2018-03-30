package com.ctmp01.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Lenovo on 2017/8/8.
 */
public class RandomUtil {

    private static final Random RANDOM = new Random();

    private static final char ALPHA[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static String code(int len) {
        StringBuffer code = new StringBuffer();
        for (int i = 0; i < len; i++) {
            code.append(ALPHA[RANDOM.nextInt(ALPHA.length)]);
        }
        return code.toString();
    }

    /**
     * 生成随机文件名：当前年月日时分秒+五位随机数   *   * @return
     */
    public static String getRandomFileName() {

        SimpleDateFormat simpleDateFormat;

        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        Random random = new Random();

        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

        return rannum + str;// 当前时间
        //
    }

    public static void main(String[] args) {

        String fileName = RandomUtil.getRandomFileName();

        System.out.println(fileName);//8835920140307
    }
}
