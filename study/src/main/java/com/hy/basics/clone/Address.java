package com.hy.basics.clone;/**
 * Created by haoy on 2018/4/2.
 */

/**
 * @auther haoy
 * @create 2018/4/2
 */
public class Address implements Cloneable{
    private String add;

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    @Override
    protected Address clone() {
        Address add = null;
        try {
            add = (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return add;
    }
}
