package com.wanghuan.service.sys;


import com.wanghuan.model.sys.SchoolsEntity;

import java.util.List;

public interface SchoolService {
	public void insert(SchoolsEntity schoolDao);

	public void del(SchoolsEntity schoolDao);

	/**
	 * 通过登录名得到用户信息
	 * @param schoolName
	 * @return
	 */
	public SchoolsEntity getSchoolEntityBySchoolName(String schoolName);

	/**
	 * 获取user列表
	 * @param schoolName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<SchoolsEntity> schoolList(String schoolName, int pageSize, int start);

	/**
	 * 获取user列表的总量
	 * @param schoolName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer schoolSize(String schoolName, int pageSize, int start);

	/**
	 * 新建用户信息
	 * @param schoolEntity
	 */
	public void insertSchool(SchoolsEntity schoolEntity);

	/**
	 * 更新用户信息
	 * @param schoolEntity
	 */
	public void updateSchool(SchoolsEntity schoolEntity);

	/**
	 * 删除用户信息
	 * @param groupId
	 */
	public void deleteSchool(List<String> groupId);


	public List<SchoolsEntity> allSchools();
}
