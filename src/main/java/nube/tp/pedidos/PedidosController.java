package nube.tp.pedidos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nube.tp.pedidos.domains.DetalleDTO;
import nube.tp.pedidos.domains.DetallePedido;
import nube.tp.pedidos.domains.EstadoPedido;
import nube.tp.pedidos.domains.Pedido;
import nube.tp.pedidos.domains.PedidoDTO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

	@Autowired
	PedidosService pedidosService;

    @PostMapping()
    public ResponseEntity<Pedido> add(@RequestBody PedidoDTO pedidoDTO) throws ControllerException {
		if (pedidoDTO.obraId == null) throw new ControllerException("Para crear un pedido tenés que definir una obra a la cual pertenece.");
        return new ResponseEntity<>(pedidosService.add(pedidoDTO), HttpStatus.OK);
    }


	@GetMapping()
    public ResponseEntity<List<Pedido>> getPedidosByObraId(
		@RequestParam(required = false) Integer obraId, 
		@RequestParam(required = false) Integer clienteId, 
		@RequestParam(required = false) String clienteCUIT
	) {
		if (obraId != null) {
			return new ResponseEntity<>(pedidosService.getByObraId(obraId), HttpStatus.OK);
		} if (clienteId != null) {
			return new ResponseEntity<>(pedidosService.getByClienteId(clienteId), HttpStatus.OK);
		} else if (clienteCUIT != null) {
			return new ResponseEntity<>(pedidosService.getByClienteCUIT(clienteCUIT), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(pedidosService.get(), HttpStatus.OK);
		}
    }

	@GetMapping("/{pedidoId}")
    public ResponseEntity<Pedido> getPedidos(@PathVariable Integer pedidoId) {
        return new ResponseEntity<>(pedidosService.getById(pedidoId),HttpStatus.OK);
    }

	@PutMapping()
    public  ResponseEntity<Pedido> update(@RequestBody Pedido pedido) throws ControllerException {
		if (pedido.id == null) throw new ControllerException("Tenés que definir un id para poder modificar el pedido.");
        return new ResponseEntity<>(pedidosService.update(pedido), HttpStatus.OK);
    }

	@DeleteMapping("/{detalleId}/detalle")
    public ResponseEntity<String> deleteDetalle(@PathVariable Integer detalleId) throws ControllerException {
        return new ResponseEntity<>(pedidosService.deleteDetalle(detalleId),HttpStatus.OK);
    }

	@DeleteMapping("/{pedidoId}")
    public ResponseEntity<String> delete(@PathVariable Integer pedidoId) throws ControllerException {
        return new ResponseEntity<>(pedidosService.delete(pedidoId), HttpStatus.OK);
    }

	@PostMapping("/{pedidoId}/detalle")
    public ResponseEntity<Pedido> addDetalle(@RequestBody DetalleDTO detalle, @PathVariable Integer pedidoId) throws ControllerException {
        return new ResponseEntity<>(pedidosService.addDetalle(detalle,pedidoId), HttpStatus.OK);
    }

	@GetMapping("/{detalleId}/detalle")
    public ResponseEntity<DetallePedido> getDetalle(
		@PathVariable Integer detalleId
	) throws ControllerException {
        return new ResponseEntity<>(pedidosService.getDetalle(detalleId), HttpStatus.OK);
    }
}