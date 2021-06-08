package com.projectmigry.migry.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projectmigry.migry.admin.common.JsonUtil;
import com.projectmigry.migry.admin.domain.Member;
import com.projectmigry.migry.admin.service.MemberService;

@Controller
public class AdminController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/admin/main.do")
    public ModelAndView adminMain(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Member loginMember = (Member)(request.getSession().getAttribute("login"));
        
        if(loginMember == null) {
        	mv.setViewName("/common/login");
        	mv.addObject("flag", "U");
        } else if (!loginMember.getUserid().equals("administrator")) {
        	mv.setViewName("/common/login");
        	mv.addObject("flag", "A");
        } else {
        	mv.setViewName("/admin/main");
        }
        
        return mv;
    }
	
	@RequestMapping("/admin/loginPage.do")
    public String loginPage() {
        return "admin/login/login";
    }
	
	@RequestMapping("/admin/loginProc.do")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		int flag;
		
		try {
			Member member = memberService.getMemberInfo(userid);
			
			if(member == null) {
				flag = 1;
			} else if(!member.getPassword().equals(passwd)) {
				flag = 2;
			} else if (!member.getUserid().equals("administrator")) {
				flag = 3;
			} else {
				flag = 0;
				HttpSession session = request.getSession();
				session.setAttribute("login", member);
				memberService.updateMemberAccess(userid);
			}
			
			mv.setViewName("/admin/login/loginProc");
			mv.addObject("flag", flag);
		} catch (Exception ex) {
			System.out.println("Login 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
		}
		
		return mv;
	}
	
	@RequestMapping("/admin/login.do")
    public void loginFilter(HttpServletRequest request, HttpServletResponse response) {
        Member loginMember = (Member)(request.getSession().getAttribute("login"));
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(loginMember == null) {
        	map.put("flag", "1");
        } else if (!loginMember.getUserid().equals("administrator")) {
        	map.put("flag", "2");
        } else {
        	map.put("flag", "0");
        }
        
        JsonUtil.parseJSON(map, response);
    }
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/admin/loginPage.do";
	}
}
