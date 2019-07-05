package com.wanghuan.dao;


import com.wanghuan.model.sys.TeacherEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TeacherDao {

	public ArrayList<TeacherEntity> select(@Param("teacherEntity") TeacherEntity teacherEntity);

	public void del(@Param("teacherEntity") TeacherEntity teacherEntity);

	public void update(@Param("teacherEntity") TeacherEntity teacherEntity);

	public void insert(@Param("teacherEntity") TeacherEntity teacherEntity);

	/**
	 * 通过登录名拿到用户信息
	 * @return
	 */
	public TeacherEntity getTeacherEntityByTeacherName(@Param("teacherName") String teacherName);

	/**
	 * 获取user列表
	 * @param teacherName
	 *
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public ArrayList<TeacherEntity> teacherList(@Param("teacherName") String teacherName, @Param("pageSize") int pageSize, @Param("start") int start);

	/**
	 * 获取user列表的总量
	 * @param teacherName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer teacherSize(@Param("teacherName") String teacherName, @Param("pageSize") int pageSize, @Param("start") int start);

	/**
	 * 新建用户信息
	 * @param teacherEntity
	 */
	public void insertTeacher(@Param("teacherEntity") TeacherEntity teacherEntity);

	/**
	 * 更新用户信息
	 * @param teacherEntity
	 */
	public void updateTeacher(@Param("teacherEntity") TeacherEntity teacherEntity);

	/**
	 * 删除用户信息
	 * @param groupId
	 */
	public void deleteTeacher(@Param("groupId") List<String> groupId);

	public List<TeacherEntity> allTeacher();
	
}
