package com.koreait.shoppingmall.model.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.shoppingmall.domain.Admin;
import com.koreait.shoppingmall.exception.AdminException;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	public List selectAll() {
		return null;
	}

	@Override
	public Admin select(Admin admin) throws AdminException{
		return adminDAO.select(admin);
	}

	@Override
	public void insert(Admin admin) {
	}

	@Override
	public void update(Admin admin) {
	}

	@Override
	public void delete(Admin admin) {
	}
	
}
