package com.projectmigry.migry.admin.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.projectmigry.migry.admin.domain.BoardCode;

@Component
public interface BoardCodeMapper {

	public List<BoardCode> selectBoardCode(Map<String, Object> map) throws Exception;
	
	public BoardCode selectBoardCodeOne(String id) throws Exception;
	
	public void insertBoardCode(BoardCode boardCode) throws Exception;
	
	public void updateBoardCode(BoardCode boardCode) throws Exception;
	
	public String getMaxBoardCodeID() throws Exception;
}
