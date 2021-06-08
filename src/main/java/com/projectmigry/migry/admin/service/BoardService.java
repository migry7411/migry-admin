package com.projectmigry.migry.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmigry.migry.admin.domain.Board;
import com.projectmigry.migry.admin.domain.BoardReply;
import com.projectmigry.migry.admin.domain.Search;
import com.projectmigry.migry.admin.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	public List<Board> getBoardList(Search search) throws Exception {
		return boardMapper.selectBoard(search);
	}
	
	public int countBoardList(Search search) throws Exception {
		return boardMapper.countBoard(search);
	}
	
	public Board getBoardInfo(int id) throws Exception {
		return boardMapper.selectBoardOne(id);
	}
	
	public void insertBoard(Board board) throws Exception {
		boardMapper.insertBoard(board);
	}
	
	public void updateBoard(Board board) throws Exception {
		boardMapper.updateBoard(board);
	}
	
	public void deleteBoard(int id) throws Exception {
		boardMapper.deleteBoard(id);
	}
	
	public int getNewBoardID() throws Exception {
		int id = boardMapper.getMaxBoardID();		
		return id++;
	}
	
	public void updateReadCount(int id) throws Exception {
		boardMapper.updateReadCount(id);
	}
	
	public List<Board> getLatestBoardList() throws Exception {
		return boardMapper.selectLatestBoard();
	}
	
	public void insertBoardReply(BoardReply boardReply) throws Exception {
		boardMapper.insertBoardReply(boardReply);
	}
	
	public List<BoardReply> getBoardReplyList(Search search) throws Exception {
		return boardMapper.selectBoardReply(search);
	}
	
	public int countBoardReplyList(int boardid) throws Exception {
		return boardMapper.countBoardReply(boardid);
	}
	
	public void deleteBoardReply(int id) throws Exception {
		boardMapper.deleteBoardReply(id);
	}
	
	public int getNewBoardReplyID() throws Exception {
		int id = boardMapper.getMaxBoardReplyID();
		return id++;
	}
}
