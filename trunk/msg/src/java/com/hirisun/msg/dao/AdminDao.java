package com.hirisun.msg.dao;

import com.hirisun.msg.domain.Admin;

import nak.nsf.dao.support.BaseDao;
import nak.nsf.pager.PageResult;

public interface AdminDao extends BaseDao<Admin>{


	/**
	*	序号	函数名（中文）	函数功能	输入参数	输出参数	备注
	*	3	读取管理员列表	获取当前已添加的管理员列表	无	人员信息List		
	*/
	public PageResult getAdmins(int page, int pagesize);
}
