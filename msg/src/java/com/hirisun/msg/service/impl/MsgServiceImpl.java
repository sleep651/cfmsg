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
import com.hirisun.msg.domain.Admin;
import com.hirisun.msg.domain.ShortMessage;
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
	
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public void setShortMessageDao(ShortMessageDao shortMessageDao) {
		this.shortMessageDao = shortMessageDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
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
		shortMessageDao.save(msg);
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
}
