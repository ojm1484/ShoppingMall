package com.koreait.shoppingmall.model.admin;

import java.util.List;

import com.koreait.shoppingmall.domain.Admin;

public interface AdminService {
	public List selectAll(); //관리자 모든 목록
	public Admin select(Admin admin); 
	public void insert(Admin admin);
	public void update(Admin admin);
	public void delete(Admin admin);

}
