package com.hy.basics.clone.SerDeep;/**
 * Created by haoy on 2018/4/2.
 */

import java.io.Serializable;

/**
 * @auther haoy
 * @create 2018/4/2
 */
public class Food implements Serializable {

    private static final long serialVersionUID = 4602438917571990906L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
