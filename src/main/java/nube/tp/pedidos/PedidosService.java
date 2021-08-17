package nube.tp.pedidos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nube.tp.pedidos.domains.DetallePedido;
import nube.tp.pedidos.domains.Pedido;
import nube.tp.pedidos.repositories.PedidoRepository;

@Service
public class PedidosService {

	@Autowired
	PedidoRepository pedidoRepository;

	public Pedido add(Pedido pedido) {
		pedido.detalles.forEach(detalle -> {
			detalle.pedido = pedido;
		}); 
		return pedidoRepository.save(pedido);
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

	public Pedido update(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	public List<Pedido> get() {
		return pedidoRepository.findAll();
	}
}
