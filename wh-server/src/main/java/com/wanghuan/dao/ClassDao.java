package com.wanghuan.dao;

import com.wanghuan.model.sys.ClassEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ClassDao {

	public ArrayList<ClassEntity> select(@Param("classEntity") ClassEntity classEntity);

	public void del(@Param("classEntity") ClassEntity classEntity);

	public void update(@Param("classEntity") ClassEntity classEntity);

	public void insert(@Param("classEntity") ClassEntity classEntity);

	/**
	 * 通过登录名拿到用户信息
	 * @return
	 */
	public ClassEntity getClassEntityByCourseName(@Param("courseName") String courseName);

	/**
	 * 获取user列表
	 * @param courseName
	 *
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public ArrayList<ClassEntity> classList(@Param("courseName") String courseName, @Param("pageSize") int pageSize, @Param("start") int start);

	/**
	 * 获取user列表的总量
	 * @param courseName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer classSize(@Param("courseName") String courseName, @Param("pageSize") int pageSize, @Param("start") int start);

	/**
	 * 新建用户信息
	 * @param classEntity
	 */
	public void insertClass(@Param("classEntity") ClassEntity classEntity);

	/**
	 * 更新用户信息
	 * @param classEntity
	 */
	public void updateClass(@Param("classEntity") ClassEntity classEntity);

	/**
	 * 删除用户信息
	 * @param groupId
	 */
	public void deleteClass(@Param("groupId") List<String> groupId);

	public List<ClassEntity> allClass();
	
}
