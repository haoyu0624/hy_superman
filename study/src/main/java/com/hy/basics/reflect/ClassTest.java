package com.hy.basics.reflect;
/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/6/5
 * Time: 10:45
 */

import com.hy.basics.clone.Dog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @auther haoy
 * @create 2018/6/5
 */
public class ClassTest {

    public static void main(String[] args) throws Exception {
        User u = new User();
        //方式1:
        Class c1 = User.class;
        //方式2:
        Class c2 = u.getClass();
        //方式3:Class.forName(类的全称);该方法不仅表示了类的类型，还代表了动态加载类。
        Class c3 = Class.forName("com.hy.basics.reflect.User");
        //可以通过类的类型创建该类的实例对象
        User user = (User) c1.newInstance();

        Class c11 = String.class;
        Class c22 = int.class;
        Class c33 = void.class;
        System.out.println(c11.getName());
        System.out.println(c22.getSimpleName());

        String s = "ss";
        printClassInfo(s);

        User a = new User();
        Class c = a.getClass();
        Method method = c.getMethod("add", new Class[]{int.class, int.class});            //也可以 Method method=c.getMethod("add",int.class,int.class);
        //方法的反射操作
        method.invoke(a, 10, 10);
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Class<?> aClass = systemClassLoader.loadClass("com.hy.basics.reflect.User");


        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Object> submit = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
        Object o = submit.get();

        Future<?> submit1 = executorService.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
        Object o1 = submit1.get();
        System.out.println("===================>>"+o1);
        System.out.println(System.currentTimeMillis());

    }
    /**
     * 获取类的所有方法，并打印出来
     *
     * @param object
     */
    public static void printClassInfo(Object object) {
        Class c = object.getClass();
        System.out.println("类的名称：" + c.getName());        /**
         * 一个成员方法就是一个method对象
         * getMethod()所有的 public方法，包括父类继承的 public
         * getDeclaredMethods()获取该类所有的方法，包括private ,但不包括继承的方法。
         */
        Method[] methods = c.getMethods();//获取方法
        //获取所以的方法，包括private ,c.getDeclaredMethods();

        for (int i = 0; i < methods.length; i++) {            //得到方法的返回类型
            Class returnType = methods[i].getReturnType();
            System.out.print(returnType.getName());            //得到方法名：
            System.out.print(methods[i].getName() + "(");

            Class[] parameterTypes = methods[i].getParameterTypes();
            for (Class class1 : parameterTypes) {
                System.out.print(class1.getName() + ",");
            }
            System.out.println(")");
        }
    }

    /**
     * 也可以获取类的成员变量信息
     *
     * @param o
     */
    public static void printFiledInfo(Object o) {
        Class c = o.getClass();        /**
         * getFileds()获取public
         * getDeclaredFields()获取所有
         */
        Field[] fileds = c.getDeclaredFields();
        for (Field f : fileds) {            //获取成员变量的类型
            Class filedType = f.getType();
            System.out.println(filedType.getName() + " " + f.getName());
        }

    }


}
