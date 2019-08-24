package com.formacionbdi.springboot.app.items.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.items.model.Item;
import com.formacionbdi.springboot.app.items.model.Producto;
import com.formacionbdi.springboot.app.items.model.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping("/listar")
	public ResponseEntity<List<Item>> listar() {
		return ResponseEntity.ok(itemService.findAll());
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public ResponseEntity<Item> detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return ResponseEntity.ok(itemService.findById(id, cantidad));
	}
	
	public ResponseEntity<Item> metodoAlternativo(Long id, Integer cantidad) {
		Item item = new Item();
		Producto producto = new Producto();
		
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Camara Sony");
		producto.setPrecio(500.00);
		
		item.setProducto(producto);
		
		return ResponseEntity.ok(item);
	}
	
}
