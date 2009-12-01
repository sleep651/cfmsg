/**
 * 
 */
package com.hirisun.msg.service.impl;

import nak.nsf.pager.PageResult;

import org.springframework.transaction.annotation.Transactional;

import com.hirisun.msg.constants.Condition;
import com.hirisun.msg.dao.AdminDao;
import com.hirisun.msg.dao.ShortMessageDao;
import com.hirisun.msg.dao.UserDao;
import com.hirisun.msg.dao.UserMsgDao;
import com.hirisun.msg.domain.Admin;
import com.hirisun.msg.domain.ShortMessage;
import com.hirisun.msg.domain.UserMsg;
import com.hirisun.msg.service.MsgService;

/**
 * @author Administrator
 *
 */

/*
 * 	默认的@Transactional如果没有设定参数时，其propagation模式是REQUIRED，
 *  read-only标记是false。isolation level是READ_COMMITTED，这时，
 *  如果你的代码中抛出异常，而且又被你通过try catch捕获的话，
 *  事务是不回滚的。
 */

@Transactional
public class MsgServiceImpl implements MsgService {

	private AdminDao adminDao;
	private ShortMessageDao shortMessageDao;
	private UserDao userDao;
	private UserMsgDao userMsgDao;
	
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public void setShortMessageDao(ShortMessageDao shortMessageDao) {
		this.shortMessageDao = shortMessageDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setUserMsgDao(UserMsgDao userMsgDao) {
		this.userMsgDao = userMsgDao;
	}
	/* (non-Javadoc)
	 * @see com.hirisun.msg.service.MsgService#addAdmin(hlx.bea.wxy.domain.Admin)
	 */
	@Transactional(readOnly = false)
	public boolean addAdmin(Admin admin) {
		boolean ret = true;
		adminDao.save(admin);
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.hirisun.msg.service.MsgService#deleteAdmin(hlx.bea.wxy.domain.Admin)
	 */
	public boolean deleteAdmin(Admin admin) {
		boolean ret = true;
		adminDao.delete(admin);
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.hirisun.msg.service.MsgService#getAllAdmins()
	 */
	public PageResult getAdmins(int page, int pagesize) {
		return adminDao.getAdmins(page, pagesize);
	}

	/* (non-Javadoc)
	 * @see com.hirisun.msg.service.MsgService#getShortMessageReceive()
	 */
	public PageResult getShortMessageReceive(String curUserID, int page, int pagesize) {
		return shortMessageDao.getShortMessageReceive(curUserID, page, pagesize);
	}

	/* (non-Javadoc)
	 * @see com.hirisun.msg.service.MsgService#getShortMessagesDetail()
	 */
	public PageResult getShortMessagesDetail(String msgID, int page, int pagesize) {
		return shortMessageDao.getShortMessagesDetail(msgID, page, pagesize);
	}

	/* (non-Javadoc)
	 * @see com.hirisun.msg.service.MsgService#getShortMessagesSend()
	 */
	public PageResult getShortMessagesSend(String curUserID, int page, int pagesize) {
		return shortMessageDao.getShortMessagesSend(curUserID, page, pagesize);
	}

	/* (non-Javadoc)
	 * @see com.hirisun.msg.service.MsgService#sendMsg()
	 */
	public boolean sendMsg(ShortMessage msg) {
		boolean ret = true;
		//保存ShortMessage
		shortMessageDao.save(msg);
		//保存UserMsg
		String receiveid = msg.getReceiveuserid();
		String[] receiveArray = receiveid.split(";");
		for(int i=0; i<receiveArray.length; i++)
		{
			String userid = receiveArray[i];
			userid = userid.trim();
			if(!userid.equals(""))
			{
				UserMsg userMsg = new UserMsg();
				userMsg.setMsgid(msg.getId());
				userMsg.setUserid(userid);
				userMsg.setIsread(false);
				userMsgDao.save(userMsg);
			}
		}
		return ret;
	}
	
	
	public int getCountReceive(String curUserID) {
		return shortMessageDao.getCountReceive(curUserID);
	}

	public int getCountSend(String curUserID) {
		return shortMessageDao.getCountSend(curUserID);
	}

	public int getCountReceiveShort(String curUserID) {
		return shortMessageDao.getCountReceiveShort(curUserID);
	}

	public int getCountReceiveSys(String curUserID) {
		return shortMessageDao.getCountReceiveSys(curUserID);
	}

	public PageResult getShortMessageReceiveByCondition(String curUserID,
			Condition cond, int page, int pagesize) {
		return shortMessageDao.getShortMessageReceiveByCondition(curUserID, cond, page, pagesize);
	}

	public PageResult getShortMessagesSendByCondition(String curUserID,
			Condition cond, int page, int pagesize) {
		return shortMessageDao.getShortMessagesSendByCondition(curUserID, cond, page, pagesize);
	}
	
	public String getUsersJson(String preStr, int max) {
		return userDao.getUsersJson(preStr, max);
	}

	/* (non-Javadoc)
	 * @see com.hirisun.msg.service.MsgService#getReplyReceiveuserid(java.lang.String, java.lang.String)
	 */
	public String getReplyReceiveuserid(String curUserID, String msgID) {
		return shortMessageDao.getReplyReceiveuserid(curUserID, msgID);
	}

	/* (non-Javadoc)
	 * @see com.hirisun.msg.service.MsgService#getReplyParentid(java.lang.String)
	 */
	public String getMsgParentidByID(String msgID) {
		String parentid = null;
		ShortMessage msg = shortMessageDao.read(msgID);
		if(msg!=null)
		{
			parentid = msg.getParentid();
		}
		
		return parentid;
	}

	public int getReceiveCount(String curUserID, Integer type, Boolean isRead) {
		return shortMessageDao.getReceiveCount(curUserID, type, isRead);
	}

	public PageResult getDetailMsgs(String msgID, int page, int pagesize) {
		return shortMessageDao.getDetailMsgs(msgID, page, pagesize);
	}

	public PageResult getReceiveMsgsByCondition(String curUserID, Integer type,
			Condition cond, int page, int pagesize) {
		return shortMessageDao.getReceiveMsgsByCondition(curUserID, type, cond, page, pagesize);
	}
	
	public PageResult getReceiveSysMsgsByCondition(String curUserID,
			Condition cond, int page, int pagesize) {
		return shortMessageDao.getReceiveSysMsgsByCondition(curUserID, cond, page, pagesize);
	}

	public PageResult getSendMsgsByCondition(String curUserID, Integer type,
			Condition cond, int page, int pagesize) {
		return shortMessageDao.getSendMsgsByCondition(curUserID, type, cond, page, pagesize);
	}

	public boolean setMsgReadState(String curUserID, String msgID) {
		return shortMessageDao.setMsgReadState(curUserID, msgID);
	}

	public PageResult getSendSysMsgsByCondition(String curUserID,
			Condition cond, int page, int pagesize) {
		return shortMessageDao.getSendSysMsgsByCondition(curUserID, cond, page, pagesize);
	}
}
