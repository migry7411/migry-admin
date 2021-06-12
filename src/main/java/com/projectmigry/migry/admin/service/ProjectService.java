package com.projectmigry.migry.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmigry.migry.admin.domain.Project;
import com.projectmigry.migry.admin.mapper.ProjectMapper;

@Service
public class ProjectService {

	@Autowired
	private ProjectMapper projectMapper;
	
	public void insertProject(Project project) throws Exception {
		projectMapper.insertProject(project);
	}
	
	public List<Project> getProjectList() throws Exception {
		return projectMapper.selectProjectList();
	}
	
	public int countProjectList() throws Exception {
		return projectMapper.countProjectList();
	}
	
	public Project getProjectInfo(int id) throws Exception {
		return projectMapper.selectProjectInfo(id);
	}
	
	public void updateProject(Project project) throws Exception {
		projectMapper.updateProject(project);
	}
	
	public void deleteProject(int id) throws Exception {
		projectMapper.deleteProject(id);
	}
}
