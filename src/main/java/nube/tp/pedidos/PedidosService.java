package nube.tp.pedidos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nube.tp.pedidos.domains.Cliente;
import nube.tp.pedidos.domains.DetalleDTO;
import nube.tp.pedidos.domains.DetallePedido;
import nube.tp.pedidos.domains.Material;
import nube.tp.pedidos.domains.Obra;
import nube.tp.pedidos.domains.Pedido;
import nube.tp.pedidos.domains.PedidoDTO;
import nube.tp.pedidos.repositories.ClienteRepository;
import nube.tp.pedidos.repositories.DetallePedidoRepository;
import nube.tp.pedidos.repositories.MaterialRepository;
import nube.tp.pedidos.repositories.ObraRepository;
import nube.tp.pedidos.repositories.PedidoRepository;

@Service
public class PedidosService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	DetallePedidoRepository detallePedidoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	MaterialRepository materialRepository;

	@Autowired
	ObraRepository obraRepository;

	public Pedido add(PedidoDTO pedidoDTO) {
		Obra obra = obraRepository.findById(pedidoDTO.obraId).get();
		Pedido nuevoPedido = new Pedido();
		nuevoPedido.obra = obra;
		Pedido savedPedido = pedidoRepository.save(nuevoPedido);
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

	public Pedido addDetalle(DetalleDTO detalleDTO, Integer pedidoId) throws ControllerException {
		Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);
		Optional<Material> optionalMaterial = materialRepository.findById(detalleDTO.materialId);
		if (optionalPedido.isPresent() && optionalMaterial.isPresent()) {
			Pedido pedido = optionalPedido.get();
			Material material = optionalMaterial.get();
			if (material.stockActual >= detalleDTO.cantidad && material.stockMinimo <= material.stockActual - detalleDTO.cantidad) {
				DetallePedido nuevoDetalle = new DetallePedido();
				material.stockActual = material.stockActual - detalleDTO.cantidad;
				nuevoDetalle.material = material;
				nuevoDetalle.cantidad = detalleDTO.cantidad;
				pedido.detalles.add(nuevoDetalle);
				pedidoRepository.save(pedido);
				return pedido;
			} else {
				throw new ControllerException("Parece que no hay stock suficiente.");	
			}
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
