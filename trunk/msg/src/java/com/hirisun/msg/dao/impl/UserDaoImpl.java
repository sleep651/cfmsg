package com.hirisun.msg.dao.impl;

import java.util.List;
import org.hibernate.criterion.CriteriaSpecification;
import nak.nsf.dao.support.BaseDaoSupport;
import com.hirisun.msg.dao.UserDao;
import com.hirisun.msg.domain.User;

public class UserDaoImpl extends BaseDaoSupport<User> implements UserDao {

	public String getUsersJson(String preStr, int max) {

		String jsonStr = "";
		String condHql = "";
		preStr = preStr.trim();
		if(preStr!=null && !preStr.equals(""))
		{
			condHql += " where lower(userid) like ('"+preStr+"%')";
		}

		String orderHql = " order by userid";
		
		String hql = "from User ";
		hql += condHql;
		hql += orderHql;
		
		List<User> list = getSession().createQuery(hql)
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.setMaxResults(max)
				.list();
		
		if(list==null || list.size()==0)
		{
			jsonStr += "[]";
		}
		else
		{
			jsonStr += "[";
			for(int i=0; i<list.size(); i++)
			{
				User user = (User) list.get(i);
				jsonStr += "'"+user.getUserid()+"'";
				jsonStr += ",";
			}
			
			//去掉最后一个","
			if(jsonStr.length()>=1 && jsonStr.charAt(jsonStr.length()-1)==',')
			{
				jsonStr = jsonStr.substring(0, jsonStr.length()-1);
			}
			
			jsonStr += "]";
		}
		
		return jsonStr;
	}
}
