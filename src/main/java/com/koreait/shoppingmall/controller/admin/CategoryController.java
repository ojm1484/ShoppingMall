package com.koreait.shoppingmall.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.shoppingmall.domain.Category;
import com.koreait.shoppingmall.exception.CategoryException;
import com.koreait.shoppingmall.model.Category.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	//카테고리 목록 요청 처리
	@GetMapping("/category/list")
	public ModelAndView getList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/category/list");
		List categoryList=categoryService.selectAll(); //3단계 
		
		mav.addObject("categoryList",categoryList); //4단계
		return mav;
	}
	
	//카테고리 등록 폼 
	@GetMapping("/category/registform")
	public String registForm(HttpServletRequest request) {
		return "admin/category/regist";
	}
	
	@PostMapping("/category/regist")
	public String regist(HttpServletRequest request,Category category) {
		//3단계: 카테고리 등록
		categoryService.insert(category);
		
		return "redirect:/admin/category/list";
	}
	@GetMapping("/category/detail")
	public String getDetail(HttpServletRequest request,int category_id, Model model) {
		//3단계:한건의 데이터 가져오기
		Category category=categoryService.select(category_id);
		
		//4단계: 저장
		model.addAttribute("category", category);
		
		return "admin/category/detail";
	}
	//서브 카테고리 등록 요청
	@PostMapping("/category/registsub")
	public String registSub(HttpServletRequest request,Category category) { //category_name, team, step, depth(내본글의 정보)
		//3단계
		categoryService.registSub(category);
		
		
		return "redirect:/admin/category/list";
	}
	
	
	@ExceptionHandler(CategoryException.class)
	public ModelAndView handle(CategoryException e){
		ModelAndView mav = new ModelAndView("admin/error/result");
		mav.addObject("e",e);//결과저장
		return mav;
	}
}
