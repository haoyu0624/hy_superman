package com.hy.basics.enumt;/**
 * Created by haoy on 2018/2/1.
 */

/**
 * @auther haoy
 * @create 2018/2/1
 */
public class EnumMain {
    public static void main(String[] args) {
        Planet a = Planet.PROXY;
//        System.out.println("=========="+a.ordinal());

        testFound(a);
    }

    private static void testFound(Planet a) {
        System.out.println("testFound========="+a);
    }
}
