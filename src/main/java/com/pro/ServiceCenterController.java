package com.pro;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Server;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pro.dto.FileDTO;
import com.pro.dto.InquiryAnswerDTO;
import com.pro.dto.InquiryDTO;
import com.pro.dto.MemberDTO;
import com.pro.service.MemberService;
import com.pro.service.ServiceCenterService;

@Controller
public class ServiceCenterController {
	private ServiceCenterService serviceService;

	public ServiceCenterController(ServiceCenterService serviceService) {
		this.serviceService = serviceService;
	}

	@RequestMapping("")
	public String main() {
		return "service_center_main";
	}

	@RequestMapping("/inquiry")
	public ModelAndView inquiry(HttpSession session, ModelAndView view) {
		MemberDTO dto = (MemberDTO) session.getAttribute("dto");
		if (dto != null) {
			view.addObject("dto", dto);
			view.setViewName("service_center_inquiry");
			return view;
		} else {
			view.setViewName("service_center_inquiry");
			return view;
		}
	}

	@RequestMapping("/inquiry/list")
	public ModelAndView inquiryList(HttpSession session) {
		ModelAndView view = new ModelAndView();
		MemberDTO dto = (MemberDTO) session.getAttribute("dto");
		if (dto != null) {
			String email = dto.getEmail();
			List<InquiryDTO> list = serviceService.selectAllInquiry(email);
			view.addObject("list", list);
			view.setViewName("service_center_inquiry_list");
			return view;
		} else {
			view.setViewName("service_center_inquiry_list");
			return view;
		}

	}
	
//	@RequestMapping("/fileupload")
//	public ResponseEntity<String> fileUpload(@RequestParam(value = "upload") MultipartFile file,HttpServletRequest request, HttpServletResponse response, HttpSession session){
//		String originFileName = file.getOriginalFilename();
//		String root = "c:\\fileupload\\";
//		MemberDTO dto = (MemberDTO) session.getAttribute("dto");
//		String fileName = dto.getEmail() + originFileName.substring(originFileName.lastIndexOf('.'));
//		File savefile = new File(root + fileName);
//		int fileNum = serviceService.uploadImage(savefile.getAbsolutePath());
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		try {
//		file.transferTo(savefile);
//		map.put("uploaded", true);
//		map.put("url", "/image/"+fileNum);
//		map.put("bi_no", fileNum);
//		}catch (IOException e) {
//			map.put("uploaded", false);
//			map.put("message", "파일 업로드 중 에러 발생");
//		}
//		
//		
//		return new ResponseEntity(map, HttpStatus.OK);
//	}

	@RequestMapping("/inquiry/add")
	public String InquiryWrite(InquiryDTO dto, @RequestParam("file") MultipartFile[] file) {
		int inquiryNum = serviceService.insertInquiry(dto);

		String root = "c://fileupload";		
		for (int i = 0; i < file.length; i++) {
			if (file[i].getSize() == 0)
				continue;
			String originFileName = file[i].getOriginalFilename();
			String fileName = i + originFileName.substring(originFileName.lastIndexOf('.'));
			try {
				File saveFile = new File(root + fileName);
				file[i].transferTo(saveFile);
				serviceService.insertFile(new FileDTO(saveFile, inquiryNum, i));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/inquiry/list";
	}

	@RequestMapping("/inquiry/answer/{inquiryNum}")
	public ModelAndView inquriyView(@PathVariable("inquiryNum") int inquiryNum) {
		ModelAndView view = new ModelAndView();
		InquiryDTO dto = serviceService.selectInquiry(inquiryNum);
		InquiryAnswerDTO adto = serviceService.selectInquiryAnswer(inquiryNum);
		List<FileDTO> flist = serviceService.selectFileList(inquiryNum);
		view.addObject("adto", adto);
		view.addObject("dto", dto);
		view.addObject("flist", flist);
		view.setViewName("service_center_inquiry_answer");

		return view;

	}

	@RequestMapping("/inquiry/delete/{inquiryNum}")
	public String inquiryDelete(@PathVariable(name = "inquiryNum") int inquiryNum) {

		int result = serviceService.inquiryDelete(inquiryNum);
		int result1 = serviceService.inquiryAnswerDelete(inquiryNum);

		return "redirect:/inquiry/list";
	}

	@RequestMapping("/inquiry/update/view/{inquiryNum}")
	public ModelAndView inquiryupdateview(@PathVariable(name = "inquiryNum") int inquiryNum) {
		ModelAndView view = new ModelAndView();
		InquiryDTO dto = serviceService.selectInquiry(inquiryNum);
		view.addObject("dto", dto);
		view.setViewName("service_center_inquiry_update");

		return view;

	}

	@RequestMapping("/inquiry/update")
	public String inquiryupdate(InquiryDTO dto) {
		int result = serviceService.updateInquiry(dto);

		return "redirect:/inquiry/answer/" + dto.getInquiryNum();

	}

	@RequestMapping("/inquiry/admin/list")
	public ModelAndView inquiryAdminList(ModelAndView view, HttpSession session, String type) {

		List<InquiryDTO> list = serviceService.selectAllAdminInquiry();

		view.addObject("list", list);
		view.setViewName("service_center_admin_inquiry_list");

		return view;
	}

	@RequestMapping("/inquiry/admin/answer/{inquiryNum}")
	public ModelAndView inquriyAdminView(@PathVariable("inquiryNum") int inquiryNum) {
		ModelAndView view = new ModelAndView();
		InquiryDTO dto = serviceService.selectInquiry(inquiryNum);
		InquiryAnswerDTO adto = serviceService.selectInquiryAnswer(inquiryNum);
		view.addObject("adto", adto);
		view.addObject("dto", dto);
		view.setViewName("service_center_admin_inquiry_answer");

		return view;

	}

	@RequestMapping("/inquiry/admin/answer")
	public String inquriyAdminAnswer(InquiryAnswerDTO adto, InquiryDTO dto) {
		int inquiryNum = serviceService.InsertAdminInquiry(adto);
		int status = serviceService.updateInquiryStatus(adto.getInquiryNum());

		return "redirect:/inquiry/admin/answer/" + dto.getInquiryNum();

	}

	@RequestMapping("/inquiry/admin/sort")
	public ModelAndView inquiryAdminSort(String type, int answer, ModelAndView view) {
		List<InquiryDTO> list = serviceService.inquiryAdminSort(type, answer);
		view.addObject("list", list);
		view.setViewName("service_center_admin_inquiry_list");
		return view;
	}

}
