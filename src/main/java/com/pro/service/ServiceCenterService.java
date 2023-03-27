package com.pro.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pro.dto.FileDTO;
import com.pro.dto.InquiryAnswerDTO;
import com.pro.dto.InquiryDTO;
import com.pro.mapper.ServiceMapper;

@Service
public class ServiceCenterService {
	private ServiceMapper serviceMapper;
	public ServiceCenterService(ServiceMapper serviceMapper) {
		this.serviceMapper = serviceMapper;
	}
	public int insertInquiry(InquiryDTO dto) {
		int inquiryNum = serviceMapper.selectInquiryNum();
		dto.setInquiryNum(inquiryNum);
		serviceMapper.insertInquiry(dto);
		return inquiryNum;
	}
	public List<InquiryDTO> selectAllInquiry(String email) {
		
		return serviceMapper.selectAllInquiry(email);
	}
	public InquiryDTO selectInquiry(int inquiryNum) {
		
		return serviceMapper.selectInquiry(inquiryNum);
	}
	public InquiryAnswerDTO selectInquiryAnswer(int inquiryNum) {
		
		return serviceMapper.selectInquiryAnswer(inquiryNum);
	}
	public int inquiryDelete(int inquiryNum) {
		
		return serviceMapper.deleteInquiry(inquiryNum);
		
	}
	public int updateInquiry(InquiryDTO dto) {
		
		return serviceMapper.updateInquiry(dto);
	}
	public List<InquiryDTO> selectAllAdminInquiry() {

		return serviceMapper.selectAllAdminInquiry();
	}
	public int InsertAdminInquiry(InquiryAnswerDTO adto) {
			
		return serviceMapper.insertAdminInquiry(adto);
	}
	public int updateInquiryStatus(int inquiryNum) {
		
		return serviceMapper.updateInquiryStatus(inquiryNum);
	}
	public int inquiryAnswerDelete(int inquiryNum) {
		
		return serviceMapper.deleteInquiryAnswer(inquiryNum);
	}
	public List<InquiryDTO> inquiryAdminSort(String type, int answer) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("answer", answer);
		return serviceMapper.inquiryAdminSort(map);
	}
	public int insertFile(FileDTO fileDTO) {
		
		return serviceMapper.insertFile(fileDTO);
	}
	public List<FileDTO> selectFileList(int inquiryNum) {
		
		return serviceMapper.selectFileList(inquiryNum);
	}
	
	
	
}
