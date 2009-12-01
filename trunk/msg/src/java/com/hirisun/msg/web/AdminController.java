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
public class AdminController {
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private MsgService msgService;

	public void setMsgService(MsgService msgService) {
		this.msgService = msgService;
	}
	
	@RequestMapping("/admin/listsys")
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
			pageResult = msgService.getSendSysMsgsByCondition(curUserID, cond, pageInt, pagesizeInt);
		}
		else
		{
			pageResult = msgService.getReceiveSysMsgsByCondition(curUserID, cond, pageInt, pagesizeInt);
		}
		
		model.addAttribute("pageResult",pageResult );
	}
	

	@RequestMapping("/admin/sendsms")
	public void showSendsms(String curUserID){
	}
	
	@RequestMapping("/admin/senddo")
	public String showSenddo(ShortMessage sendForm,String curUserID){
		if(sendForm!=null)
		{
			sendForm.setSendtime(new Date());
		}
		msgService.sendMsg(sendForm);
		logger.info("sendForm="+sendForm);
		return "/admin/sendresult";
	}
	
	@RequestMapping("/admin/sendresult")
	public void showSendresult(String curUserID){
	}
	


	@RequestMapping("/admin/detailsys")
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
	
}
