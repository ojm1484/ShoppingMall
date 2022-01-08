package com.koreait.shoppingmall.model.Category;

import java.util.List;

import com.koreait.shoppingmall.domain.Category;

public interface CategoryDAO {
	
	public List selecAll();
	public Category select(int category_id);
	public void insert(Category category);
	public void updateTeam(int team); //team값 설정
	public void update(Category category);
	public void delete(int category_id);
	public void updateStep(Category category);	//답변자리 확보
	public void insertSub(Category category);	//답변 등록

}
