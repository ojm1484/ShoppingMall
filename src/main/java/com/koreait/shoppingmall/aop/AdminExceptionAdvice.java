package com.koreait.shoppingmall.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.shoppingmall.exception.AdminException;

//관리자와 관련된 모든 요청시에 예외가 발생하는 것을 감자할 수 있는 글로벌 익셉션 핸들러 객체
@ControllerAdvice //컨트롤러에서 종류와 상관없이 발생하는 예외를 감지하는 객체 
public class AdminExceptionAdvice {
	@ExceptionHandler(AdminException.class)
	public ModelAndView handle(AdminException e) {
		ModelAndView mav = new ModelAndView();
		AdminException adminException=new AdminException("관리자 인증이 필요한 서비스입니다.(글로벌)");
		mav.addObject("e",adminException);
		mav.setViewName("admin/error/result");
		return mav;
	}
}
