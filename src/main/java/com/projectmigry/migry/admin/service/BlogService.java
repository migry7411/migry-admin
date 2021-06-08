package com.projectmigry.migry.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmigry.migry.admin.domain.Blog;
import com.projectmigry.migry.admin.domain.Search;
import com.projectmigry.migry.admin.mapper.BlogMapper;

@Service
public class BlogService {

	@Autowired
	private BlogMapper blogMapper;
	
	public List<Blog> getBlogLatestList() throws Exception {
		return blogMapper.selectBlogLatestList();
	}
	
	public List<Blog> getBlogList(Search search) throws Exception {
		return blogMapper.selectBlogList(search);
	}
	
	public int countBlogList() throws Exception {
		return blogMapper.countBlogList();
	}
	
	public Blog getBlogInfo(int id) throws Exception {
		return blogMapper.selectBlogInfo(id);
	}
	
	public void insertBlog(Blog blog) throws Exception {
		blogMapper.insertBlog(blog);
	}
	
	public void deleteBlog(int id) throws Exception {
		blogMapper.deleteBlog(id);
	}
}
