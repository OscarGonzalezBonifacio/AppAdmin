package com.app.admin.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.admin.models.entity.Role;



public interface IRoleService {
	

	public List<Role> findAll();
	
	public Page<Role> findAll(Pageable pageable);
	



	

}
