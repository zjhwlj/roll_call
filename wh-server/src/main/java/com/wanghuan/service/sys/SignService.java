package com.wanghuan.service.sys;

import com.wanghuan.model.sys.SignEntity;

import java.util.List;

public interface SignService {
	public void insert(SignEntity signEntity);

	public void del(SignEntity signEntity);

	/**
	 * 通过登录名得到用户信息
	 * @param name
	 * @return
	 */
	public SignEntity getSignEntityByName(String name);

	/**
	 * 获取user列表
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<SignEntity> signsList(String name, int pageSize, int start);

	/**
	 * 获取user列表的总量
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer signsSize(String name, int pageSize, int start);

	/**
	 * 新建用户信息
	 * @param userEntity
	 */
	public void insertSign(SignEntity signEntity);

	/**
	 * 更新用户信息
	 * @param userEntity
	 */
	public void updateSign(SignEntity signEntity);

	/**
	 * 删除用户信息
	 * @param groupId
	 */
	public void deleteSigns(List<String> groupId);


	public List<SignEntity> allSigns();
}
