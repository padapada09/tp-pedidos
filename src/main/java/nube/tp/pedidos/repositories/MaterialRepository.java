package nube.tp.pedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nube.tp.pedidos.domains.Material;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
	List<Material> findByNombre(String nombre);
	List<Material> findByPrecio(Float precio);

	@Query(
		value = "select material.* from material where stock_actual >= ?1 and stock_actual <= ?2 ;",
		nativeQuery = true
	)
	List<Material> findByStockRange(Float minimo, Float maximo);
}
