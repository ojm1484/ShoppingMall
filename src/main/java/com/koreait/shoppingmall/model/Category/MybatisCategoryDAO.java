package com.koreait.shoppingmall.model.Category;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.shoppingmall.domain.Category;
import com.koreait.shoppingmall.exception.CategoryException;

@Repository
public class MybatisCategoryDAO implements CategoryDAO {
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	@Override
	public List selecAll() {
		return sessionTemplate.selectList("Category.selectAll");
	}

	@Override
	public Category select(int category_id) {
		return sessionTemplate.selectOne("Category.select", category_id);
	}

	@Override
	public void insert(Category category) throws CategoryException{
		int result=sessionTemplate.insert("Category.insert",category);
		if(result==0) {
			throw new CategoryException("카테고리 등록 실패");
		}
	}
	@Override
	public void updateTeam(int team) throws CategoryException{
		int result=sessionTemplate.update("Category.updateTeam", team);
		if(result==0) {
			throw new CategoryException("team값 업데이트 실패");
		}
	}

	@Override
	public void update(Category category) {
	}

	@Override
	public void delete(int category_id) {
	}

	@Override
	public void updateStep(Category category) {
		sessionTemplate.update("Category.updateStep", category);
	}

	@Override
	public void insertSub(Category category)throws CategoryException {
		int result= sessionTemplate.insert("Category.insertSub", category);
		if(result==0) {
			throw new CategoryException("하위 카테고리 입력 실패");
		}
	}

}
