package com.projectmigry.migry.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.projectmigry.migry.admin.common.JsonUtil;
import com.projectmigry.migry.admin.domain.BoardCode;
import com.projectmigry.migry.admin.domain.Member;
import com.projectmigry.migry.admin.service.BoardCodeService;

@Controller
public class BoardCodeController {
	
	@Autowired
	private BoardCodeService boardCodeService;
	
	@RequestMapping("/admin/board/listBoard.do")
	public ModelAndView listBoardCode(HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
		
		try{
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
	        
	        if(loginMember == null) {
	        	mv.setViewName("/common/login");
	        	mv.addObject("flag", "U");
	        } else if (!loginMember.getUserid().equals("administrator")) {
	        	mv.setViewName("/common/login");
	        	mv.addObject("flag", "A");
	        } else {
	        	List<BoardCode> list = boardCodeService.getBoardCodeList("A");
				mv.setViewName("/admin/board/list");
	        	mv.addObject("list", list);
	        	mv.addObject("count", list.size());
	        }
		} catch(Exception ex) {
			System.out.println("Board Code List 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/admin/board/saveBoard.do", method = RequestMethod.POST)
	public String saveBoardCode(@ModelAttribute BoardCode dto, HttpServletRequest request) {
		try{
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
			
			if(loginMember == null) {
				return "redirect:/common/login.do?flag=U";
	        } else if (!loginMember.getUserid().equals("administrator")) {
	        	return "redirect:/common/login.do?flag=A";
	        } else {
				if(dto.getId() == "") {
					dto.setId(boardCodeService.getNewBoardCodeID());
					boardCodeService.insertBoardCode(dto);
				} else {
					boardCodeService.updateBoardCode(dto);
				}
				
				return "redirect:listBoard.do";
	        }
		} catch(Exception ex) {
			System.out.println("Board Code Save 에러 : " + ex.toString());
        	ex.printStackTrace();
        	return "redirect:/common/error.do";
		}
	}
	
	@RequestMapping("/admin/board/detailBoardCode.do")
	public void detailBoardCode(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			BoardCode boardcode = boardCodeService.getBoardCodeInfo(id);
			
			Map<String, Object> map = new HashMap<String, Object>();
        	map.put("id", boardcode.getId());
        	map.put("name", boardcode.getName());
        	map.put("use_board", boardcode.getUse_board());
        	map.put("use_html_tag", boardcode.getUse_html_tag());
        	map.put("write_auth", boardcode.getWrite_auth());
        	map.put("use_reply", boardcode.getUse_reply());
        	
        	JsonUtil.parseJSON(map, response);
		} catch(Exception ex) {
			System.out.println("Board Code Detail 에러 : " + ex.toString());
        	ex.printStackTrace();
		}
	}
}
