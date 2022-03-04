package com.app.admin.controllers;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.admin.models.entity.Usuario;
import com.app.admin.models.service.IPerfilservice;
import com.app.admin.models.service.IRoleService;
import com.app.admin.models.service.JpaUserDetailsService;
import com.app.admin.models.util.paginator.PageRender;



@Controller
// direcionamiento del html debe contener el mismo nombre

@Secured("ROLE_ADMIN")
@RequestMapping("/Usuarios")
public class UsuariosController {

	//@Autowired
	//private BCryptPasswordEncoder encoder;
	
	@Autowired
	private JpaUserDetailsService  jpaService;
	
	@Autowired
	private IPerfilservice servicePerfiles;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Autowired 
	private IRoleService roleservice;
	
		
	
	@RequestMapping(value = "/CreateUser")
	public String crear(Map<String, Object> model) {
		
		//Usuario usuario = new Usuario();
	model.put("listRoles", roleservice.findAll());
	   model.put("usuario", new Usuario());
		
		//model.addAttribute("listRoles", roleservice.findAll());
		//Usuario eEmpleado = new Usuario();
		//model.addAttribute("Empleado", eEmpleado);
		
	//	model.addAttribute("usuario", new Usuario());
		return "Usuarios/CreateUser";
		
	}
	
	
	@RequestMapping(value ="/CreateUser", method = RequestMethod.POST)
	public String saveUser(Usuario usuario, Model model) {
		
		String tmpPass = usuario.getPassword();
		String encriptado = encoder.encode(tmpPass);
		usuario.setPassword(encriptado);
	servicePerfiles.save(usuario); 
	return "redirect:/Usuarios/ListUsuarios"; 
	}
	
	
	@RequestMapping(value = "/CreateUser/{id}")
	public String editarUsuarios(@PathVariable(value = "id") Long id, Map<String, Object>  model, RedirectAttributes flash) {
		
		Usuario usuario = null;
		
		if(id > 0) {
			usuario = servicePerfiles.findOne(id);
			if (usuario == null) {
				flash.addAttribute("error", "El Id no existe en la base de datos");
				return "redirect:/Usuarios/ListUsuarios";
			}
		} else {
			flash.addAttribute("error", "El Usuario no puede ser cero");
			return "redirect:/Usuarios/ListUsuarios"; 
			
		}
		
		
		model.put("listRoles", roleservice.findAll());
		model.put("usuario", usuario);
		
		
		
		return "Usuarios/CreateUser";
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		servicePerfiles.delete(id);
		return "redirect:/listar";
	}
	
	/**
	 * Método para activar un usuario
	 */
	@GetMapping("/unlock/{id}")
	public String activar(@PathVariable("id") long idUsuario, RedirectAttributes attributes) {
		servicePerfiles.activar(idUsuario);
		attributes.addFlashAttribute("msg", "El usuario fue activado y ahora tiene acceso al sistema");
		return "redirect:/listar";
	}
	
	/**
	 * Método para bloquear un usuario
	 */
	@GetMapping("/lock/{id}")
	public String bloquear(@PathVariable("id") long idUsuario, RedirectAttributes attributes) {
		servicePerfiles.bloquear(idUsuario);
		attributes.addFlashAttribute("msg", "El usuario fue bloqueado y ahora no tiene acceso al sistema");
		return "redirect:/listar";
	}
	
	
	

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/ListUsuarios", method = RequestMethod.GET)
		public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model ) {

			Pageable pageRequest = PageRequest.of(page, 3);

			Page<Usuario> usuarios =jpaService.findAll(pageRequest);

			PageRender<Usuario> pageRender = new PageRender<Usuario>("/Usuarios/ListUsuarios", usuarios);
			model.addAttribute("titulo", "Listado de Usuarios");
			model.addAttribute("usuarios", usuarios);
			model.addAttribute("page", pageRender);
		
		
		// compocision del  mapping y del GET
		return "Usuarios/ListUsuarios";
	}
	
	
	

	
	
	
	

	
	
}
