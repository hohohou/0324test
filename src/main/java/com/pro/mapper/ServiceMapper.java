package com.pro.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pro.dto.FileDTO;
import com.pro.dto.InquiryAnswerDTO;
import com.pro.dto.InquiryDTO;

@Mapper
public interface ServiceMapper {

	int insertInquiry(InquiryDTO dto);

	int selectInquiryNum();

	List<InquiryDTO> selectAllInquiry(String email);

	InquiryDTO selectInquiry(int inquiryNum);

	InquiryAnswerDTO selectInquiryAnswer(int inquiryNum);

	int deleteInquiry(int inquiryNum);

	int updateInquiry(InquiryDTO dto);

	List<InquiryDTO> selectAllAdminInquiry();

	int insertAdminInquiry(InquiryAnswerDTO adto);

	int updateInquiryStatus(int inquiryNum);

	int deleteInquiryAnswer(int inquiryNum);

	List<InquiryDTO> inquiryAdminSort(HashMap<String, Object> map);

	int insertFile(FileDTO fileDTO);


}
