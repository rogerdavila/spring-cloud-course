package com.formacionbdi.springboot.app.productos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.productos.model.entity.Producto;
import com.formacionbdi.springboot.app.productos.model.service.IProductoService;

@RestController
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	@Value("${eureka.instance.instance-id}")
	private String port;
	
	@GetMapping("/listar")
	public ResponseEntity<List<Producto>> listar() {
		return ResponseEntity.ok(productoService.findAll().stream().map(producto -> {
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList()));
	}
	
	@GetMapping("/ver/{id}")
	public ResponseEntity<Producto> detalle (@PathVariable Long id) throws Exception{
		Producto producto = productoService.findById(id);
		producto.setPort(port);
		
		return ResponseEntity.ok(producto);
	}
}
