package nube.tp.pedidos.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "producto")
public class Producto {
	@Id()
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer id;

	@Column()
	public String descripcion;

	@Column()
	public Double precio;

	@Override
	public String toString() {
		return "Producto [descripcion=" + descripcion + ", detalle=" + id + ", precio=" + precio
				+ "]";
	}
}
