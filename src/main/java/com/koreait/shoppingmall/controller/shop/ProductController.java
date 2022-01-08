package com.koreait.shoppingmall.controller.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.shoppingmall.model.Category.CategoryService;

@Controller
public class ProductController {

	//카테고리 소속과 상관없이 모든 상품 다 가져오기
	//해당 카테고리에 소속된 상품의 목록 가져오기
	@GetMapping("/product/list")
	public ModelAndView selectAll(HttpServletRequest request,@RequestParam(name = "category_id", defaultValue = "0") int category_id) {
		//category_id 0 이면 모든상품, 0이 아니면 조건 부여
		ModelAndView mav = new ModelAndView("shop/product/list");
		
		return mav;
	}
	
	
}
