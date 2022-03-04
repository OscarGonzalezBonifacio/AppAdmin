package com.app.admin.models.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.Table;



@Entity
@Table(name = "users")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	
	@Column(length = 30, unique= true )
	private String username;
	
	@Column(length = 30 )
	private String area;
	
	@Column(length = 30 )
	private String correo;
	

	@Column(length = 60)
	private String password;

	private Boolean enabled;
	

	@ManyToMany(fetch=FetchType.EAGER) // Esto es para que cuando se haga una busqueda de un usuario automaticamente se recupere en la misma consulta todos los perfiles que tenga asociados dicho usuario
	@JoinTable(name="UsuarioRole", // tabla intermedia debe usarse el nombre de las clases
	// es neseario en el segundo para metrp el nombre la tabla de la anotacion table de la clase
			joinColumns=@JoinColumn(name="idusers"), // foreignKey en la tabla de UsuarioPerfil
			inverseJoinColumns=@JoinColumn(name="idauthorities")) // foreignKey en la tabla de UsuarioPerfil
	
	private List<Role> roles;
	
	public Usuario() {
		
	}
	
	

	public Usuario(Long id) {
		super();
		this.id = id;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	public String getArea() {
		return area;
	}



	public String getCorreo() {
		return correo;
	}



	public void setCorreo(String correo) {
		this.correo = correo;
	}



	public void setArea(String area) {
		this.area = area;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void agregar( Role tempPerfil) {
		if (roles == null) {
			roles = new LinkedList<Role>();
		}
		roles.add(tempPerfil);
	}

	
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", roles=" + roles + "]";
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
