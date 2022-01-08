package com.koreait.shoppingmall.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.shoppingmall.domain.Notice;
import com.koreait.shoppingmall.model.notice.NoticeService;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
		@GetMapping("/notice/list")
		public String getList(HttpServletRequest request) {
			
			return "admin/notice/list";
		}
		@GetMapping("/notice/registform")
		public String getForm(HttpServletRequest request) {
			
			return "admin/notice/regist";
		}
		//상세보기 요청 처리
		@GetMapping("/notice/detail")
		public ModelAndView getDetail(@RequestParam(name = "notice_id", defaultValue="0") int notice_id) {
			Notice notice=noticeService.select(notice_id);
			ModelAndView mav = new ModelAndView("admin/notice/detail");
			mav.addObject("notice",notice);
			
			return mav;
		}
}
