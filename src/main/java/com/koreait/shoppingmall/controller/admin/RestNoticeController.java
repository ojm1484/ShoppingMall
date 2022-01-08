package com.koreait.shoppingmall.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.shoppingmall.domain.Notice;
import com.koreait.shoppingmall.exception.NoticeException;
import com.koreait.shoppingmall.model.notice.NoticeService;
import com.koreait.shoppingmall.util.Message;

@Controller
public class RestNoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/rest/notice")
	@ResponseBody
	public List getList(HttpServletRequest request) {
		//게시물 목록
		List<Notice> noticeList=noticeService.selectAll();
		
		return noticeList;
	}
	//한건의 데이터 rest요청 처리
	@RequestMapping(value="/rest/notice/{notice_id}", method=RequestMethod.GET)
	@ResponseBody
	public Notice getDetail(@PathVariable(name="notice_id") int notice_id) {
		Notice notice=noticeService.select(notice_id);
		return notice;
	}
	
	//등록 요청 처리
	@RequestMapping(value="/rest/notice", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Message> insert(Notice notice) {
		System.out.println(notice.getTitle());
		System.out.println(notice.getWriter());
		System.out.println(notice.getContent());
		//이것도 안넘어와여
		
		Message message=new Message();
		message.setMsg("등록성공");
		message.setCode(1);
		
		noticeService.insert(notice);
		ResponseEntity<Message> entity=new ResponseEntity<Message>(message, HttpStatus.OK); //200
		return entity;
	}
	//한건 수정
	@RequestMapping(value="/rest/notice", method=RequestMethod.PUT)
	public ResponseEntity<Message> update(Notice notice){
		System.out.println("수정요청");
		
		noticeService.update(notice);
		
		Message message=new Message();
		message.setMsg("등록성공");
		message.setCode(1);
		
		noticeService.insert(notice);
		ResponseEntity<Message> entity=new ResponseEntity<Message>(message, HttpStatus.OK); //200
		
		
		return null;
	}
	//한건 삭제요청
	@RequestMapping(value="/rest/notice/{notice_id}", method=RequestMethod.DELETE)
	public ResponseEntity<Message> delete(@PathVariable(name = "notice_id") int notice_id){
		noticeService.delete(notice_id);
		
		Message message=new Message();
		message.setMsg("등록성공");
		message.setCode(1);
		
		ResponseEntity<Message> entity=new ResponseEntity<Message>(message, HttpStatus.OK); //200
		return entity;
	}
	
	@ExceptionHandler(NoticeException.class)
	public ResponseEntity<Message> handle(NoticeException e) {
		Message message=new Message();
		message.setMsg(e.getMessage());
		message.setCode(0);
		ResponseEntity<Message> entity=new ResponseEntity<Message>(message, HttpStatus.OK); //200
	
		return entity;
	}
}











