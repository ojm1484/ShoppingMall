package com.koreait.shoppingmall.model.Category;

import java.util.List;

import com.koreait.shoppingmall.domain.Category;

public interface CategoryService {
	public List selectAll();
	public Category select(int category_id);
	public void insert(Category category);
	public void update(Category category);
	public void delete(int category_id);
	
	public void registSub(Category category);	//하위 카테고리 등록
}
