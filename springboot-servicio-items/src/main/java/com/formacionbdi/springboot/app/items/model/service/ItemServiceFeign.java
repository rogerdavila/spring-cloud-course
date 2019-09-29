package com.formacionbdi.springboot.app.items.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.items.clientes.ProductoClienteRest;
import com.formacionbdi.springboot.app.items.model.Item;

@Service
public class ItemServiceFeign implements ItemService {

	@Autowired
	private ProductoClienteRest clienteRestFeign;
	
	@Override
	public List<Item> findAll() {
		return clienteRestFeign.listar().stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(clienteRestFeign.detalle(id).getBody(), cantidad);
	}

}
