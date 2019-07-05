package com.wanghuan.dao;


import com.wanghuan.model.sys.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface StudentDao {

	public ArrayList<StudentEntity> select(@Param("studentEntity") StudentEntity studentEntity);

	public void del(@Param("studentEntity") StudentEntity studentEntity);

	public void update(@Param("studentEntity") StudentEntity studentEntity);

	public void insert(@Param("studentEntity") StudentEntity studentEntity);

	/**
	 * 通过登录名拿到用户信息
	 * @return
	 */
	public StudentEntity getStudentEntityByStudentName(@Param("studentName") String studentName);

	/**
	 * 获取user列表
	 * @param studentName
	 *
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public ArrayList<StudentEntity> studentList(@Param("studentName") String studentName, @Param("pageSize") int pageSize, @Param("start") int start);

	/**
	 * 获取user列表的总量
	 * @param studentName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer studentSize(@Param("studentName") String studentName, @Param("pageSize") int pageSize, @Param("start") int start);

	/**
	 * 新建用户信息
	 * @param studentEntity
	 */
	public void insertStudent(@Param("studentEntity") StudentEntity studentEntity);

	/**
	 * 更新用户信息
	 * @param studentEntity
	 */
	public void updateStudent(@Param("studentEntity") StudentEntity studentEntity);

	/**
	 * 删除用户信息
	 * @param groupId
	 */
	public void deleteStudent(@Param("groupId") List<String> groupId);

	public List<StudentEntity> allStudent();
	
}
