package com.app.admin.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.admin.models.dao.IRoleDao;
import com.app.admin.models.dao.IRolePerfil;
import com.app.admin.models.entity.Role;




@Service
public class RoleServiceImpl implements IRoleService{
	
	
	@Autowired
	private IRoleDao roleDao;
	
	@Autowired
	private IRolePerfil rolePerfil;
	
	

	@Override
	@Transactional(readOnly = true)
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return (List<Role>) roleDao.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)
	public Page<Role> findAll(Pageable pageable) {
		return roleDao.findAll(pageable);
	}



	

	




}
