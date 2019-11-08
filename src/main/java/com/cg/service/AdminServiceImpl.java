package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Admin;

import com.cg.repo.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService{
	
	
	
	
	@Autowired
	AdminRepo  adminRepo;

	@Override
	public Admin addAdmin(Admin a) {
		 return adminRepo.save(a);
	}

	@Override
	public List<Admin> getAdminList() {
		return  (List<Admin>) adminRepo.findAll();
	}

}
