package nube.tp.pedidos.domains;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "cliente")
public class Cliente {
	@Id()
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer id;

	@Column()
	public String razonSocial;

	@Column()
	public String cuit;

	@Column()
	public String mail;

	@Column()
	public Float maxCuentaCorriente;

	@Column()
	public Boolean habilitadoOnline;

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "cliente_id", nullable = false)
	public List<Obra> obras;

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "cliente_id", nullable = false)
	public List<Pago> pagos;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "usuario_id", nullable = false)
	public Usuario usuario;
}
