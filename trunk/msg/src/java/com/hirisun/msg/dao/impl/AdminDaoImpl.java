package com.hirisun.msg.dao.impl;

import org.hibernate.criterion.CriteriaSpecification;

import nak.nsf.dao.support.BaseDaoSupport;
import nak.nsf.pager.PageResult;
import nak.nsf.pager.Pager;

import com.hirisun.msg.dao.AdminDao;
import com.hirisun.msg.domain.Admin;

public class AdminDaoImpl extends BaseDaoSupport<Admin> implements AdminDao {

	public PageResult getAdmins(int page, int pagesize) {

		PageResult result = new PageResult();
		Pager pager = new Pager();
		pager.setCurrentPage(page);
		pager.setPageSize(pagesize);
		String hql = "from Admin";
		pager.setQuantityOfData(getSession().createQuery(hql)
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list().size());
		
		pager.compute();
		result.setPager(pager);
		result.setList(getSession().createQuery(hql)
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.setFirstResult(pager.getDataStart())
				.setMaxResults(pager.getPageSize())
				.list());
		
		return result;
	}
}
