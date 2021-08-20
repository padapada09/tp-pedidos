package nube.tp.pedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nube.tp.pedidos.domains.Obra;

public interface ObraRepository extends JpaRepository<Obra, Integer> {
	
	@Query(
		value = "select obra.* from obra where obra.tipo = ?1 ;",
		nativeQuery = true
	)
	List<Obra> findByTipoObra(String tipoObra);

	@Query(
		value = "select obra.* from obra left join cliente on obra.cliente_id = cliente.id where cliente.id = ?1 ;",
		nativeQuery = true
	)
	List<Obra> findByClienteId(Integer clienteId);
}
