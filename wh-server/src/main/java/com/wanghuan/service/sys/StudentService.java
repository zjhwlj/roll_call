package com.wanghuan.service.sys;




import com.wanghuan.model.sys.StudentEntity;

import java.util.List;

public interface StudentService {
	public void insert(StudentEntity studentDao);

	public void del(StudentEntity studentDao);

	/**
	 * 通过登录名得到用户信息
	 * @param studentName
	 * @return
	 */
	public StudentEntity getStudentEntityByStudentName(String studentName);

	/**
	 * 获取user列表
	 * @param studentName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<StudentEntity> studentList(String studentName, int pageSize, int start);

	/**
	 * 获取user列表的总量
	 * @param studentName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer studentSize(String studentName, int pageSize, int start);

	/**
	 * 新建用户信息
	 * @param studentEntity
	 */
	public void insertStudent(StudentEntity studentEntity);

	/**
	 * 更新用户信息
	 * @param studentEntity
	 */
	public void updateStudent(StudentEntity studentEntity);

	/**
	 * 删除用户信息
	 * @param groupId
	 */
	public void deleteStudent(List<String> groupId);


	public List<StudentEntity> allStudent();
}
