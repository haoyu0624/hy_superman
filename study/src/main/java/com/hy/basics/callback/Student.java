package com.hy.basics.callback;/**
 * Created by haoy on 2018/3/26.
 */

/**
 * @auther haoy
 * @create 2018/3/26
 */
public class Student {
    private String name;
    /**
     * 心算
     * @param a
     * @param b
     * @return
     */
    public int calcAdd(int a, int b){
        return a + b;
    }
    /**
     * 计算器
     * @param a
     * @param b
     * @return
     */
    public int userCalcultorAdd(int a, int b){
        return new Calculator().add(a,b);
    }

    public void fillBlank(int a, int b){
        int result = userCalcultorAdd(a, b);
//        System.out.println(name +":"+a +"+"+b +"="+result);//心算
        System.out.println(name+"使用计算器" +":"+a +"+"+b +"="+result);//使用计算器
    }

    public void callHelp(int a, int b){
        new SuperCalculator().add(a,b,new DoHomeWork());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void fillBlank(int a, int b, int result) {
        System.out.println(name+"使用超级计算器" +":"+a +"+"+b +"="+result);//使用超级计算器
    }

    class DoHomeWork implements DoJob{

        @Override
        public void fillBlank(int a, int b, int result) {
            System.out.println(name+"使用超级计算器" +":"+a +"+"+b +"="+result);//使用超级计算器
        }
    }
}
