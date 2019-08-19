package com.formacionbdi.springboot.app.items.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.items.model.Item;
import com.formacionbdi.springboot.app.items.model.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping("/listar")
	public ResponseEntity<List<Item>> listar() {
		return ResponseEntity.ok(itemService.findAll());
	}
	
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public ResponseEntity<Item> detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return ResponseEntity.ok(itemService.findById(id, cantidad));
	}
	
}
