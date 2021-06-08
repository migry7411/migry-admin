package com.projectmigry.migry.admin.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projectmigry.migry.admin.domain.Blog;
import com.projectmigry.migry.admin.domain.Search;

@Component
public interface BlogMapper {

	public List<Blog> selectBlogLatestList() throws Exception;
	
	public List<Blog> selectBlogList(Search search) throws Exception;
	
	public int countBlogList() throws Exception;
	
	public Blog selectBlogInfo(int id) throws Exception;
	
	public void insertBlog(Blog blog) throws Exception;
	
	public void deleteBlog(int id) throws Exception;
}
