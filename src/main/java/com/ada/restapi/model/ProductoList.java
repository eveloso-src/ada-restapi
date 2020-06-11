package com.ada.restapi.model;

import java.util.ArrayList;
import java.util.List;

public class ProductoList {

	List<Producto> prods;

	public ProductoList() {
		prods = new ArrayList<>();
	}

	public List<Producto> getProds() {
		return prods;
	}

	public void setProds(List<Producto> prods) {
		this.prods = prods;
	}
	
	
}
