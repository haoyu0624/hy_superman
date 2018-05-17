package com.hy.basics.clone.SerDeep;/**
 * Created by haoy on 2018/4/2.
 */

/**
 * 序列化方式的深度克隆
 * @auther haoy
 * @create 2018/4/2
 */
public class DeepCloneTest {

    public static void main(String[] args) {
        Cat cat = new Cat();
        Food food = new Food();
        cat.setName("小花");
        food.setName("鱼");
        cat.setFood(food);
        Cat cat1 = cat.myClone();
        cat1.setName("小宝");
        cat1.getFood().setName("猫粮");
        System.out.println("cat =======>"+cat.getFood().getName());
        System.out.println("cat1 =======>"+cat1.getFood().getName());
    }
}
