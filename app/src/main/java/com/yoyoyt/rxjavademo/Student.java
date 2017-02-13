package com.yoyoyt.rxjavademo;

/**
 * 作者: 刘净辉
 * 日期: 2017/2/13 09:58.
 */

public class Student {
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "course=" + course +
                '}';
    }
}
