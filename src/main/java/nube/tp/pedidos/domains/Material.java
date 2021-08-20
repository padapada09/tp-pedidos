package nube.tp.pedidos.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "material")
public class Material {
	@Id()
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer id;

	@Column()
	public String descripcion;

	@Column()
	public Float precio;

	@Column()
	public String nombre;

	@Column()
	public Integer stockActual;

	@Column()
	public Integer stockMinimo;

	public String unidad;

	@Override
	public String toString() {
		return "Material [descripcion=" + descripcion + ", detalle=" + id + ", precio=" + precio + "]";
	}
}
