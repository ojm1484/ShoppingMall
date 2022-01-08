package com.koreait.shoppingmall.model.product;

import java.util.List;

import com.koreait.shoppingmall.domain.Product;

public interface ProductDAO {

	public List selectAll();
	public List selectAllByCategory(int category_id);
	public Product select(int product_id);
	public void insert(Product product);	
	public void update(Product product);
	public void delete(int product_id);
}
