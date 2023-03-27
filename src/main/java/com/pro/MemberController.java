package com.pro;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pro.dto.AdminDTO;
import com.pro.dto.MemberDTO;
import com.pro.service.MemberService;

@Controller
public class MemberController {
	private MemberService memberService;	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}


	@RequestMapping("/login/view")
	public String loginView() {
		
		return "login_view";
	}
	
	@RequestMapping("/login")
	public String login(HttpSession session, String email, String passwd ,HttpServletResponse response) throws IOException {
		
		MemberDTO dto = memberService.login(email, passwd);
		AdminDTO addto = memberService.adminLogin(email, passwd);
		if(dto != null) {
			session.setAttribute("dto", dto);
		}
		if (dto == null && addto != null) {
			session.setAttribute("addto", addto);			
		}
		if(dto == null && addto == null){
			response.setContentType("text/html; charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('아이디, 비밀번호를 확인해주십시오'); history.back();</script>");
			out.flush();	
		}
		
		return "redirect:/";
	} 
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("/member/register/view")
	public String memberRegisterView() {
		
		return "create_account";
	}
	
	@RequestMapping("/member/register")
	public String memberRegister(MemberDTO dto) {
		int result = memberService.insertMember(dto);
		
		return "redirect:/login/view";
	}
	
	@RequestMapping("/member/check/view")
	public String memberCheckView() {
	
		
		
		return "check_pwd";
	}
	@RequestMapping("/member/update/view")
	public String memberUpdateView() {
		
		return "change_main";
	}
	
	
	@RequestMapping("/check/passwd/{passwd}")
	public ResponseEntity<String> checkPasswd(@PathVariable(name = "passwd") String passwd, HttpSession session){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		MemberDTO dto = (MemberDTO) session.getAttribute("dto");
		String check = dto.getPasswd();
		if(check.equals(passwd)) {
			map.put("code", 1);
		}else {
			map.put("code", 0);
		}
		
		return new ResponseEntity(map,HttpStatus.OK);
	}
	

	
	@RequestMapping("/member/nick/change")
	public String updateNick() {
		
		
		return "change_nick";
	}
	@RequestMapping("/member/email/change")
	public String updateEmail() {
		
		
		return "change_email";
	}
	@RequestMapping("/member/passwd/change")
	public String updatepasswd() {
		
		
		return "change_pwd";
	}
	
	

	
	
	
}
