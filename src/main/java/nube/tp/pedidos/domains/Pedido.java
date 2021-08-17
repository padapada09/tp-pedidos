package nube.tp.pedidos.domains;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer id;

	@Column(columnDefinition = "DATE")
	public Instant fechaPedido;

	@OneToMany(mappedBy = "pedido", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	public List<DetallePedido> detalles;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	public EstadoPedido estado;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	public Obra obra;

	@Override
	public String toString() {
		return "Pedido [detalles=" + detalles + ", estado=" + estado + ", fechaPedido=" + fechaPedido + ", id=" + id
				+ ", obra=" + obra + "]";
	}
}