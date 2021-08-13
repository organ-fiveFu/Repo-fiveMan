package com.vblessings.nhs.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;


public class ListUtil {

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 集合分片
     * @param list
     * @param length
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list, int length){
        if (list.isEmpty() || list == null || length < 1) {
            return Collections.emptyList();
        }
        //result 最终分片后结果
        List<List<T>> result = new ArrayList<List<T>>();
        int size = list.size();
        //count分片大小
        int count = (size + length -1) / length;
        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i*length, (i+1)*length > size ? size : (i+1)*length);
            result.add(subList);
        }
        return result;
    }

}
