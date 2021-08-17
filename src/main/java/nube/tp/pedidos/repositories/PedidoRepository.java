package nube.tp.pedidos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nube.tp.pedidos.domains.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	List<Pedido> findAll();
	Optional<Pedido> findById(Integer id);
}
