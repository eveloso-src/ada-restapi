package com.ada.restapi;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.ada.restapi.model.Producto;

public class ProductoTest {

	private static RestTemplate restTemplate;
	private static String productosURL = "http://localhost:8082/v1/productos";

	@BeforeClass
	public static void runBeforeAllTestMethods() {
		restTemplate = new RestTemplate();
	}

	@Test
	public void testGetMethod() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		ResponseEntity<Producto[]> responseEntity = restTemplate.getForEntity(productosURL,  Producto[].class);
		Producto[] objects = responseEntity.getBody();
		MediaType contentType = responseEntity.getHeaders().getContentType();
		HttpStatus statusCode = responseEntity.getStatusCode();
		System.out.println(statusCode);
		System.out.println(objects);
		System.out.println(contentType);
		assertTrue(objects.length > 0);
	}

	@Test
	public void testPost() {
		Producto prod = new Producto();
		prod.setEstado("B");
		prod.setNombre("Auricular");
		prod.setPrecio(89);

		ResponseEntity<Producto> result = restTemplate.postForEntity(productosURL, prod, Producto.class);
		System.out.println(result.getBody());
		Producto ptest = result.getBody();
		System.out.println(ptest.getNombre());
		System.out.println(result.getStatusCodeValue());
	}

	// Generar un test GET de producto con ID como parametro
	@Test
	public void testGetId() throws IOException {
		
		ResponseEntity<Producto> responseEntity = restTemplate.getForEntity(productosURL + "/46",  Producto.class);
		Producto objects = responseEntity.getBody();
		MediaType contentType = responseEntity.getHeaders().getContentType();
		HttpStatus statusCode = responseEntity.getStatusCode();
		System.out.println(statusCode);
		System.out.println(objects);
		System.out.println(objects.getNombre());
		System.out.println(contentType);
		assertEquals("Auricular", objects.getNombre());
	}

	@Test
	public void testGetIdBadRequest() throws IOException {

		try {
			ResponseEntity<Producto> responseEntity = restTemplate.getForEntity(productosURL + "/1",  Producto.class);
			
			HttpStatus statusCode = responseEntity.getStatusCode();
			System.out.println(statusCode);
			
			assertEquals(HttpStatus.BAD_REQUEST, statusCode);
		}
		catch(Exception e) {
			assertTrue(true);
		}
	}

	// Generar test para -> @GetMapping(path = "/productos/nombre")
	
}
