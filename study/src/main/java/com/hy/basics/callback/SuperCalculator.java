package com.hy.basics.callback;/**
 * Created by haoy on 2018/3/26.
 */

/**
 * @auther haoy
 * @create 2018/3/26
 */
public class SuperCalculator {
    public void add(int a, int b,DoJob dojob){
        int result = a+b;
        dojob.fillBlank(a,b,result);
    }
}
