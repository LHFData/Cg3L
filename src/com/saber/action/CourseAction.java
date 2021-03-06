package com.saber.action;

import com.opensymphony.xwork2.ActionSupport;
import com.saber.pojo.Course;
import com.saber.dao.CourseManager;

/**
 * Created by Saber on 2017/8/16.
 */
public class CourseAction extends ActionSupport {
    /**
     * 获取课程的action
     */
    private String source;//课程的图片
    private Course course;//课程
    private String course_source;//课程的视频、音频、PPT等链接
    private String course_describe;//课程描述
    private String course_name;//课程名
    //后期还将假如课程到底是视频、音频、PPT的判断

    public String getCourse_describe() {
        return course_describe;
    }

    public void setCourse_describe(String course_describe) {
        this.course_describe = course_describe;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_source() {
        return course_source;
    }

    public void setCourse_source(String course_source) {
        this.course_source = course_source;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String queryCourse() throws Exception{
        CourseManager courseManager=new CourseManager();
        course=courseManager.queryBySource(source);
        course_source=course.getCourse_source();
        course_name=course.getDescribe();
        course_describe=course.getKey();
        return "video";//表示返回的是视频
        //return "music"表示返回的是音频
        //return "ppt"表示返回的是ppt
    }
}
