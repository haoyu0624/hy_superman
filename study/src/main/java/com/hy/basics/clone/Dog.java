package com.hy.basics.clone;/**
 * Created by haoy on 2018/4/2.
 */

/**
 * @auther haoy
 * @create 2018/4/2
 */
public class Dog implements Cloneable{
    private int number;

    private Address address;

    private String name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    protected Dog clone()  {
        Dog d=null;
        try {
            d = (Dog) super.clone();//浅复制
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        d.address = d.address.clone();//深复制
        return d;
    }
}
