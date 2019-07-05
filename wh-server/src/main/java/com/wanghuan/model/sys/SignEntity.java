package com.wanghuan.model.sys;

/**
 * @author wanghuan
 *
 */
public class SignEntity {
	/**
	 * id
	 */
	private int id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 登录名
	 */
	private String studentId;
	/**
	 * 密码
	 */
	private String courseName;
	/**
	 * 邮箱
	 */
	private String signNum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSignNum() {
		return signNum;
	}

	public void setSignNum(String signNum) {
		this.signNum = signNum;
	}

//	@Override
//	public String toString() {
//		return "SignEntity [id=" + id + ", loginName=" + loginName + ", name=" + name + ", password=" + password
//				+ ", email=" + email + "]";
//	}

}
