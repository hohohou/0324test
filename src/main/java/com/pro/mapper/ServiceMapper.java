package com.pro.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pro.dto.FileDTO;
import com.pro.dto.InquiryAnswerDTO;
import com.pro.dto.InquiryDTO;
import com.pro.dto.NoticeCommentDTO;
import com.pro.dto.NoticeDTO;

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

	int selectImageFileNum();

	List<FileDTO> selectFileList(int inquiryNum);

	void insertBoardImage(HashMap<String, Object> map);

	FileDTO selectFile(HashMap<String, Object> map);

	FileDTO selectImageFile(int fileNum);

	List<NoticeDTO> selectAllNotice();

	int updateNoticeCount(int noticeNum);

	NoticeDTO selectNotice(int noticeNum);

	List<NoticeCommentDTO> selectNoticeComment(int noticeNum);

	int insertNoticeComment(NoticeCommentDTO ndto);

	


}
