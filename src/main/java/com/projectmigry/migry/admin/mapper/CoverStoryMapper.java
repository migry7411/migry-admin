package com.projectmigry.migry.admin.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projectmigry.migry.admin.domain.CoverStory;
import com.projectmigry.migry.admin.domain.Search;

@Component
public interface CoverStoryMapper {

	public List<CoverStory> selectCover(Search search) throws Exception;
	
	public int countCover() throws Exception;
	
	public CoverStory selectCoverLatest() throws Exception;
	
	public void insertCover(CoverStory cover) throws Exception;
	
	public void deleteCover(int id) throws Exception;
	
	public int getMaxCoverID() throws Exception;
}
