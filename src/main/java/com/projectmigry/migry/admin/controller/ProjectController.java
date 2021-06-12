package com.projectmigry.migry.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projectmigry.migry.admin.common.JsonUtil;
import com.projectmigry.migry.admin.domain.Member;
import com.projectmigry.migry.admin.domain.Project;
import com.projectmigry.migry.admin.service.ProjectService;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value = "/admin/project/saveProject.do", method = RequestMethod.POST)
	public String saveProject(@ModelAttribute Project dto, HttpServletRequest request) {
		try{
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
			
			if(loginMember == null) {
				return "redirect:/common/login.do?flag=U";
	        } else if (!loginMember.getUserid().equals("administrator")) {
	        	return "redirect:/common/login.do?flag=A";
	        } else {
				if(dto.getId() == 0) {
					projectService.insertProject(dto);
				} else {
					projectService.updateProject(dto);
				}
				
				return "redirect:listProject.do";
	        }
		} catch(Exception ex) {
			System.out.println("Project Save 에러 : " + ex.toString());
        	ex.printStackTrace();
        	return "redirect:/common/error.do";
		}
	}
	
	@RequestMapping("/admin/project/listProject.do")
	public String listProject(Model model, HttpServletRequest request) {
		try{
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
	        
	        if(loginMember == null) {
	        	model.addAttribute("flag", "U");
	        	return "redirect:/common/login";
	        } else if (!loginMember.getUserid().equals("administrator")) {
	        	model.addAttribute("flag", "A");
	        	return "redirect:/common/login";
	        } else {
	        	List<Project> list = projectService.getProjectList();
				model.addAttribute("list", list);
				model.addAttribute("count", list.size());
				return "admin/project/list";
	        }
		} catch(Exception ex) {
			System.out.println("Project List 에러 : " + ex.toString());
        	ex.printStackTrace();
        	return "redirect:/common/error.do";
		}
	}
	
	@RequestMapping("/admin/project/detailProject.do")
	public void detailProject(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			Project project = projectService.getProjectInfo(Integer.parseInt(id));
			
			Map<String, Object> map = new HashMap<String, Object>();
        	map.put("id", project.getId());
        	map.put("title", project.getTitle());
        	map.put("company", project.getCompany());
        	map.put("content", project.getContent());
        	map.put("startYm", project.getStartYm());
        	map.put("endYm", project.getEndYm());
        	
        	JsonUtil.parseJSON(map, response);
		} catch(Exception ex) {
			System.out.println("Project Detail 에러 : " + ex.toString());
        	ex.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/admin/project/deleteProject.do")
	public String deleteProject(HttpServletRequest request) {
		try{
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
			
			if(loginMember == null) {
				return "redirect:/common/login.do?flag=U";
	        } else if (!loginMember.getUserid().equals("administrator")) {
	        	return "redirect:/common/login.do?flag=A";
	        } else {
	        	int id = Integer.parseInt(request.getParameter("id"));
				projectService.deleteProject(id);
				return "redirect:listProject.do";
	        }
		} catch(Exception ex) {
			System.out.println("Project Delete 에러 : " + ex.toString());
        	ex.printStackTrace();
        	return "redirect:/common/error.do";
		}
	}
}
