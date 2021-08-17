package nube.tp.pedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nube.tp.pedidos.domains.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
	List<DetallePedido> findAll();
}
