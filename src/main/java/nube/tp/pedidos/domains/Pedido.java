package nube.tp.pedidos.domains;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@OneToMany(cascade = CascadeType.ALL)
	public List<DetallePedido> detalles;

	@OneToOne(cascade = CascadeType.ALL)
	public DetallePedido estado;

	@OneToOne(cascade = CascadeType.ALL)
	public Obra obra;

	@Override
	public String toString() {
		return "Pedido [detalles=" + detalles + ", estado=" + estado + ", fechaPedido=" + fechaPedido + ", id=" + id
				+ ", obra=" + obra + "]";
	}
}