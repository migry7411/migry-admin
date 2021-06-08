package com.projectmigry.migry.admin.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projectmigry.migry.admin.domain.Board;
import com.projectmigry.migry.admin.domain.BoardReply;
import com.projectmigry.migry.admin.domain.Search;

@Component
public interface BoardMapper {

	public List<Board> selectBoard(Search search) throws Exception;
	
	public int countBoard(Search search) throws Exception;
	
	public Board selectBoardOne(int id) throws Exception;
	
	public void insertBoard(Board board) throws Exception;
	
	public void updateBoard(Board board) throws Exception;
	
	public void deleteBoard(int id) throws Exception;
	
	public int getMaxBoardID() throws Exception;
	
	public void updateReadCount(int id) throws Exception;
	
	public List<Board> selectLatestBoard() throws Exception;
	
	public void insertBoardReply(BoardReply boardReply) throws Exception;
	
	public List<BoardReply> selectBoardReply(Search search) throws Exception;
	
	public int countBoardReply(int boardid) throws Exception;
	
	public void deleteBoardReply(int id) throws Exception;
	
	public int getMaxBoardReplyID() throws Exception;
}
