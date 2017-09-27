package com.ctmp01.web.util;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/6/26.
 */
public class StreamUtils {
    /**
     * 获取 Stream 中第一个元素
     *
     * @param collection
     * @return
     */
    public static <E> E findFirst(Collection<E> collection) {
        return findFirst(collection.stream());
    }

    /**
     * 获取 Stream 中第一个元素
     *
     * @param stream
     * @return
     */
    public static <E> E findFirst(Stream<E> stream) {
        return stream.findFirst().get();
    }
}
