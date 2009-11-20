package com.hirisun.msg.dao;

import com.hirisun.msg.domain.User;

import nak.nsf.dao.support.BaseDao;

public interface UserDao extends BaseDao<User>{
	
	/**	
	 *	 获取符合前缀为preStr的，Users的json字符串，当preStr为空时，返回任意匹配
	 *	 返回查询结果的前max条
	 */
	public String getUsersJson(String preStr, int max);
}
