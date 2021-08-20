package nube.tp.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import nube.tp.pedidos.domains.Pago;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
}
