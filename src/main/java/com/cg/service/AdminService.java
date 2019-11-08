package com.cg.service;

import java.util.List;

import com.cg.entity.Admin;


public interface AdminService {
	
	public Admin addAdmin(Admin a);
	
	public List<Admin> getAdminList();

}