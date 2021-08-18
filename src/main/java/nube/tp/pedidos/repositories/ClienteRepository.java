package nube.tp.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import nube.tp.pedidos.domains.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
}
