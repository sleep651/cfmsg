package com.hirisun.msg.dao;


import java.util.List;

import com.hirisun.msg.constants.Condition;
import com.hirisun.msg.domain.ShortMessage;

import nak.nsf.dao.support.BaseDao;
import nak.nsf.pager.PageResult;

public interface ShortMessageDao extends BaseDao<ShortMessage> {

	/**
	*	序号	函数名（中文）	函数功能	输入参数	输出参数	备注
	*	1	读取已发送短消息	获得发送人为指定userID对应所有短消息	userID	短消息List	
	*/
	public PageResult getShortMessagesSend(String curUserID, int page, int pagesize);
	
	/**
	*	序号	函数名（中文）	函数功能	输入参数	输出参数	备注
	*	2	读取短消息详情	获取指定短消息对应的消息序列	messageID	短消息List	
	*/
	public PageResult getShortMessagesDetail(String msgID, int page, int pagesize);

	/**
	序号	函数名（中文）	函数功能	输入参数	输出参数	备注
	1	读取已接收的短消息	获得接收人为指定userID对应所有短消息，以及所有接收人为“全体”的系统消息	userID	短消息List	
	*/
	public PageResult getShortMessageReceive(String curUserID, int page, int pagesize);

	
	/**	
	 *	 获得接收条数
	 */
	public int getCountReceive(String curUserID);
	
	/**	
	 *	 获得接收的系统消息条数
	 */
	public int getCountReceiveSys(String curUserID);
	
	/**	
	 *	 获得接收的短消息条数
	 */
	@Deprecated
	public int getCountReceiveShort(String curUserID);
	
	/**	
	 *	 获得接收的短消息条数
	 */
	public int getReceiveCount(String curUserID, Integer type, Boolean isRead);
	
	/**	
	 *	 获得发送条数
	 */
	public int getCountSend(String curUserID);
	
	/**	
	 *	 根据条件查询发件箱信息
	 */
	public PageResult getShortMessagesSendByCondition(String curUserID, Condition cond, int page, int pagesize);
	
	/**	
	 *	 根据条件查询收件箱信息
	 */
	public PageResult getShortMessageReceiveByCondition(String curUserID, Condition cond, int page, int pagesize);
	
	
	/**	
	 *	 获取当前回复消息应该填入的收件人
	 */
	public String getReplyReceiveuserid(String curUserID, String msgID);
	
	/**
	*	序号	函数名（中文）	函数功能	输入参数	输出参数	备注
	*	2	读取短消息详情	获取指定短消息对应的消息序列	messageID	短消息List	
	*/
	public PageResult getDetailMsgs(String msgID, int page, int pagesize);

	/**	
	 *	 根据条件查询发件箱信息
	 */
	public PageResult getSendMsgsByCondition(String curUserID, Integer type, Condition cond, int page, int pagesize);
	/**	
	 *	 根据条件查询sys发件箱信息
	 *	 type:消息类型,null（全部）
	 */
	public PageResult getSendSysMsgsByCondition(String curUserID, Condition cond, int page, int pagesize);
	
	/**	
	 *	 根据条件查询收件箱信息
	 */
	public PageResult getReceiveMsgsByCondition(String curUserID, Integer type, Condition cond, int page, int pagesize);
	/**	
	 *	 根据条件查询sys收件箱信息
	 *	 type:消息类型,null（全部）
	 */
	public PageResult getReceiveSysMsgsByCondition(String curUserID, Condition cond, int page, int pagesize);
	/**	
	 *	 将msg设置为已读状态
	 */	
	public boolean setMsgReadState(String curUserID, String msgID);
}
