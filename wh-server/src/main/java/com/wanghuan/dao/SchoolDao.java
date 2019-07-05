package com.wanghuan.dao;

import com.wanghuan.model.sys.SchoolsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SchoolDao {

	public ArrayList<SchoolsEntity> select(@Param("schoolEntity") SchoolsEntity schoolEntity);

	public void del(@Param("schoolEntity") SchoolsEntity schoolEntity);

	public void update(@Param("schoolEntity") SchoolsEntity schoolEntity);

	public void insert(@Param("schoolEntity") SchoolsEntity schoolEntity);

	/**
	 * 通过登录名拿到用户信息
	 * @return
	 */
	public SchoolsEntity getSchoolEntityBySchoolName(@Param("schoolName") String schoolName);

	/**
	 * 获取user列表
	 * @param schoolName
	 *
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public ArrayList<SchoolsEntity> schoolsList(@Param("schoolName") String schoolName, @Param("pageSize") int pageSize, @Param("start") int start);

	/**
	 * 获取user列表的总量
	 * @param schoolName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer schoolsSize(@Param("schoolName") String schoolName, @Param("pageSize") int pageSize, @Param("start") int start);

	/**
	 * 新建用户信息
	 * @param schoolEntity
	 */
	public void insertSchool(@Param("schoolEntity") SchoolsEntity schoolEntity);

	/**
	 * 更新用户信息
	 * @param schoolEntity
	 */
	public void updateSchool(@Param("schoolEntity") SchoolsEntity schoolEntity);

	/**
	 * 删除用户信息
	 * @param groupId
	 */
	public void deleteSchools(@Param("groupId") List<String> groupId);

	public List<SchoolsEntity> allSchools();
	
}
