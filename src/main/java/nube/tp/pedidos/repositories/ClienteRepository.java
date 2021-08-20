package nube.tp.pedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nube.tp.pedidos.domains.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	List<Cliente> findByCuit(String cuit);
	List<Cliente> findByRazonSocial(String razonSocial);
	
	@Query(
		value = "select cliente.* from cliente left join usuario on cliente.usuario_id = usuario.id where usuario.username = ?1 ;",
		nativeQuery = true
	)
	List<Cliente> findByUsername(String username);
	
	@Query(
		value = "select cliente.* from cliente left join obra on obra.cliente_id = cliente.id where obra.id = ?1 ;", 
		nativeQuery = true
	)
	List<Cliente> findByObraId(Integer obraId);
}
