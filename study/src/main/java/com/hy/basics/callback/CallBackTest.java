package com.hy.basics.callback;/**
 * Created by haoy on 2018/3/26.
 */

/**
 * @auther haoy
 * @create 2018/3/26
 */
public class CallBackTest {

    public static void main(String[] args) {
//        Student xm = new Student();
//        xm.setName("小明");
//        xm.fillBlank(1,1);

//        Student xm = new Student();
//        xm.setName("小明");
//        xm.callHelp(11,11);

        Student xm = new Student();
        xm.setName("小明");
        xm.callHelp(11,11);


        Seller lz = new Seller();
        lz.setName("老张");
        lz.callHelp(22,22);
    }
}
