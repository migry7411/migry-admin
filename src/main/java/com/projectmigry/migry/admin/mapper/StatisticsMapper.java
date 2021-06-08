package com.projectmigry.migry.admin.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public interface StatisticsMapper {

	public List<Map<String, Object>> selectBoardStats() throws Exception;
}
