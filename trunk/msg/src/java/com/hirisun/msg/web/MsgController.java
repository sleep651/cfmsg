package com.hirisun.msg.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nak.nsf.pager.PageResult;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hirisun.msg.constants.Condition;
import com.hirisun.msg.domain.ShortMessage;
import com.hirisun.msg.service.MsgService;

@Controller
public class MsgController {
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private MsgService msgService;

	public void setMsgService(MsgService msgService) {
		this.msgService = msgService;
	}
	
	
	@RequestMapping("/index")
	public void showSmsstat(String curUserID){
	}
	
	@RequestMapping("/sendsms")
	public void showSendsms(String curUserID){
	}
	
	@RequestMapping("/senddo")
	public String showSenddo(ShortMessage sendForm,String curUserID){
		if(sendForm!=null)
		{
			sendForm.setSendtime(new Date());
		}
		msgService.sendMsg(sendForm);
		logger.info("sendForm="+sendForm);
		return "/sendresult";
	}
	
	@RequestMapping("/sendresult")
	public void showSendresult(String curUserID){
	}
	
	@RequestMapping("/listshort")
	public void showtListShort(HttpServletRequest request,Model model,String curUserID,String page,String pagesize,String msgType){
		int pageInt = 1;
		int pagesizeInt = 10;
		
		try {
			pageInt = Integer.parseInt(page);
			pagesizeInt = Integer.parseInt(pagesize);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		Condition cond = new Condition();
		cond.setStartDate(request.getParameter("startTime"));
		cond.setEndDate(request.getParameter("endTime"));
		cond.setKey(request.getParameter("key"));
		
		PageResult pageResult;
		
		if("sendbox".equals(msgType))
		{
			pageResult = msgService.getSendMsgsByCondition(curUserID, ShortMessage.MSG_TYPE_SHORT, cond, pageInt, pagesizeInt);
		}
		else
		{
			pageResult = msgService.getReceiveMsgsByCondition(curUserID, ShortMessage.MSG_TYPE_SHORT, cond, pageInt, pagesizeInt);
		}
		
		model.addAttribute("pageResult",pageResult );
	}
	

	@RequestMapping("/listsys")
	public void showListSys(HttpServletRequest request,Model model,String curUserID,String page,String pagesize){
		int pageInt = 1;
		int pagesizeInt = 10;
		
		try {
			pageInt = Integer.parseInt(page);
			pagesizeInt = Integer.parseInt(pagesize);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		Condition cond = new Condition();
		cond.setStartDate(request.getParameter("startTime"));
		cond.setEndDate(request.getParameter("endTime"));
		cond.setKey(request.getParameter("key"));
		
		PageResult pageResult = msgService.getReceiveSysMsgsByCondition(curUserID, cond, pageInt, pagesizeInt);
		
		model.addAttribute("pageResult",pageResult );
	}
	
	@RequestMapping("/detailshort")
	public void showDetailShort(HttpServletRequest request,Model model,String curUserID,String page,String pagesize,String msgID){
		int pageInt = 1;
		int pagesizeInt = 10;

		try {
			pageInt = Integer.parseInt(page);
			pagesizeInt = Integer.parseInt(pagesize);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		//获得当前msg的parentid，如果当前msg的parentid不会空，则回复短信的parentid继承这个parentid
		//否则，以当前msg的id作为回复短信的parentid
		logger.info("msgID="+msgID);
		msgService.setMsgReadState(curUserID, msgID);
		
		String parentid = msgService.getMsgParentidByID(msgID);
		
		if(parentid==null || parentid.equals(""))
		{
			parentid = msgID;
		}
		
		PageResult pageResult = msgService.getDetailMsgs(parentid, pageInt, pagesizeInt);
		String receiveuserid = msgService.getReplyReceiveuserid(curUserID, parentid);
	
		model.addAttribute("pageResult",pageResult );
		model.addAttribute("receiveuserid",receiveuserid );
		model.addAttribute("parentid",parentid );
	}
	

	@RequestMapping("/detailsys")
	public void showDetailSys(HttpServletRequest request,Model model,String curUserID,String page,String pagesize,String msgID){
		int pageInt = 1;
		int pagesizeInt = 10;

		try {
			pageInt = Integer.parseInt(page);
			pagesizeInt = Integer.parseInt(pagesize);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		//获得当前msg的parentid，如果当前msg的parentid不会空，则回复短信的parentid继承这个parentid
		//否则，以当前msg的id作为回复短信的parentid
		logger.info("msgID="+msgID);
		msgService.setMsgReadState(curUserID, msgID);
		
		String parentid = msgService.getMsgParentidByID(msgID);
		
		if(parentid==null || parentid.equals(""))
		{
			parentid = msgID;
		}
		
		PageResult pageResult = msgService.getDetailMsgs(parentid, pageInt, pagesizeInt);
		String receiveuserid = msgService.getReplyReceiveuserid(curUserID, parentid);
	
		model.addAttribute("pageResult",pageResult );
		model.addAttribute("receiveuserid",receiveuserid );
		model.addAttribute("parentid",parentid );
	}
	
	
	@RequestMapping("/smsstat_getjson")
	public String showSmsstatGetjson(HttpServletRequest request, HttpServletResponse response, String username){
		//扩展Spring MVC 用以支持Controller对Ajax的处理
		String ret_json = "";
		try{
				if(username!=null && !username.equals(""))
				{
					int new_allMsgCount = msgService.getReceiveCount(username, null, false);
					int new_shortMsgCout = msgService.getReceiveCount(username, ShortMessage.MSG_TYPE_SHORT, false);
					int new_sysMsgCount = new_allMsgCount-new_shortMsgCout;
					
					int total_allMsgCout = msgService.getReceiveCount(username, null, null);
					int total_shortMsgCout = msgService.getReceiveCount(username, ShortMessage.MSG_TYPE_SHORT, null);
					int total_sytMsgCount = total_allMsgCout - total_shortMsgCout;
					
					logger.info("new_allMsgCount="+new_allMsgCount);
					logger.info("new_shortMsgCout="+new_shortMsgCout);
					logger.info("new_sysMsgCount="+new_sysMsgCount);
					logger.info("total_allMsgCout="+total_allMsgCout);
					logger.info("total_shortMsgCout="+total_shortMsgCout);
					logger.info("total_sytMsgCount="+total_sytMsgCount);
					
					ret_json += "{"
						+"new_shortMsgCout:"+new_shortMsgCout+","
						+"new_sysMsgCount:"+new_sysMsgCount
						+"}";
				}
				else
				{
					ret_json = null;
				}
		
		}catch(Exception e)
		{
			ret_json = null;
			e.printStackTrace();
		}
        response.setContentType("text/Xml;charset=utf-8");   
        PrintWriter out = null;   
        try {   
            out = response.getWriter();   
            out.println(ret_json);   
        }   
        catch (IOException ex1) {   
            ex1.printStackTrace();   
        }   
        finally {   
            out.close();   
        }   
  
        return null;   
	}
	
	@RequestMapping("/sendsms_getjson")
	public String showSendsmsGetjson(HttpServletRequest request, HttpServletResponse response){
		//扩展Spring MVC 用以支持Controller对Ajax的处理
		String ret_json = "";
		String preStr = "";
		int max = 10;
		
		String q = request.getParameter("q");
		String limit = request.getParameter("limit");
		
		if(q!=null)
		{
			preStr = q;
		}
		
		if(limit!=null)
		{
			try {
				max = Integer.parseInt(limit);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ret_json = msgService.getUsersJson(preStr, max);
		
		logger.info("ret_json="+ret_json);
		
        response.setContentType("text/Xml;charset=utf-8");   
        PrintWriter out = null;   
        try {   
            out = response.getWriter();   
            out.println(ret_json);   
        }   
        catch (IOException ex1) {   
            ex1.printStackTrace();   
        }   
        finally {   
            out.close();   
        }   
  
        return null;   
	}
	

/*
	@RequestMapping("/adddo")
	public String addUser(@ModelAttribute User user){
		System.out.println(user.getUserName());
		return "indexform";
	}
	
	@RequestMapping("/form")
	public String showForm(@ModelAttribute User user){
		user.setUserName("newusername");
		return "indexform";
	}
	
	@RequestMapping("/show")
	public String showIndexPage(Model model,@RequestParam("uid") String userID){
		User user=new User();
		user.setUserName("sssss");
		model.addAttribute("user",user );//testService.readUserByUserID(userID));
		return "/index";
	}*/
}
