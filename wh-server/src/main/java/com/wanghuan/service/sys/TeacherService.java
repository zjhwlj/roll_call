package com.wanghuan.service.sys;





import com.wanghuan.model.sys.TeacherEntity;

import java.util.List;

public interface TeacherService {
	public void insert(TeacherEntity teacherDao);

	public void del(TeacherEntity teacherDao);

	/**
	 * 通过登录名得到用户信息
	 * @param teacherName
	 * @return
	 */
	public TeacherEntity getTeacherEntityByTeacherName(String teacherName);

	/**
	 * 获取user列表
	 * @param teacherName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<TeacherEntity> teacherList(String teacherName, int pageSize, int start);

	/**
	 * 获取user列表的总量
	 * @param teacherName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer teacherSize(String teacherName, int pageSize, int start);

	/**
	 * 新建用户信息
	 * @param teacherEntity
	 */
	public void insertTeacher(TeacherEntity teacherEntity);

	/**
	 * 更新用户信息
	 * @param teacherEntity
	 */
	public void updateTeacher(TeacherEntity teacherEntity);

	/**
	 * 删除用户信息
	 * @param groupId
	 */
	public void deleteTeacher(List<String> groupId);


	public List<TeacherEntity> allTeacher();
}
