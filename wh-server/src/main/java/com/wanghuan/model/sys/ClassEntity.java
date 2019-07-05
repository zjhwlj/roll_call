package com.wanghuan.model.sys;

public class ClassEntity {

    private int id;
    private String courseName;
    /**
     * 学校名称
     */
    private String courseId;
    /**
     * 登录名
     */
    private String teacherName;
    private String classWeek;
    private String firstClasstime;
    private String classNum;
    private String credit;
    private String classroom;
    /**
     * 密码
     */
    //private String password;
    /**
     * 邮箱
     */
    //private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }


    public String getClassWeek() {
        return classWeek;
    }

    public void setClassWeek(String classWeek) {
        this.classWeek = classWeek;
    }


    public String getFirstClasstime() {
        return firstClasstime;
    }

    public void setFirstClasstime(String firstClasstime) {
        this.firstClasstime = firstClasstime;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }


    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }



//    @Override
//    public String toString() {
//        return "SchoolEntity [id=" + schoolId + ", name=" + schoolName + ", am_class_time=" + amClasstime + ", am_afterclass_time=" + amAfterclasstime
//                + ", pm_class_time=" + pmClasstime + ", pm_afterclass_time=" + pmAfterclasstime + "]";
//    }

}
