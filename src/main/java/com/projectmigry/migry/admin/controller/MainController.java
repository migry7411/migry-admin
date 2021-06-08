package com.projectmigry.migry.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projectmigry.migry.admin.domain.Board;
import com.projectmigry.migry.admin.domain.CoverStory;
import com.projectmigry.migry.admin.domain.Member;
import com.projectmigry.migry.admin.service.BoardService;
import com.projectmigry.migry.admin.service.CoverStoryService;
import com.projectmigry.migry.admin.service.MemberService;

@Controller
public class MainController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CoverStoryService coverStoryService;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/user/main.do")
	public ModelAndView mainPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/main");
		
		String title = "No Data";
		String date = "";
		String contents = "글이 없습니다.";
		
		try {
			CoverStory cover = coverStoryService.getCoverStoryLatestInfo();
			List<Board> boardList = boardService.getLatestBoardList();
			
			if(cover != null) {
				title = cover.getSubject();
				date = cover.getRegdate();
				contents = cover.getContents();
			}
			
			mv.addObject("coverTitle", title);
			mv.addObject("coverDate", date);
			mv.addObject("coverContents", contents);
			mv.addObject("boardList", boardList);
		} catch(Exception ex) {
			System.out.println("Main Page 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
		}
		
		return mv;
	}
	
	@RequestMapping("/user/login.do")
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
			} else {
				flag = 0;
				HttpSession session = request.getSession();
				session.setAttribute("login", member);
				memberService.updateMemberAccess(userid);
			}
			
			mv.setViewName("/user/login");
			mv.addObject("flag", flag);
		} catch (Exception ex) {
			System.out.println("Login 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
		}
		
		return mv;
	}
	
	@RequestMapping("/user/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		String mainPage = "/user/main.do";
		return "redirect:" + mainPage;
	}
	
	@RequestMapping("/user/about.do")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/about");
		return mv;
	}
	
	@RequestMapping("/common/error.do")
	public ModelAndView error() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/common/error");
		return mv;
	}
	
	@RequestMapping("/common/login.do")
	public ModelAndView loginFilter(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String flag = request.getParameter("flag");
		mv.setViewName("/common/login");
		mv.addObject("flag", flag);
		return mv;
	}
}
