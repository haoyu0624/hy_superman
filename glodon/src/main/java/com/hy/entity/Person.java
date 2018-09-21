package com.hy.entity;

/**
 * Created by IntelliJ IDEA.
 * User: haoy
 * Date: 2018/9/3
 * Time: 16:36
 */
public class Person {
    private Integer id;
    private String name;

    private Integer age;

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
