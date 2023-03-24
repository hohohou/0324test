package com.pro.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.pro.dto.AdminDTO;
import com.pro.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO login(HashMap<String, Object> map);

	AdminDTO adminLogin(HashMap<String, Object> map);

	int insertMember(MemberDTO dto);

	int checkPasswd(MemberDTO dto);

}
