package com.wanghuan.model.sys;

public class TeacherEntity {

    private int id;
    /**
     * 学校名称
     */
    private String teacherName;
    /**
     * 登录名
     */
    private String teacherId;
    private String schoolName;
    private String courseNum;

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


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }


    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName11111) {
        this.schoolName = schoolName11111;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }




//    @Override
//    public String toString() {
//        return "SchoolEntity [id=" + schoolId + ", name=" + schoolName + ", am_class_time=" + amClasstime + ", am_afterclass_time=" + amAfterclasstime
//                + ", pm_class_time=" + pmClasstime + ", pm_afterclass_time=" + pmAfterclasstime + "]";
//    }

}
