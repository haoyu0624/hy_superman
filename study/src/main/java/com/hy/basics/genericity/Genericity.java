package com.hy.basics.genericity;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/6/4
 * Time: 17:53
 */

/**
 * @auther haoy
 * @create 2018/6/4
 */
public class Genericity {
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }

//    public static <T> int countGreaterThan(T[] anArray, T elem) {
//        int count = 0;
//        for (T e : anArray)
//            if (e > elem)  // compiler error
//                ++count;
//        return count;
//    }
}
