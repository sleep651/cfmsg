package com.hirisun.msg.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hirisun.msg.domain.User;

@Controller
public class TestController {
	
	/*	
	private TestService testService;

	public void setTestService(TestService testService) {
		this.testService = testService;
	}
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
	}
	*/
	
	//---------------begin page test
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
}
