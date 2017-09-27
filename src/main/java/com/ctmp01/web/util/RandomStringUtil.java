package com.ctmp01.web.util;

import java.util.Random;
import java.util.UUID;

/**
 * Created by Administrator on 2017/6/26.
 */
public class RandomStringUtil {
    /**
     * 生成 0 至指定位数范围内的随机数
     *
     * @param range
     *            随机数范围上限
     * @return 生成String类型随机数
     */
    public static long random(int range) {
        return new Random().nextInt(range);
    }

    /**
     * 生成指定长度的数字串
     *
     * @param
     * @return
     */
    public static String randomLen(int len) {
        long range = 1;
        for (int i = 0; i < len - 1; i++) {
            range = range * 10;
        }
        String ran = String.valueOf((long) ((Math.random() * 9 + 1) * range));
        return ran;
    }

    /**
     * 生成用户登录令牌，不重复
     *
     * @return 生成唯一的uuid字符串
     */
    public static String token() {
        return UUID.fromString(
                UUID.nameUUIDFromBytes(UUID.randomUUID().toString().getBytes())
                        .toString()).toString();
    }

    public static String captcha() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }
    /**
     * 随机生成数字
     * @param  randomSome 随机数个数
     * @return
     */
    public static String randomStr(int  randomSome) {
        if ( randomSome == 1) {
            return  new Random().nextInt(10)+"";
        }
        int rangeNumber = 1;
        for (int i = 0; i < ( randomSome-1); i++) {
            rangeNumber = rangeNumber * 10;
        }
        return String.valueOf((int) ((Math.random() * ((1 << 3) + 1) + 1) * rangeNumber));
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(captcha());
        }
        System.out.println(5 << 3);
        System.out.println(random(4));
        System.out.println(token());
    }
}
