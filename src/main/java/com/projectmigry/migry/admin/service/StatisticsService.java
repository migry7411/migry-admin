package com.projectmigry.migry.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmigry.migry.admin.mapper.StatisticsMapper;

@Service
public class StatisticsService {

	@Autowired
	private StatisticsMapper statisticsMapper;
	
	public List<Map<String, Object>> getBoardStatsList() throws Exception {
		return statisticsMapper.selectBoardStats();
	}
}
