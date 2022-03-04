package com.app.admin.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.admin.models.entity.Usuario;
import com.app.admin.models.service.IPerfilservice;



@RestController
@RequestMapping("/servicios")
public class UsuarioControllerRest {
	
	@Autowired
	private IPerfilservice perfilService;
	
	
	@GetMapping("/usuarios")
	public List<Usuario> listar() {
		return perfilService.findAllUsuarios();
	}
	
	@GetMapping("/usuarios/{id}")
	public Usuario show(@PathVariable Long id) {
		return this.perfilService.findOne(id);
	
}
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario create(@RequestBody Usuario usuario) {
		
		this.perfilService.save(usuario);
		return usuario;
	}
	@PutMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id) {
		Usuario currentUsuario = this.perfilService.findOne(id);
		currentUsuario.setUsername(usuario.getUsername());
		currentUsuario.setArea(usuario.getArea());
		currentUsuario.setCorreo(usuario.getCorreo());
		currentUsuario.setPassword(usuario.getPassword());
		currentUsuario.setEnabled(usuario.getEnabled());
		this.perfilService.save(currentUsuario);
		return currentUsuario;
	}
	
	@DeleteMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Usuario currentUsuario = this.perfilService.findOne(id);
		this.perfilService.delete(currentUsuario);
	}
}
