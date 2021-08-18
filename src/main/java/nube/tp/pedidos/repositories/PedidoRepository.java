package nube.tp.pedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nube.tp.pedidos.domains.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	@Query(value = "select pedido.* from pedido where pedido.obra_id = ?1 ;", nativeQuery = true)
	List<Pedido> findByObraId(Integer id);

	@Query(value = "select pedido.* from pedido left join obra on obra.id = pedido.obra_id left join cliente on obra.cliente_id = cliente.id where cliente.cuit = ?1 ;", nativeQuery = true)
	List<Pedido> findByClienteCUIT(String cuit);

	@Query(value = "select pedido.* from pedido left join obra on obra.id = pedido.obra_id where obra.cliente_id = ?1 ;", nativeQuery = true)
	List<Pedido> findByClienteId(Integer id);
}
