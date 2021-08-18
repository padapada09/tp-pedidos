package nube.tp.pedidos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nube.tp.pedidos.domains.Cliente;
import nube.tp.pedidos.domains.DetallePedido;
import nube.tp.pedidos.domains.Pedido;
import nube.tp.pedidos.repositories.ClienteRepository;
import nube.tp.pedidos.repositories.DetallePedidoRepository;
import nube.tp.pedidos.repositories.PedidoRepository;

@Service
public class PedidosService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	DetallePedidoRepository detallePedidoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	public Pedido add(Pedido pedido, Integer clienteId) {
		Cliente cliente = clienteRepository.findById(clienteId).get();
		Pedido savedPedido = pedidoRepository.save(pedido);
		cliente.obras.add(savedPedido.obra);
		clienteRepository.save(cliente);
		return savedPedido;
	}

	public Pedido update(Pedido pedido) {
		Pedido pedidoToUpdate = pedidoRepository.findById(pedido.id).get();
		if (pedido.fechaPedido != null) pedidoToUpdate.fechaPedido = pedido.fechaPedido;
		return pedidoToUpdate;
	}

	public String delete(Integer pedidoId) {
		pedidoRepository.deleteById(pedidoId);
		return "Deleted successfully";
	}

	public Pedido addDetalle(DetallePedido detalle, Integer pedidoId) throws ControllerException {
		Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);
		if (optionalPedido.isPresent()) {
			Pedido pedido = optionalPedido.get();
			pedido.detalles.add(detalle);
			pedidoRepository.save(pedido);
			return pedido;
		} else {
			throw new ControllerException("Parece que el pedido no existe.");
		}
	}

	public String deleteDetalle(Integer detalleId) throws ControllerException {
		detallePedidoRepository.deleteById(detalleId);
		return "Deleted successfully";
	}

	public DetallePedido getDetalle(Integer detalleId) throws ControllerException {
		return detallePedidoRepository.findById(detalleId).get();
	}

	public List<Pedido> get() {
		return pedidoRepository.findAll();
	}

	public Pedido getById(Integer pedidoId) {
		return pedidoRepository.findById(pedidoId).get();
	}

	public List<Pedido> getByObraId(Integer obraId) {
		return pedidoRepository.findByObraId(obraId);
	}

	public List<Pedido> getByClienteId(Integer clienteId) {
		return pedidoRepository.findByClienteId(clienteId);
	}

	public List<Pedido> getByClienteCUIT(String clienteCUIT) {
		return pedidoRepository.findByClienteCUIT(clienteCUIT);
	}
}
