package com.wanghuan.dao;

import com.wanghuan.model.sys.SignEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SignDao {

	public ArrayList<SignEntity> select(@Param("signEntity") SignEntity signEntity);

	public void del(@Param("signEntity") SignEntity signEntity);

	public void update(@Param("signEntity") SignEntity signEntity);

	public void insert(@Param("signEntity") SignEntity signEntity);

	/**
	 * 通过登录名拿到用户信息
	 * @return
	 */
	public SignEntity getSignEntityByName(@Param("name") String name);

	/**
	 * 获取user列表
	 * @param name
	 *
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public ArrayList<SignEntity> signsList(@Param("name") String name, @Param("pageSize") int pageSize, @Param("start") int start);

	/**
	 * 获取user列表的总量
	 * @param name
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer signsSize(@Param("name") String name, @Param("pageSize") int pageSize, @Param("start") int start);

	/**
	 * 新建用户信息
	 * @param signEntity
	 */
	public void insertSign(@Param("signEntity") SignEntity signEntity);

	/**
	 * 更新用户信息
	 * @param signEntity
	 */
	public void updateSign(@Param("signEntity") SignEntity signEntity);

	/**
	 * 删除用户信息
	 * @param groupId
	 */
	public void deleteSigns(@Param("groupId") List<String> groupId);

	public List<SignEntity> allSigns();

	
}
