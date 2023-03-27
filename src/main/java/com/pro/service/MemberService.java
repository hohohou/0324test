package com.pro.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.pro.dto.AdminDTO;
import com.pro.dto.MemberDTO;
import com.pro.mapper.MemberMapper;

@Service
public class MemberService {
	private MemberMapper mapper;
	public MemberService(MemberMapper mapper) {
		this.mapper = mapper;
	}
	public MemberDTO login(String email, String passwd) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("passwd", passwd);
		return mapper.login(map);
	}
	public AdminDTO adminLogin(String email, String passwd) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", email);
		map.put("passwd", passwd);
		return mapper.adminLogin(map);
	}
	public int insertMember(MemberDTO dto) {
		
		return mapper.insertMember(dto);
	}
	public int checkPasswd(MemberDTO dto) {
		
		return mapper.checkPasswd(dto);
	}
	public int deleteMember(String email) {
		
		return mapper.deleteMember(email);
	}
	
	
	
	
	
	
	
	
	
}
