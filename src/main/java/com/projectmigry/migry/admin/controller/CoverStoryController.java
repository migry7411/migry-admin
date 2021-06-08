package com.projectmigry.migry.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.projectmigry.migry.admin.common.NumPerPage;
import com.projectmigry.migry.admin.common.Utility;
import com.projectmigry.migry.admin.domain.CoverStory;
import com.projectmigry.migry.admin.domain.Member;
import com.projectmigry.migry.admin.domain.PageInfo;
import com.projectmigry.migry.admin.domain.Search;
import com.projectmigry.migry.admin.service.CoverStoryService;

@Controller
public class CoverStoryController {
	
	@Autowired
	private CoverStoryService coverStoryService;
	
	@RequestMapping("/admin/cover/listCover.do")
    public ModelAndView listMember(HttpServletRequest request) {
		ModelAndView mv  = new ModelAndView();
		int beginPerPage = 0;		// 시작 레코드 번호
		int numPerPage = NumPerPage.COVER_STORY;		// 한 페이지당 보여줄 레코드 개수
		//int recNo = 0;					// 개시판 출력 글번호
		
        try {
        	PageInfo pageinfo = Utility.getPageInfo(request);
        	
        	// 시작 레코드번호 값 부여
        	beginPerPage = pageinfo.getNowPage() * numPerPage;
        	
        	int startRow = beginPerPage + 1;
    		int endRow = beginPerPage + numPerPage;
    		
        	Search s = new Search(pageinfo.getSearchColumn(), pageinfo.getSearchWord(), startRow, endRow, pageinfo.getCode());
        	List<CoverStory> list = coverStoryService.getCoverStoryList(s);
        	int count = coverStoryService.countCoverStoryList();
        	String url = "listCover.do";
        	String paging = Utility.paging(pageinfo.getSearchColumn(), pageinfo.getSearchWord(), pageinfo.getNowPage(), pageinfo.getNowBlock(),
        						count, numPerPage, url, pageinfo.getCode());
        	
        	Member loginMember = (Member)(request.getSession().getAttribute("login"));
            
            if(loginMember == null) {
            	mv.setViewName("/common/login");
            	mv.addObject("flag", "U");
            } else if (!loginMember.getUserid().equals("administrator")) {
            	mv.setViewName("/common/login");
            	mv.addObject("flag", "A");
            } else {
	        	mv.setViewName("/admin/cover/list");
	        	mv.addObject("list", list);
	        	mv.addObject("count", list.size());
	        	mv.addObject("pageinfo", pageinfo);
	        	mv.addObject("paging", paging);
            }
        } catch (Exception ex) {
        	System.out.println("Cover Story List 에러 : " + ex.toString());
        	ex.printStackTrace();
        	mv.setViewName("/common/error");
        }
        
        return mv;
    }
	
	@RequestMapping(value = "/admin/cover/insertCover.do", method = RequestMethod.POST )
	public String insertCover(@ModelAttribute CoverStory dto, HttpServletRequest request) {
		try {
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
			
			if(loginMember == null) {
				return "redirect:/common/login.do?flag=U";
	        } else if (!loginMember.getUserid().equals("administrator")) {
	        	return "redirect:/common/login.do?flag=A";
	        } else {
	        	dto.setId(coverStoryService.getNewID());
	        	coverStoryService.insertCoverStory(dto);
	           	return "redirect:listCover.do";
	        }
		} catch(Exception ex) {
			System.out.println("Cover Story Insert 에러 : " + ex.toString());
        	ex.printStackTrace();
        	return "redirect:/common/error.do";
		}
	}
	
	@RequestMapping("/admin/cover/deleteCover.do" )
	public String deleteCover(HttpServletRequest request) {
		try {
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
			
			if(loginMember == null) {
				return "redirect:/common/login.do?flag=U";
	        } else if (!loginMember.getUserid().equals("administrator")) {
	        	return "redirect:/common/login.do?flag=A";
	        } else {
				int id = Integer.parseInt(request.getParameter("id"));
				coverStoryService.deleteCoverStory(id);
				return "redirect:listCover.do";
	        }
		} catch(Exception ex) {
			System.out.println("Cover Story Delete 에러 : " + ex.toString());
        	ex.printStackTrace();
        	return "redirect:/common/error.do";
		}
	}
}
