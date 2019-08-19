package com.formacionbdi.springboot.app.items.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.items.clientes.ProductoClienteRest;
import com.formacionbdi.springboot.app.items.model.Item;

@Service
@Primary
public class itemServiceFeign implements ItemService {

	@Autowired
	private ProductoClienteRest clienteRest;
	
	@Override
	public List<Item> findAll() {
		return clienteRest.listar().stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(clienteRest.detalle(id).getBody(), cantidad);
	}

}
