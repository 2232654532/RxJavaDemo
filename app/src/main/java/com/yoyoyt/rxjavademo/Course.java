package com.yoyoyt.rxjavademo;

/**
 * 作者: 刘净辉
 * 日期: 2017/2/13 10:04.
 */

public class Course {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }
}
