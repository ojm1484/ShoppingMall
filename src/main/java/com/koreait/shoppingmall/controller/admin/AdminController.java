package com.koreait.shoppingmall.controller.admin;

import java.net.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.shoppingmall.domain.Admin;
import com.koreait.shoppingmall.exception.AdminException;
import com.koreait.shoppingmall.model.admin.AdminService;
import com.koreait.shoppingmall.util.HashBuilder;
import com.koreait.shoppingmall.util.Message;

//관리자 인증과 관련된 요청을 처리하는 하위 컨트롤러
@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private HashBuilder hashBuilder;
	
	//로그인 폼 요청 처리
	@GetMapping("/login/form")
	public String getLoginForm(HttpServletRequest request) {
		
		return "admin/login/loginForm";
	}
	
	//로그인 요청 처리
	@PostMapping("/login")
	@ResponseBody // return값에 반환된 데이터를 viewResolver가 해석하는게 아니라 메서드의 반환형으로명시한 데이터자체를 응답데이터로 전송 원래는 .jsp가 붙음
	public Message loginCheck(HttpServletRequest request,Admin admin){
		
		//서비스에게 일 시키기 전에 비밀번호를 hash값으로 변환해서 보내야 한다.
		String pass=hashBuilder.convertStringToHash(admin.getPass());
		admin.setPass(pass); //변환하고 바로 다시 담아주기
		
		Admin result=adminService.select(admin);
		System.out.println("관리자 로그인 결과"+result);
		
		Message message = new Message();
		message.setCode(1);
		message.setMsg("인증성공");
		
		//클라이언트가 재접속 시 데이터를 사용할 수 있도록 session에 reusult를 담자
		HttpSession session=request.getSession();
		session.setAttribute("admin", result); //세션에 dto저장
		
		return message;
	}
	
	//로그아웃 요청 처리
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate(); //세션 무효화 기존의 세션을 사용할 수 없게됨
		
		return "redirect:/admin/login/form";
	}
	
	@ExceptionHandler(AdminException.class)
	@ResponseBody
	public ResponseEntity<Message> handle(HttpServletRequest request,AdminException e) {
		org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
		header.add("Content-Type","text/html;charset=utf-8");
		//한글 및 제대로 된 응답 정보를 구성하려면 responseEntity header, body
		//Gson을 써도 되지만 로드존슨은 이미 자동으로 json으로 변환하는 내부적 처리를 했다...!홀리몰리! 객체->json
		Message message = new Message();
		message.setCode(0);
		message.setMsg(e.getMessage());
		
		ResponseEntity<Message> entity=new ResponseEntity(message, HttpStatus.OK);
		return entity;
	}
}









