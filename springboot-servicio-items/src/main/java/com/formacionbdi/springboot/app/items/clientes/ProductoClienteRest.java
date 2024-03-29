package com.formacionbdi.springboot.app.items.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formacionbdi.springboot.app.items.model.Producto;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {

	@GetMapping("/listar")
	public List<Producto> listar();
	
	@GetMapping("/ver/{id}")
	public ResponseEntity<Producto> detalle (@PathVariable Long id);
	
}
