package com.projectmigry.migry.admin.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.projectmigry.migry.admin.domain.Member;
import com.projectmigry.migry.admin.domain.Search;

@Component
public interface MemberMapper {

	public List<Member> selectMember(Search search) throws Exception;
	
	public int countMember(Search search) throws Exception;
	
	public Member selectMemberOne(Map<String, String> map) throws Exception;
	
	public void insertMember(Member member) throws Exception;
	
	public void updateMember(Member member) throws Exception;
	
	public void updateMemberAccess(String userid) throws Exception;
	
	public void deleteMember(String userid) throws Exception;
	
	public String findMember(Map<String, String> map) throws Exception;
	
	public String checkMember(Map<String, String> map) throws Exception;
}
