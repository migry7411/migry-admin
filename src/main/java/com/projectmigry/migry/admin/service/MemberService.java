package com.projectmigry.migry.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmigry.migry.admin.common.Encrypt;
import com.projectmigry.migry.admin.domain.Member;
import com.projectmigry.migry.admin.domain.Search;
import com.projectmigry.migry.admin.mapper.MemberMapper;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	public List<Member> getMemberList(Search search) throws Exception {
		List<Member> list = memberMapper.selectMember(search);
		
		for(Member m : list) {
			String pwd = m.getPassword();
			String email = m.getEmail();
			
			m.setPassword(Encrypt.decrypt(pwd));
			m.setEmail(Encrypt.decrypt(email));
		}
		
		return list;
	}
	
	public int countMemberList(Search search) throws Exception {
		return memberMapper.countMember(search);
	}
	
	public Member getMemberInfo(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
    	map.put("userid", userid);
    	
    	Member member = memberMapper.selectMemberOne(map);
    	
    	if(member != null) {
			String pwd = member.getPassword();
			String email = member.getEmail();
			
			member.setPassword(Encrypt.decrypt(pwd));
			member.setEmail(Encrypt.decrypt(email));
		}
		
		return member;
	}
	
	public void insertMember(Member member) throws Exception {
		memberMapper.insertMember(member);
	}
	
	public void updateMember(Member member) throws Exception {
		memberMapper.updateMember(member);
	}
	
	public void updateMemberAccess(String userid) throws Exception {
		memberMapper.updateMemberAccess(userid);
	}
	
	public void deleteMember(String userid) throws Exception {
		memberMapper.deleteMember(userid);
	}
	
	public String findMember(String column, String email, String userid, String username) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
    	map.put("userid", userid);
    	map.put("username", username);
    	map.put("email", Encrypt.encrypt(email));
    	map.put("column", column);
    	
    	String value = memberMapper.findMember(map);
    	
    	if(column == "PASSWORD" && value != null) {
			value = Encrypt.decrypt(value);
		}
    	
    	return value;
	}
	
	public String checkMember(String column, String value) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
    	map.put("column", column);
    	
    	if(column == "EMAIL") {
    		map.put("value",  Encrypt.encrypt(value));
    	} else {
    		map.put("value", value);
    	}
    	
    	return memberMapper.checkMember(map);
	}
}
