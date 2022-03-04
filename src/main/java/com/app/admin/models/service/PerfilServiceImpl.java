package com.app.admin.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.admin.models.dao.IRolePerfil;
import com.app.admin.models.entity.Usuario;




@Service
public class PerfilServiceImpl implements IPerfilservice {
	
	@Autowired
	private IRolePerfil perfilDao;	
	
	

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
	return perfilDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAllUsuarios() {
		// TODO Auto-generated method stub
		return (List<Usuario>) perfilDao.findAll();
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		perfilDao.save(usuario);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findOne(Long id) {
		// TODO Auto-generated method stub
		return perfilDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Usuario usuario) {
		perfilDao.delete(usuario);
		
	}
		
	

	@Override
	@Transactional
	public int activar(long idUsuario) {
		int rows = perfilDao.unlock(idUsuario);
		return rows;
	}

	@Override
	@Transactional
	public int bloquear(long idUsuario) {
		int rows = perfilDao.lock(idUsuario);
		return rows;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		perfilDao.deleteById(id);
		
	}



	
	
}
