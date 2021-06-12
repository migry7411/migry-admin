package com.projectmigry.migry.admin.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projectmigry.migry.admin.domain.Project;

@Component
public interface ProjectMapper {

	public void insertProject(Project project) throws Exception;
	
	public List<Project> selectProjectList() throws Exception;
	
	public int countProjectList() throws Exception;
	
	public Project selectProjectInfo(int id) throws Exception;
	
	public void updateProject(Project project) throws Exception;
	
	public void deleteProject(int id) throws Exception;
}
