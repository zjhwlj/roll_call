package com.wanghuan.model.sys;

public class SchoolsEntity {

    private int schoolId;
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 登录名
     */
    private String amClasstime;
    private String amAfterclasstime;
    private String pmClasstime;
    private String pmAfterclasstime;
    /**
     * 密码
     */
    //private String password;
    /**
     * 邮箱
     */
    //private String email;

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName11111) {
        this.schoolName = schoolName11111;
    }

    public String getAmClasstime() {
        return amClasstime;
    }

    public void setAmClasstime(String amClasstime155) {
        this.amClasstime = amClasstime155;
    }

    public String getAmAfterclasstime() {
        return amAfterclasstime;
    }

    public void setAmAfterclasstime(String amAfterclasstime78678) {
        this.amAfterclasstime = amAfterclasstime78678;
    }


    public String getPmClasstime() {
        return pmClasstime;
    }

    public void setPmClasstime(String pmClasstime) {
        this.pmClasstime = pmClasstime;
    }


    public String getPmAfterclasstime() {
        return pmAfterclasstime;
    }

    public void setPmAfterclasstime(String pmAfterclasstime) {
        this.pmAfterclasstime = pmAfterclasstime;
    }



    @Override
    public String toString() {
        return "SchoolEntity [id=" + schoolId + ", name=" + schoolName + ", am_class_time=" + amClasstime + ", am_afterclass_time=" + amAfterclasstime
                + ", pm_class_time=" + pmClasstime + ", pm_afterclass_time=" + pmAfterclasstime + "]";
    }

}
