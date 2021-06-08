package com.projectmigry.migry.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmigry.migry.admin.domain.CoverStory;
import com.projectmigry.migry.admin.domain.Search;
import com.projectmigry.migry.admin.mapper.CoverStoryMapper;

@Service
public class CoverStoryService {

	@Autowired
	private CoverStoryMapper coverStoryMapper;
	
	public List<CoverStory> getCoverStoryList(Search search) throws Exception {
		return coverStoryMapper.selectCover(search);
	}
	
	public int countCoverStoryList() throws Exception {
		return coverStoryMapper.countCover();
	}
	
	public CoverStory getCoverStoryLatestInfo() throws Exception {
		return coverStoryMapper.selectCoverLatest();
	}
	
	public void insertCoverStory(CoverStory cover) throws Exception {
		coverStoryMapper.insertCover(cover);
	}
	
	public void deleteCoverStory(int id) throws Exception {
		coverStoryMapper.deleteCover(id);
	}
	
	public int getNewID() throws Exception {
		int id = coverStoryMapper.getMaxCoverID();
		return id++;
	}
}
