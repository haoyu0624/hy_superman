package com.hy.basics.clone;/**
 * Created by haoy on 2018/4/2.
 */

/**
 * 实现Cloneable接口的浅度克隆
 * @auther haoy
 * @create 2018/4/2
 */
public class CloneTest {

    public static void main(String[] args) {
        //==============浅复制
//        Dog dog = new Dog();
//        dog.setNumber(147852);
////        Dog dog1 = dog;
//        Dog dog1 = dog.clone();
//        dog1.setNumber(111111);
//        System.out.println("dog====>>"+dog.getNumber());
//        System.out.println("dog1====>>"+dog1.getNumber());

        //==============深复制
        Dog dogDeep = new Dog();
        Address addDe = new Address();
        addDe.setAdd("史各庄");
        dogDeep.setNumber(222222);
        dogDeep.setAddress(addDe);
        Dog dogDeep1 = dogDeep.clone();
        dogDeep1.getAddress().setAdd("定福皇庄");
        dogDeep1.setNumber(333333);
        System.out.println("dogDeep=========>>"+dogDeep.getNumber());
        System.out.println("dogDeep1=========>>"+dogDeep1.getNumber());
        System.out.println("dogDeep=========>>"+dogDeep.getAddress().getAdd());
        System.out.println("dogDeep1=========>>"+dogDeep1.getAddress().getAdd());
    }
}
