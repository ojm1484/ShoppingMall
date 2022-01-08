package com.koreait.shoppingmall.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Product {
	private int product_id;
	private Category category;
	private String product_name;
	private int price;
	private String introduce;
	private String detail;
	private MultipartFile[] imgFiles; //다중파일 업로드 때문에 배열로 준비 
	
}
