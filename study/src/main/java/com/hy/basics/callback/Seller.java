package com.hy.basics.callback;/**
 * Created by haoy on 2018/3/26.
 */

/**
 * @auther haoy
 * @create 2018/3/26
 */
public class Seller {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void callHelp(int a, int b){
        new SuperCalculator().add(a,b,new DoSell());
    }

    class DoSell implements DoJob{

        @Override
        public void fillBlank(int a, int b, int result) {
            System.out.println(name+"使用超级计算器卖菜算数" +":"+a +"+"+b +"="+result);
        }
    }

}
