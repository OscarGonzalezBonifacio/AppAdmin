package com.app.admin.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.admin.models.entity.Usuario;


@Repository
public interface IRolePerfil extends PagingAndSortingRepository<Usuario, Long> {
	
	//@Query("select users.id, username, authority, enabled from authorities join usuario_role on authorities.id = usuario_role.idauthorities join users on usuario_role.idusers =  users.id")
   // List<Usuario>DatosPerfiles(@Param("id") int id);
	//@Query("select u.username from Usuario u inner join u.Role r where r  in:Role")

	
	public Usuario findByUsername(String username);
	
	


//@Query(nativeQuery = true, value = "select p.*, o.name as organisation_name from person p left join organisation o on p.organisation_guid = o.guid")
//List<Person> findAll();
//}
//Cambiar estatus a 0 (bloquear)
	@Modifying
	@Query("UPDATE Usuario u SET u.enabled=0 WHERE u.id = :paramIdUsuario")
	int lock(@Param("paramIdUsuario") long idUsuario);
	
	// Cambiar estatus a 1 (activar)
	@Modifying
	@Query("UPDATE Usuario u SET u.enabled=1 WHERE u.id = :paramIdUsuario")
	int unlock(@Param("paramIdUsuario") long idUsuario);


	


	
//public Page<Usuario> findAll(Pageable pageable);
	
	

}
