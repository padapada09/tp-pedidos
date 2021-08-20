package nube.tp.pedidos.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer id;

	@Column()
	public String username;

	@Column()
	public String password;

	@Enumerated(EnumType.STRING)
	public TipoUsuario tipoUsuario = TipoUsuario.CLIENTE;
}
