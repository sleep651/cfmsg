package com.hirisun.msg.service;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nak.nsf.pager.PageResult;
import com.hirisun.msg.domain.Admin;
import com.hirisun.msg.domain.ShortMessage;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/hirisun/msg/context/applicationContext.xml" })
public class MsgServiceTests {

	static{
		
		Properties props = new Properties();
		try {
			props
					.load(ClassLoader
							.getSystemResourceAsStream("com/hirisun/msg/log4j/log4j.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		PropertyConfigurator.configure(props);
	}
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	
	
	@Resource
	private MsgService msgService;

	//添加管理员
	@Test
	public void test_addAdmin() throws Exception {

		Admin admin = new Admin();
		admin.setPower(0);
		admin.setUserid("test");

		boolean ret = msgService.addAdmin(admin);

		assertTrue(ret);
	}
	
	//添加管理员X
	@Test
	public void test_addAdminX() throws Exception {

		for(int i=0; i<50; i++)
		{
			Admin admin = new Admin();
			admin.setPower(0);
			admin.setUserid("test"+i);

			msgService.addAdmin(admin);
		}

		assertTrue(true);
	}
	
	//删除管理员
	@Test
	public void test_deleteAdmin() throws Exception {

		Admin admin = new Admin();
		admin.setId("40288af224e7748b0124e7748d960001");

		boolean ret = msgService.deleteAdmin(admin);

		assertTrue(ret);
	}
	
	//发送消息
	@Test
	public void test_sendMsg() throws Exception {

		ShortMessage msg = new ShortMessage();
		msg.setSenduserid("wxy");
		msg.setShowsenduserid("王锡勇");
		msg.setReceiveuserid("test");
		msg.setSendtime(new Date());
		msg.setContent("你好！测试消息！");
		msg.setParentid(null);
		msg.setType(ShortMessage.MSG_TYPE_SHORT);
		msg.setReply(true);
		
		boolean ret = msgService.sendMsg(msg);

		assertTrue(ret);
	}
	
	//发送消息X
	@Test
	public void test_sendMsgX() throws Exception {

		for(int i=10; i<30; i++)
		{
			ShortMessage msg = new ShortMessage();
			msg.setSenduserid("wxy");
			msg.setShowsenduserid("wxy"+i);
			msg.setReceiveuserid("wxy");
			msg.setSendtime(new Date());
			msg.setContent("i=["+i+"]\t你好！测试消息!");
			msg.setParentid(null);
			msg.setType(ShortMessage.MSG_TYPE_SHORT);
			msg.setReply(true);
			
			msgService.sendMsg(msg);
		}

		assertTrue(true);
	}
	
	//读取管理员列表
	@Test
	public void test_getAdmins() throws Exception {
		System.out.println("@test_getAdmins:begin-------------");
		int page = 1;
		int pagesize = 20;
		
		PageResult ret = msgService.getAdmins(page, pagesize);
		
		if(ret!=null)
		{
			System.out.println("pager=\n"+ret.getPager().toString());
			List list = ret.getList();
			if(list != null)
			{
				for(int i=0; i<list.size(); i++)
				{
					Admin admin = (Admin)list.get(i);
					System.out.println("admin["+i+"]="+admin.toString());
				}
			}
		}
		System.out.println("@test_getAdmins:end==================");
		assertTrue(ret!=null);
	}
	
	
	
	//读取已发送短消息
	@Test
	public void test_getShortMessagesSend() throws Exception {
		System.out.println("@test_getShortMessagesSend:begin-------------");
		String curUserID = "wxy";
		int page = 1;
		int pagesize = 20;
		
		PageResult ret = msgService.getShortMessagesSend(curUserID, page, pagesize);
		
		if(ret!=null)
		{
			System.out.println("pager=\n"+ret.getPager().toString());
			List list = ret.getList();
			if(list != null)
			{
				for(int i=0; i<list.size(); i++)
				{
					ShortMessage msg = (ShortMessage)list.get(i);
					System.out.println("msg["+i+"]="+msg.toString());
				}
			}
		}
		System.out.println("@test_getShortMessagesSend:end==================");
		assertTrue(ret!=null);
	}
	
	
	//读取短消息详情
	@Test
	public void test_getShortMessagesDetail() throws Exception {
		System.out.println("@test_getShortMessagesDetail:begin-------------");
		String msgID = "40288af224e7d5420124e7d544310001";
		int page = 1;
		int pagesize = 20;
		
		PageResult ret = msgService.getShortMessagesDetail(msgID, page, pagesize);
		
		if(ret!=null)
		{
			System.out.println("pager=\n"+ret.getPager().toString());
			List list = ret.getList();
			if(list != null)
			{
				for(int i=0; i<list.size(); i++)
				{
					ShortMessage msg = (ShortMessage)list.get(i);
					System.out.println("msg["+i+"]="+msg.toString());
				}
			}
		}
		System.out.println("@test_getShortMessagesDetail:end==================");
		assertTrue(ret!=null);
	}
	
	
	//读取已接收的短消息
	@Test
	public void test_getShortMessageReceive() throws Exception {
		System.out.println("@test_getShortMessageReceive:begin-------------");
		String curUserID = "test";
		int page = 1;
		int pagesize = 20;
		
		PageResult ret = msgService.getShortMessageReceive(curUserID, page, pagesize);
		
		if(ret!=null)
		{
			System.out.println("pager=\n"+ret.getPager().toString());
			List list = ret.getList();
			if(list != null)
			{
				for(int i=0; i<list.size(); i++)
				{
					ShortMessage msg = (ShortMessage)list.get(i);
					System.out.println("msg["+i+"]="+msg.toString());
				}
			}
		}
		System.out.println("@test_getShortMessageReceive:end==================");
		assertTrue(ret!=null);
	}
	
	//读取已接收的短消息
	@Test
	public void test_getCountReceive() throws Exception {
		System.out.println("@getCountReceive:begin-------------");
		String curUserID = "test";
		
		int ret = msgService.getCountReceive(curUserID);
		System.out.println("ret="+ret);
		System.out.println("@getCountReceive:end==================");
		
		assertTrue(ret!=0);
	}
	
	//获取符合前缀为preStr的，Users的json字符串
	@Test
	public void test_getUsersJson() throws Exception {
		System.out.println("@test_getUsersJson:begin-------------");
		String preStr = " ";
		int max = 10;
		
		String ret = msgService.getUsersJson(preStr, max);
		System.out.println("ret="+ret);
		System.out.println("@test_getUsersJson:end==================");
		assertTrue(true);
	}
	
	
	//读取短消息详情
	@Test
	public void test_getDetailMsgs() throws Exception {
		System.out.println("@test_getDetailMsgs:begin-------------");
		String curUserID = "hpc";
		String msgID = "40288aef252005c10125200680200001";
		int page = 1;
		int pagesize = 20;
		
		PageResult ret = msgService.getDetailMsgs(msgID, page, pagesize);
		
		if(ret!=null)
		{
			System.out.println("pager=\n"+ret.getPager().toString());
			List list = ret.getList();
			if(list != null)
			{
				for(int i=0; i<list.size(); i++)
				{
					ShortMessage msg = (ShortMessage)list.get(i);
					System.out.println("msg["+i+"]="+msg.toString());
				}
			}
		}
		System.out.println("@test_getDetailMsgs:end==================");
		assertTrue(ret!=null);
	}

}
