package com.hirisun.msg.service;

import com.hirisun.msg.constants.Condition;
import com.hirisun.msg.domain.Admin;
import com.hirisun.msg.domain.ShortMessage;

import nak.nsf.pager.PageResult;

public interface MsgService {

	/**
	 * 	 序号	函数名（中文）	函数功能	输入参数	输出参数	备注
	 *	1	添加管理员	添加指定userID的管理员权限	userID	TRUE/FALSE	
	 */
	public boolean addAdmin(Admin admin);
	
	
	/**
	*	序号	函数名（中文）	函数功能	输入参数	输出参数	备注
	*	2	删除管理员	删除指定userID的管理员权限	userID	TRUE/FALSE	
	*/
	public boolean deleteAdmin(Admin admin);
	
	
	/**
	*	序号	函数名（中文）	函数功能	输入参数	输出参数	备注
	*	3	读取管理员列表	获取当前已添加的管理员列表	无	人员信息List		
	*/
	public PageResult getAdmins(int page, int pagesize);
	
	
	/**
	*	序号	函数名（中文）	函数功能	输入参数	输出参数	备注
	*	1	发送消息	发送系统消息	发送人ID，
	*	显示的发送人名称，
	*	接收人ID序列，
	*	发送内容，
	*	消息父ID，
	*	消息类型ID，
	*	是否可回复	TRUE/FALSE	
	*/
	public boolean sendMsg(ShortMessage msg);
	
	/**
	*	序号	函数名（中文）	函数功能	输入参数	输出参数	备注
	*	1	读取已发送短消息	获得发送人为指定userID对应所有短消息	userID	短消息List	
	*/
	@Deprecated
	public PageResult getShortMessagesSend(String curUserID, int page, int pagesize);
	
	/**
	*	序号	函数名（中文）	函数功能	输入参数	输出参数	备注
	*	2	读取短消息详情	获取指定短消息对应的消息序列	messageID	短消息List	
	*/
	@Deprecated
	public PageResult getShortMessagesDetail(String msgID, int page, int pagesize);

	/**
	*	序号	函数名（中文）	函数功能	输入参数	输出参数	备注
	*	1	读取已接收的短消息	获得接收人为指定userID对应所有短消息，以及所有接收人为“全体”的系统消息	userID	短消息List	
	*/
	@Deprecated
	public PageResult getShortMessageReceive(String curUserID, int page, int pagesize);
	
	
	/************************补充的接口  begin************************/
	/**	
	 *	 获得接收条数
	 */
	@Deprecated
	public int getCountReceive(String curUserID);
	
	/**	
	 *	 获得接收的系统消息条数
	 */
	@Deprecated
	public int getCountReceiveSys(String curUserID);

	/**	
	 *	 获得发送条数
	 */
	@Deprecated
	public int getCountSend(String curUserID);

	/**	
	 *	 获得接收的短消息条数
	 */
	@Deprecated
	public int getCountReceiveShort(String curUserID);
	
	
	/************************补充的接口  end************************/
	
	/**	
	 *	 根据条件查询发件箱信息
	 */
	@Deprecated
	public PageResult getShortMessagesSendByCondition(String curUserID, Condition cond, int page, int pagesize);
	
	/**	
	 *	 根据条件查询收件箱信息
	 */
	@Deprecated
	public PageResult getShortMessageReceiveByCondition(String curUserID, Condition cond, int page, int pagesize);
	
	/**	
	 *	 获取符合前缀为preStr的，Users的json字符串，当preStr为空时，返回任意匹配
	 *	 返回查询结果的前max条
	 */
	public String getUsersJson(String preStr, int max);
	
	
	/************************业务逻辑的接口  begin************************/
	/**	
	 *	 获取当前回复消息应该填入的收件人
	 */
	public String getReplyReceiveuserid(String curUserID, String msgID);
	
	/**	
	 *	 获取当前消息的parentid
	 */
	public String getMsgParentidByID(String msgID);
	
	/************************业务逻辑的接口  end************************/

	
	/************************新增UserMsg后的接口  begin************************/

	/**	
	 *	 获得接收的Sys短消息条数
	 *   isRead:true(已读)，false（未读），null（全部）
	 *   type:消息类型,null（全部）
	 */
	public int getReceiveCount(String curUserID, Integer type, Boolean isRead);

	/**
	*	序号	函数名（中文）	函数功能	输入参数	输出参数	备注
	*	2	读取短消息详情	获取指定短消息对应的消息序列	messageID	短消息List	
	*/
	public PageResult getDetailMsgs(String msgID, int page, int pagesize);

	/**	
	 *	 根据条件查询发件箱信息
	 *	 type:消息类型,null（全部）
	 */
	public PageResult getSendMsgsByCondition(String curUserID, Integer type, Condition cond, int page, int pagesize);
	
	/**	
	 *	 根据条件查询sys发件箱信息
	 *	 type:消息类型,null（全部）
	 */
	public PageResult getSendSysMsgsByCondition(String curUserID, Condition cond, int page, int pagesize);
	
	
	/**	
	 *	 根据条件查询收件箱信息
	 *	 type:消息类型,null（全部）
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
	
	/************************新增UserMsg后的接口  begin************************/
}
