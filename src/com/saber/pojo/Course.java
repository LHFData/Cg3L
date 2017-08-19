package com.saber.pojo;

/**
 * Created by Saber on 2017/7/3.
 */
public class Course {//课程
    private String source;
    private String describe;
    private String key;
    private String course_source;//课程的source

    public String getCourse_source() {
        return course_source;
    }

    public void setCourse_source(String course_source) {
        this.course_source = course_source;
    }



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
