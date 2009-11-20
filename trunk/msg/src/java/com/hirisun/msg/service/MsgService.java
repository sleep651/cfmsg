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
	public PageResult getShortMessagesSend(String curUserID, int page, int pagesize);
	
	/**
	*	序号	函数名（中文）	函数功能	输入参数	输出参数	备注
	*	2	读取短消息详情	获取指定短消息对应的消息序列	messageID	短消息List	
	*/
	public PageResult getShortMessagesDetail(String msgID, int page, int pagesize);

	/**
	*	序号	函数名（中文）	函数功能	输入参数	输出参数	备注
	*	1	读取已接收的短消息	获得接收人为指定userID对应所有短消息，以及所有接收人为“全体”的系统消息	userID	短消息List	
	*/
	public PageResult getShortMessageReceive(String curUserID, int page, int pagesize);
	
	
	/************************补充的接口  begin************************/
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
	public int getCountReceiveShort(String curUserID);
	
	/**	
	 *	 获得发送条数
	 */
	public int getCountSend(String curUserID);
	
	/************************补充的接口  end************************/
	
	/**	
	 *	 根据条件查询发件箱信息
	 */
	public PageResult getShortMessagesSendByCondition(String curUserID, Condition cond, int page, int pagesize);
	
	/**	
	 *	 根据条件查询收件箱信息
	 */
	public PageResult getShortMessageReceiveByCondition(String curUserID, Condition cond, int page, int pagesize);
	
	
	/**	
	 *	 获取符合前缀为preStr的，Users的json字符串，当preStr为空时，返回任意匹配
	 *	 返回查询结果的前max条
	 */
	public String getUsersJson(String preStr, int max);

}
