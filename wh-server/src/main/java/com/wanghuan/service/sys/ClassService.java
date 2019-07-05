package com.wanghuan.service.sys;



import com.wanghuan.model.sys.ClassEntity;

import java.util.List;

public interface ClassService {
	public void insert(ClassEntity classDao);

	public void del(ClassEntity classDao);

	/**
	 * 通过登录名得到用户信息
	 * @param schoolName
	 * @return
	 */
	public ClassEntity getClassEntityByCourseName(String courseName);

	/**
	 * 获取user列表
	 * @param schoolName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<ClassEntity> classList(String courseName, int pageSize, int start);

	/**
	 * 获取user列表的总量
	 * @param schoolName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer classSize(String courseName, int pageSize, int start);

	/**
	 * 新建用户信息
	 * @param schoolEntity
	 */
	public void insertClass(ClassEntity classEntity);

	/**
	 * 更新用户信息
	 * @param schoolEntity
	 */
	public void updateClass(ClassEntity classEntity);

	/**
	 * 删除用户信息
	 * @param groupId
	 */
	public void deleteClass(List<String> groupId);


	public List<ClassEntity> allClasss();
}
