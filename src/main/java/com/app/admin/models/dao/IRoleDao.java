package com.app.admin.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.app.admin.models.entity.Role;




public interface IRoleDao extends PagingAndSortingRepository<Role, Long> {
	
	
public List<Role> findAll() ;
	
	public Page<Role> findAll(Pageable pageable);

}
