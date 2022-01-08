package com.koreait.shoppingmall.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.koreait.shoppingmall.domain.Product;
import com.koreait.shoppingmall.domain.ProductImg;
import com.koreait.shoppingmall.exception.ProductException;
import com.koreait.shoppingmall.exception.ProductImgException;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ProductImgDAO productImgDAO;

	@Override
	public List selectAll() {
		return productDAO.selectAll();
	}
	@Override
	public List selectAllByCategory(int category_id) {
		return productDAO.selectAllByCategory(category_id);
	}

	@Override
	public Product select(int product_id) {
		return productDAO.select(product_id);
	}

	//적어도 상품등록이란 product + product_img +이미지 업로드
	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Product product, List<ProductImg> productImgList) throws ProductException, ProductImgException {
		productDAO.insert(product); //상품정보
		
		//이시점부터는 mybatis에 의해 product_id값이 채워진 상태이다
		// product_id=최근에 들어간 pk
		
		for(ProductImg obj: productImgList) {
			System.out.println("productImg "+obj);
			//pk값을 채워넣자
			obj.setProduct(product);
			
			obj.getProduct().setProduct_id(product.getProduct_id());
			
			productImgDAO.insert(obj); //상품 등록
		}
	}

	@Override
	public void update(Product product) throws ProductException{
		productDAO.update(product);
	}

	@Override
	public void delete(int product_id) throws ProductException{
		productDAO.delete(product_id);
	}


}
