package com.ctmp01.web.util;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;

/**
 * Created by Administrator on 2017/6/26.
 */
public class UUIDUtil {
    /**
     * @return UUID随机数
     */
    public static String getUUID() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }

    public static void main(String[] args) {
        System.out.println(UUIDUtil.getUUID());
    }

}
