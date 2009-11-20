package com.hirisun.msg.dao.impl;

import org.hibernate.criterion.CriteriaSpecification;

import nak.nsf.dao.support.BaseDaoSupport;
import nak.nsf.pager.PageResult;
import nak.nsf.pager.Pager;

import com.hirisun.msg.constants.Condition;
import com.hirisun.msg.dao.ShortMessageDao;
import com.hirisun.msg.domain.ShortMessage;


public class ShortMessageDaoImpl extends BaseDaoSupport<ShortMessage> implements
		ShortMessageDao {

	public PageResult getShortMessageReceive(String curUserID, int page, int pagesize) {
		PageResult result = new PageResult();
		Pager pager = new Pager();
		pager.setCurrentPage(page);
		pager.setPageSize(pagesize);
		/*
		 * 接收到的ShortMessage包括：
		 * 1.接收人为curUserID的；
		 * 2.类型为MSG_TYPE_SYS_ALL的全部ShortMessage；
		 * */
		String hql = "from ShortMessage where "+
				" type=" + ShortMessage.MSG_TYPE_SYS_ALL +
				" or (receiveuserid like '"+curUserID+";%' or receiveuserid like '%;"+curUserID+"'" +
				" or receiveuserid like '%;"+curUserID+";%' or receiveuserid='"+curUserID+"')"+
				" order by sendtime desc";

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

	public PageResult getShortMessagesDetail(String msgID, int page, int pagesize) {
		PageResult result = new PageResult();
		Pager pager = new Pager();
		pager.setCurrentPage(page);
		pager.setPageSize(pagesize);
		/*
		 * ShortMessage的详情包括：
		 * 1.ID为msgID的ShortMessage；
		 * 2.parent为msgID的ShortMessage；
		 * */
		String hql = "from ShortMessage where" +
				" id='"+msgID+"'" +
				" or parentid ='"+msgID+"'" +
				" order by sendtime desc";
		
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

	public PageResult getShortMessagesSend(String curUserID, int page, int pagesize) {
		PageResult result = new PageResult();
		Pager pager = new Pager();
		pager.setCurrentPage(page);
		pager.setPageSize(pagesize);
		/*
		 * 发送的ShortMessage包括：
		 * 1.发送人为curUserID的；
		 * */
		String hql = "from ShortMessage where senduserid='"+curUserID+"' order by sendtime desc";
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

	/* (non-Javadoc)
	 * @see com.hirisun.msg.dao.ShortMessageDao#getCountReceive(java.lang.String)
	 */
	public int getCountReceive(String curUserID) {
		return ((Long) getSession()
		.createQuery(
				"select count(*) from ShortMessage where" +
				" type=" + ShortMessage.MSG_TYPE_SYS_ALL +
				" or (receiveuserid like '"+curUserID+";%' or receiveuserid like '%;"+curUserID+"'" +
				" or receiveuserid like '%;"+curUserID+";%' or receiveuserid='"+curUserID+"')")
		.uniqueResult()).intValue();
	}

	/* (non-Javadoc)
	 * @see com.hirisun.msg.dao.ShortMessageDao#getCountReceiveShort(java.lang.String)
	 */
	public int getCountReceiveShort(String curUserID) {
		return ((Long) getSession()
				.createQuery(
						"select count(*) from ShortMessage where" +
						" type=" + ShortMessage.MSG_TYPE_SHORT +
						" or (receiveuserid like '"+curUserID+";%' or receiveuserid like '%;"+curUserID+"'" +
						" or receiveuserid like '%;"+curUserID+";%' or receiveuserid='"+curUserID+"')")
				.uniqueResult()).intValue();
	}

	/* (non-Javadoc)
	 * @see com.hirisun.msg.dao.ShortMessageDao#getCountReceiveSys(java.lang.String)
	 */
	public int getCountReceiveSys(String curUserID) {
		return ((Long) getSession()
				.createQuery(
						"select count(*) from ShortMessage where" +
						" type=" + ShortMessage.MSG_TYPE_SYS_ALL +
						" or ( type=" +ShortMessage.MSG_TYPE_SYS_ALONE +
						" and (receiveuserid like '"+curUserID+";%' or receiveuserid like '%;"+curUserID+"'" +
						" or receiveuserid like '%;"+curUserID+";%' or receiveuserid='"+curUserID+"'))")
				.uniqueResult()).intValue();
	}

	/* (non-Javadoc)
	 * @see com.hirisun.msg.dao.ShortMessageDao#getCountSend(java.lang.String)
	 */
	public int getCountSend(String curUserID) {
		return ((Long) getSession()
				.createQuery(
						"select count(*) from ShortMessage where senduserid='"+curUserID+"'")
				.uniqueResult()).intValue();
	}

	/* (non-Javadoc)
	 * @see com.hirisun.msg.dao.ShortMessageDao#getShortMessageReceiveByCondition(java.lang.String, com.hirisun.msg.constants.Condition, int, int)
	 */
	public PageResult getShortMessageReceiveByCondition(String curUserID,
			Condition cond, int page, int pagesize) {
		PageResult result = new PageResult();
		Pager pager = new Pager();
		pager.setCurrentPage(page);
		pager.setPageSize(pagesize);
		/*
		 * 接收到的ShortMessage包括：
		 * 1.接收人为curUserID的；
		 * 2.类型为MSG_TYPE_SYS_ALL的全部ShortMessage；
		 * */
		String conHql = " and 1=1 ";
		if(cond!=null)
		{
			if(cond.getStartDate()!=null && !cond.getStartDate().equals(""))
			{
				conHql += " and sendtime>='"+cond.getStartDate()+" 00:00:00'";
			}
			
			if(cond.getEndDate()!=null && !cond.getEndDate().equals(""))
			{
				conHql += " and sendtime<='"+cond.getEndDate()+" 23:59:59'";
			}
			
			if(cond.getKey()!=null && !cond.getKey().equals(""))
			{
				conHql += " and content like '%"+cond.getKey()+"%'";
			}
		}
		String orderHql = " order by sendtime desc";
		
		String hql = "from ShortMessage where "+
		" (type=" + ShortMessage.MSG_TYPE_SYS_ALL +
		" or (receiveuserid like '"+curUserID+";%' or receiveuserid like '%;"+curUserID+"'" +
		" or receiveuserid like '%;"+curUserID+";%' or receiveuserid='"+curUserID+"'))";
		
		hql += conHql;
		hql += orderHql;
		
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

	/* (non-Javadoc)
	 * @see com.hirisun.msg.dao.ShortMessageDao#getShortMessagesSendByCondition(java.lang.String, com.hirisun.msg.constants.Condition, int, int)
	 */
	public PageResult getShortMessagesSendByCondition(String curUserID,
			Condition cond, int page, int pagesize) {
		PageResult result = new PageResult();
		Pager pager = new Pager();
		pager.setCurrentPage(page);
		pager.setPageSize(pagesize);
		/*
		 * 发送的ShortMessage包括：
		 * 1.发送人为curUserID的；
		 * */
		String conHql = "";
		
		if(cond!=null)
		{
			if(cond.getStartDate()!=null && !cond.getStartDate().equals(""))
			{
				conHql += " and sendtime>='"+cond.getStartDate()+" 00:00:00'";
			}
			
			if(cond.getEndDate()!=null && !cond.getEndDate().equals(""))
			{
				conHql += " and sendtime<='"+cond.getEndDate()+" 23:59:59'";
			}
			
			if(cond.getKey()!=null && !cond.getKey().equals(""))
			{
				conHql += " and content like '%"+cond.getKey()+"%'";
			}
		}
		String orderHql = " order by sendtime desc";
		
		String hql = "from ShortMessage where senduserid='"+curUserID+"'";
		hql += conHql;
		hql += orderHql;
		
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
