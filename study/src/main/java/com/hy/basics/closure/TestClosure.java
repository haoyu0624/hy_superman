package com.hy.basics.closure;

public class TestClosure {
    public static void main(String[] args) {
        Food food = new Food();

        food.getEat().eat();
        food.getEat().eat();

        //在内部类是public修饰时，可以通过以下方式
        Food foodPub = new Food();
        Food.EatActive eat = foodPub.new EatActive();
        eat.eat();
    }

}

class Food{
    public static final String name = "Food";
    private static int num = 20;

    public Food() {
        System.out.println("Delicious Food");
    }

    public Active getEat() {
        return new EatActive();
    }

    class EatActive implements Active {

        @Override
        public void eat() {
            if (num == 0) {
                System.out.println("吃货，已经吃没了");
            }
            num --;
            System.out.println("吃货，你吃了一份了");
        }
    }

    public void currentNum() {
        System.out.println("还剩:"+num+"份");
    }
}

interface Active{

    void eat();
}