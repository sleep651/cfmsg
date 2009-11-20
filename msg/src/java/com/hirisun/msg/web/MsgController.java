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
	
	@RequestMapping("/page/index")
	public String showPageIndex(){
		return "page/index";
	}
	
	@RequestMapping("/page/send")
	public String showPageSend(){
		return "page/send";
	}
	
	@RequestMapping("/page/sendsys")
	public String showPageSendsys(){
		return "page/sendsys";
	}
	
	@RequestMapping("/page/inbox")
	public String showPageInbox(){
		return "/page/inbox";
	}
	
	
	@RequestMapping("/smsbox/index")
	public void showSmsstat(String curUserID){
	}
	
	@RequestMapping("/smsbox/sendsms")
	public void showSendsms(String curUserID){
	}
	
	@RequestMapping("/smsbox/senddo")
	public String showSenddo(ShortMessage sendForm,String curUserID){
		if(sendForm!=null)
		{
			sendForm.setSendtime(new Date());
		}
		msgService.sendMsg(sendForm);
		logger.info("sendForm="+sendForm);
		return "/smsbox/sendresult";
	}
	
	@RequestMapping("/smsbox/sendresult")
	public void showSendresult(String curUserID){
	}
	
	@RequestMapping("/smsbox/main")
	public void showtTabSendbox(HttpServletRequest request,Model model,String curUserID,String page,String pagesize,String msgType){
		int pageInt = 0;
		int pagesizeInt = 0;
		
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
		
		if(msgType.equals("sendbox"))
		{
			pageResult = msgService.getShortMessagesSendByCondition(curUserID, cond, pageInt, pagesizeInt);
		}
		else
		{
			pageResult = msgService.getShortMessageReceiveByCondition(curUserID, cond, pageInt, pagesizeInt);
		}
		
		model.addAttribute("pageResult",pageResult );
	}
	
	@RequestMapping("/smsbox/smsstat_getjson")
	public String showSmsstatGetjson(HttpServletRequest request, HttpServletResponse response, String username){
		//扩展Spring MVC 用以支持Controller对Ajax的处理
		String ret_json = "";
		try{
				String all_sendCount = ""+msgService.getCountSend(username);
				String alone_sendSys = ""+msgService.getCountReceiveSys(username);
				String alone_sendShort = ""+msgService.getCountReceiveShort(username);
				
				String all_receiveCount = ""+msgService.getCountSend(username);
				
				ret_json += "{"
					+"all_sendCount:"+all_sendCount+","
					+"alone_sendSys:"+alone_sendSys+","
					+"alone_sendShort:"+alone_sendShort+","
					+"all_receiveCount:"+all_receiveCount
					+"}";
		
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
