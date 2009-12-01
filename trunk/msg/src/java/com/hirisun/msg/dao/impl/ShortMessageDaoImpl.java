package com.hirisun.msg.dao.impl;

import java.util.Date;

import org.hibernate.Query;
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
						" and (receiveuserid like '"+curUserID+";%' or receiveuserid like '%;"+curUserID+"'" +
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

	public String getReplyReceiveuserid(String curUserID, String msgID) {
		String newreceiveuserid = "";
		String oldReceiveuserid = "";
		ShortMessage msg = this.read(msgID);
		
		if(msg!=null)
		{
			oldReceiveuserid = msg.getReceiveuserid();
			//取出原来的receiveuserid+senduserid,然后去掉curUserID
			if(oldReceiveuserid!=null && !oldReceiveuserid.equals(""))
			{
				//=======>注意
				//receiveuserid=""时，receiveuserid.length=1,receiverArray[0]="";
				//receiveuserid=";"时，receiveuserid.length=0;
				oldReceiveuserid += ";"+msg.getSenduserid();
				String receiverArray[] = oldReceiveuserid.split(";");
				for(int i=0; i<receiverArray.length; i++)
				{
					if(!receiverArray[i].equals(curUserID))
					{
						newreceiveuserid += receiverArray[i];
						newreceiveuserid += ";";
					}
				}
				
				if(newreceiveuserid.length()>1 && newreceiveuserid.charAt(newreceiveuserid.length()-1)==';')
				{
					newreceiveuserid = newreceiveuserid.substring(0, newreceiveuserid.length()-1);
				}
			}
		}
		
		return newreceiveuserid;
	}

	public int getReceiveCount(String curUserID, Integer type, Boolean isRead) {
		String hql = "select count(*) from UserMsg um,ShortMessage s" +
					" where um.userid='"+curUserID+"'" +
					" and um.msgid=s.id";
		String condHql = "";
		if(type!=null)
		{
			condHql += " and s.type='"+type+"'";
		}
		
		if(isRead!=null)
		{
			condHql += " and um.isread="+isRead;
		}
		hql += condHql;
		return ((Long) getSession()
				.createQuery(hql)
				.uniqueResult()).intValue();	
	}

	public PageResult getDetailMsgs(String msgID, int page, int pagesize) {
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

	public PageResult getReceiveMsgsByCondition(String curUserID, Integer type,
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
		String consql = " and 1=1 ";
		if(cond!=null)
		{
			if(cond.getStartDate()!=null && !cond.getStartDate().equals(""))
			{
				consql += " and sendtime>='"+cond.getStartDate()+" 00:00:00'";
			}
			
			if(cond.getEndDate()!=null && !cond.getEndDate().equals(""))
			{
				consql += " and sendtime<='"+cond.getEndDate()+" 23:59:59'";
			}
			
			if(cond.getKey()!=null && !cond.getKey().equals(""))
			{
				consql += " and content like '%"+cond.getKey()+"%'";
			}
		}
		if(type!=null)
		{
			consql += " and s.type='"+type+"'";
		}
		
		String ordersql = " order by sendtime desc";
		
		String sql = "select s.id,s.senduserid,s.showsenduserid,s.receiveuserid," +
					"s.sendtime,s.content,s.parentid,s.type,s.reply," +
					"um.isread from sm_UserMsg um, sm_ShortMessage s" +
					" where um.msgid=s.id " +
					" and um.userid='"+curUserID+"'";
		
		sql += consql;
		sql += ordersql;
		
		pager.setQuantityOfData(getSession().createSQLQuery(sql)
				.addEntity(ShortMessage.class)
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list().size());
		
		pager.compute();
		result.setPager(pager);
		result.setList(getSession().createSQLQuery(sql)
				.addEntity(ShortMessage.class)
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.setFirstResult(pager.getDataStart())
				.setMaxResults(pager.getPageSize())
				.list());
		
		return result;
	}

	public PageResult getSendMsgsByCondition(String curUserID, Integer type,
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
		if(type!=null)
		{
			conHql += " and type='"+type+"'";
		}
		
		String orderHql = " order by sendtime desc";
		
		String hql = "from ShortMessage where senduserid='"+curUserID+"'";;
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
	
	public boolean setMsgReadState(String curUserID, String msgID)
	{
		Query q = getSession().createQuery(
				"update UserMsg set isread=true where userid='"+curUserID+"' and msgid='"+msgID+"'");
		q.executeUpdate();
		return true;
	}

	public PageResult getReceiveSysMsgsByCondition(String curUserID,
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
			String consql = " and 1=1 ";
			if(cond!=null)
			{
				if(cond.getStartDate()!=null && !cond.getStartDate().equals(""))
				{
					consql += " and sendtime>='"+cond.getStartDate()+" 00:00:00'";
				}
				
				if(cond.getEndDate()!=null && !cond.getEndDate().equals(""))
				{
					consql += " and sendtime<='"+cond.getEndDate()+" 23:59:59'";
				}
				
				if(cond.getKey()!=null && !cond.getKey().equals(""))
				{
					consql += " and content like '%"+cond.getKey()+"%'";
				}
			}
			consql += " and (s.type='"+ShortMessage.MSG_TYPE_SYS_ALL+"' or s.type='"+ShortMessage.MSG_TYPE_SYS_ALONE+"')";
			
			String ordersql = " order by sendtime desc";
			
			String sql = "select s.id,s.senduserid,s.showsenduserid,s.receiveuserid," +
						"s.sendtime,s.content,s.parentid,s.type,s.reply," +
						"um.isread from sm_UserMsg um, sm_ShortMessage s" +
						" where um.msgid=s.id " +
						" and um.userid='"+curUserID+"'";
			
			sql += consql;
			sql += ordersql;
			
			pager.setQuantityOfData(getSession().createSQLQuery(sql)
					.addEntity(ShortMessage.class)
					.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
					.list().size());
			
			pager.compute();
			result.setPager(pager);
			result.setList(getSession().createSQLQuery(sql)
					.addEntity(ShortMessage.class)
					.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
					.setFirstResult(pager.getDataStart())
					.setMaxResults(pager.getPageSize())
					.list());
			
			return result;
	}

	public PageResult getSendSysMsgsByCondition(String curUserID,
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
		
		conHql += " and (type='"+ShortMessage.MSG_TYPE_SYS_ALL+"' or type='"+ShortMessage.MSG_TYPE_SYS_ALONE+"')";
		
		String orderHql = " order by sendtime desc";
		
		String hql = "from ShortMessage where senduserid='"+curUserID+"'";;
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
