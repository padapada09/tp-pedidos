package nube.tp.pedidos.domains;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "detalle_pedido")
public class DetallePedido {
	@Id()
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer id;

	@Column()
	public Integer cantidad;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	public Producto producto;

	@Override
	public String toString() {
		return "DetallePedido [cantidad=" + cantidad + ", id=" + id + ", producto=" + producto
				+ "]";
	}
}
