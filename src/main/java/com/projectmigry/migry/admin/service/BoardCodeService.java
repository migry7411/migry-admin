package com.projectmigry.migry.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmigry.migry.admin.domain.BoardCode;
import com.projectmigry.migry.admin.mapper.BoardCodeMapper;

@Service
public class BoardCodeService {
	
	@Autowired
	private BoardCodeMapper boardCodeMapper;

	public List<BoardCode> getBoardCodeList(String mode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("mode", mode);
    	
    	return boardCodeMapper.selectBoardCode(map);
	}
	
	public BoardCode getBoardCodeInfo(String id) throws Exception {
		return boardCodeMapper.selectBoardCodeOne(id);
	}
	
	public void insertBoardCode(BoardCode boardCode) throws Exception {
		boardCodeMapper.insertBoardCode(boardCode);
	}
	
	public void updateBoardCode(BoardCode boardCode) throws Exception {
		boardCodeMapper.updateBoardCode(boardCode);
	}
	
	public String getNewBoardCodeID() throws Exception {
		String newID = "";
		String maxID = boardCodeMapper.getMaxBoardCodeID();
		
		if(maxID == null) {
			newID = "B0000";
		} else {
			int cnt = Integer.parseInt(maxID.substring(1));
			cnt++;
			newID = "B" + String.format("%04d", cnt);
		}
		
		return newID;
	}
}
