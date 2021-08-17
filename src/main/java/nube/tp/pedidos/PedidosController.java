package nube.tp.pedidos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nube.tp.pedidos.domains.DetallePedido;
import nube.tp.pedidos.domains.EstadoPedido;
import nube.tp.pedidos.domains.Pedido;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

	@Autowired
	PedidosService pedidosService;

    @PostMapping()
    public ResponseEntity<Pedido> add(@RequestBody Pedido pedido) throws ControllerException {
		if (pedido.id != null) throw new ControllerException("Si querés crear un pedido nuevo, no podes definir el id.");
		if (pedido.detalles == null) throw new ControllerException("Para crear un pedido tenés que definir un listado de detalles.");
		if (pedido.detalles.size() == 0) throw new ControllerException("El listado de detalles no puede estar vacío.");
		if (pedido.obra == null) throw new ControllerException("Para crear un pedido tenés que definir una obra a la cual pertenece.");
		if (pedido.estado != EstadoPedido.NUEVO) throw new ControllerException("No podes especificar un estado al crear un pedido.");
        return new ResponseEntity<>(pedidosService.add(pedido), HttpStatus.OK);
    }

	@GetMapping()
    public ResponseEntity<List<Pedido>> getPedidos() {
        return new ResponseEntity<>(pedidosService.get(), HttpStatus.OK);
    }

	@PutMapping("/update")
    public  ResponseEntity<Pedido> update(@RequestBody Pedido pedido) throws ControllerException {
		if (pedido.id == null) throw new ControllerException("Tenés que definir un id para poder modificar el pedido.");
        return new ResponseEntity<>(pedidosService.update(pedido), HttpStatus.OK);
    }

	@DeleteMapping("/{pedidoId}")
    public ResponseEntity<String> delete(@PathVariable Integer pedidoId) throws ControllerException {
        return new ResponseEntity<>(pedidosService.delete(pedidoId), HttpStatus.OK);
    }

	@PostMapping("/{pedidoId}/detalle")
    public ResponseEntity<Pedido> addDetalle(@RequestBody DetallePedido detalle, @PathVariable Integer pedidoId) throws ControllerException {
        return new ResponseEntity<>(pedidosService.addDetalle(detalle,pedidoId), HttpStatus.OK);
    }

	@DeleteMapping("/{detalleId}/detalle")
    public ResponseEntity<String> deleteDetalle(@PathVariable Integer detalleId) throws ControllerException {
        return new ResponseEntity<>(pedidosService.deleteDetalle(detalleId),HttpStatus.OK);
    }
}