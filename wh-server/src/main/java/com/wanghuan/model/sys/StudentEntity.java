package com.wanghuan.model.sys;

public class StudentEntity {

    private int id;
    private String studentId;
    private String studentName;
    private String schoolName;
    private String major;

    //private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }


    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName11111) {
        this.schoolName = schoolName11111;
    }


    public String getMajor() {
        return major;
    }

    public void setmMjor(String major) {
        this.major = major;
    }






//    @Override
//    public String toString() {
//        return "SchoolEntity [id=" + schoolId + ", name=" + schoolName + ", am_class_time=" + amClasstime + ", am_afterclass_time=" + amAfterclasstime
//                + ", pm_class_time=" + pmClasstime + ", pm_afterclass_time=" + pmAfterclasstime + "]";
//    }

}
