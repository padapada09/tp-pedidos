package nube.tp.pedidos.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "obra")
public class Obra {
	@Id()
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer id;

	@Column()
	public String descripcion;

	@Column()
	public Float latitud;

	@Column()
	public Float longitud;

	@Column()
	public String direccion;

	@Column()
	public Integer superficie;

	@Override
	public String toString() {
		return "Obra [descripcion=" + descripcion + ", id=" + id + "]";
	}
}
