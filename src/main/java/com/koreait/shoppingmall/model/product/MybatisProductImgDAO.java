package com.koreait.shoppingmall.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.shoppingmall.domain.ProductImg;
import com.koreait.shoppingmall.exception.ProductImgException;

@Repository
public class MybatisProductImgDAO implements ProductImgDAO{
	
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	@Override
	public void insert(ProductImg productImg) throws ProductImgException {
		int result=sessionTemplate.insert("ProductImg.insert", productImg);
		//result=0; //0104 일부러 실패 테스트용
		if(result==0) {
			throw new ProductImgException("상품 사진 insert실패");
		}
	}

}
