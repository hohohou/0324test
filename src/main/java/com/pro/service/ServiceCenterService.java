package com.pro.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pro.dto.FileDTO;
import com.pro.dto.InquiryAnswerDTO;
import com.pro.dto.InquiryDTO;
import com.pro.dto.NoticeCommentDTO;
import com.pro.dto.NoticeDTO;
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
	public int uploadImage(String absolutePath) {
		int fileNum = serviceMapper.selectImageFileNum();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fileNum", fileNum);
		map.put("path", absolutePath);
		serviceMapper.insertBoardImage(map);
		return fileNum;

		
		
	}
	public FileDTO selectFile(int inquiryNum, int fileNum) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("inquiryNum", inquiryNum);
		map.put("fileNum,", fileNum);
		return serviceMapper.selectFile(map);
	}
	public FileDTO selectImageFile(int fileNum) {
		
		return serviceMapper.selectImageFile(fileNum);
	}
	public List<NoticeDTO> selectAllNotice() {
		
		return serviceMapper.selectAllNotice();
	}
	public int updateNoticeCount(int noticeNum) {
		
		return serviceMapper.updateNoticeCount(noticeNum);
	}
	public NoticeDTO selectNotice(int noticeNum) {
		
		return serviceMapper.selectNotice(noticeNum);
	}
	public List<NoticeCommentDTO> selectNoticeComment(int noticeNum) {
		
		return serviceMapper.selectNoticeComment(noticeNum);
	}
	public int insertNoticeComment(NoticeCommentDTO ndto) {
		
		return serviceMapper.insertNoticeComment(ndto);
	}
	
	
	
}
