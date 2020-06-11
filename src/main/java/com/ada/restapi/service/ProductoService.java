package com.ada.restapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.restapi.model.Producto;
import com.ada.restapi.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	ProductoRepository prodRepo;
	
	public void borrarProducto(int idProduct) {
		Optional<Producto> prod = prodRepo.findById(idProduct);
		if (prod.get()!=null) {
			prodRepo.delete(prod.get());
		}
	}

}
