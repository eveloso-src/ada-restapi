package com.ada.restapi.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ada.restapi.form.DistribucionForm;
import com.ada.restapi.model.Distribucion;
import com.ada.restapi.model.Producto;
import com.ada.restapi.model.Proveedor;
import com.ada.restapi.repository.DistribucionRepository;
import com.ada.restapi.repository.ProductoRepository;
import com.ada.restapi.repository.ProveedorRepository;
import com.ada.restapi.service.ProductoService;
import com.google.common.collect.Lists;

import io.swagger.v3.oas.annotations.Operation;

//SWAGGER
@RestController
@RequestMapping(path = "/v1")
public class ProductoController {

	@Autowired
	ProductoRepository prodRepo;

	@Autowired
	ProveedorRepository provRepo;

	@Autowired
	DistribucionRepository disRepo;

	@Autowired
	ProductoService prodServ;

	Log log = LogFactory.getLog(ProductoController.class);

	@GetMapping(path = "/login/{name}/{age}")
	@Operation(summary = "Get users", description = "Get list of users")
	public @ResponseBody String getMessage(@PathVariable("name") String name, @PathVariable("age") String age) {

		String msg = String.format("%s is %s years old", name, age);

		return msg;
	}

	@PostMapping(path = "/productos")
	public ResponseEntity<Producto> guardarProducto(@RequestBody Producto prod) {
		log.info("guardarProducto " + prod);
		prodRepo.save(prod);
		return new ResponseEntity<>(prod, HttpStatus.CREATED);
	}

	@PostMapping(path = "/proveedor")
	public ResponseEntity<Producto> proveedor(@RequestBody Proveedor prove) {
		provRepo.save(prove);
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	@PostMapping(path = "/dist")
	public ResponseEntity<Producto> setDist(@RequestBody DistribucionForm distri) {
		Distribucion distribucion = new Distribucion();
		distribucion.setPrecio(distri.getPrecio());
		Producto prod = prodRepo.findById(distri.getProductoId()).get();
		distribucion.setProducto(prod);
		Proveedor proveedor = provRepo.findById(distri.getProveedorId()).get();
		distribucion.setProveedor(proveedor);
		this.disRepo.save(distribucion);
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	// generar listado de todos los productos
	@GetMapping(path = "/productos")
	public ResponseEntity<List<Producto>> listaProducto() {
		log.info("comienzo invocacion listar productos...");
		Iterable<Producto> lp = prodRepo.findAll();
		List<Producto> result = Lists.newArrayList(lp);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// generar busqueda de un producto por id
	@GetMapping(path = "/productos/{id}")
	public ResponseEntity<Producto> getProducto(@PathVariable("id") Integer id) {
		log.info("metodo: getProducto");
		Optional<Producto> produ = prodRepo.findById(id);
		if (Optional.empty().equals(produ)) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {

			Producto produRespuesta = produ.get();
			return new ResponseEntity<>(produRespuesta, HttpStatus.OK);
		}
	}

	// generar busqueda de un producto por nombre
	@GetMapping(path = "/productos/nombre")
	public ResponseEntity<List<Producto>> getProductoPorNombre(@RequestParam String nombre) {
		log.info("metodo: getProductoPorNombre " + nombre);
		List<Producto> produList = prodRepo.findByNombreStartingWith(nombre);
		return new ResponseEntity<>(produList, HttpStatus.OK);
	}

	@DeleteMapping(path = "/productos/{id}")
	public ResponseEntity<Object> deleteProducto(@PathVariable Integer id) {
		log.info("metodo: deleteProducto " + id);

		prodServ.borrarProducto(id);
		prodRepo.deleteById(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
