package nube.tp.pedidos.domains;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "pedido_id")
	public List<DetallePedido> detalles;

	@Enumerated(EnumType.STRING)
	public EstadoPedido estado = EstadoPedido.NUEVO;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	public Obra obra;

	@Override
	public String toString() {
		return "Pedido [detalles=" + detalles + ", estado=" + estado + ", fechaPedido=" + fechaPedido + ", id=" + id
				+ ", obra=" + obra + "]";
	}
}