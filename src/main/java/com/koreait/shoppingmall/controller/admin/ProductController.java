package com.koreait.shoppingmall.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.shoppingmall.domain.Product;
import com.koreait.shoppingmall.domain.ProductImg;
import com.koreait.shoppingmall.exception.AdminException;
import com.koreait.shoppingmall.exception.ProductException;
import com.koreait.shoppingmall.exception.ProductImgException;
import com.koreait.shoppingmall.exception.UploadException;
import com.koreait.shoppingmall.model.Category.CategoryService;
import com.koreait.shoppingmall.model.product.ProductService;
import com.koreait.shoppingmall.util.FileManager;
import com.koreait.shoppingmall.util.Pager;

//관리자의 상품과 관련된 요청을 처리하는 하위 컨트롤러
@Controller
public class ProductController{
	@Autowired
	private FileManager fileManager;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private Pager pager;
	
	//상품목록 요청
	@GetMapping("/product/list")
	public String getList(HttpServletRequest request,Model model) {
		List productList=productService.selectAll();
		pager.init(productList, request);
		
		model.addAttribute("productList",productList);
		model.addAttribute("pager",pager);
		return "admin/product/list";
	}
	
	//상품등록 폼 요청
	@GetMapping("/product/registform")
	public String registForm(HttpServletRequest request,Model model) {
		//3단계:카테고리 목록 가져오기
		List categoryList=categoryService.selectAll();
		model.addAttribute("categoryList",categoryList);
		return"admin/product/regist";
	}
	
	@RequestMapping(value="/product/regist", method=RequestMethod.POST)
	public String regist(HttpServletRequest request,Product product) {

		//이미지 저장 +db에 넣기 
		String[] imgArray=fileManager.saveMultiFile(request, product);
		System.out.println("imgArray "+imgArray);
		
		List imgList = new ArrayList();
		for(String obj:imgArray) {
			ProductImg productImg = new ProductImg(); //empty\
			productImg.setImg(obj);
			imgList.add(productImg); //리스트에 모으기
		}
		//서비스에게 디비 저장
		productService.regist(product, imgList); //product 테이블 + product_img테이블
		
		
		System.out.println("업로드 성공");
		return "redirect:/admin/product/list";
	}
	@ExceptionHandler(UploadException.class)
	public ModelAndView handle(UploadException e) {
		ModelAndView mav = new ModelAndView("admin/error/result");
		mav.addObject("e",e); // 에러 객체 심기 
		return mav;
	}
	@ExceptionHandler(ProductException.class)
	public ModelAndView handle(ProductException e) {
		ModelAndView mav = new ModelAndView("admin/error/result");
		mav.addObject("e",e); // 에러 객체 심기 
		return mav;
	}
	@ExceptionHandler(ProductImgException.class)
	public ModelAndView handle(ProductImgException e) {
		ModelAndView mav = new ModelAndView("admin/error/result");
		mav.addObject("e",e); // 에러 객체 심기 
		return mav;
	}

}
	

