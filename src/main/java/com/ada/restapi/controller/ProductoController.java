package com.ada.restapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ada.restapi.form.LoginForm;
import com.ada.restapi.model.Producto;

@Controller
@RequestMapping(path = "/v1")
public class ProductoController {

	@GetMapping(path = "/test")
	public @ResponseBody String getDate(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name1, Integer numero) {
		Date date = new Date();
		return "Hola " + name1 + " Fecha: " + date;
	}

	@GetMapping(path = "/login")
	public @ResponseBody String login(@RequestParam String usuario, @RequestParam String contrasena) {

		return "Hola " + usuario + " Fecha: " + new Date();
	}

	@GetMapping(path = "/login/{name}/{age}")
	public @ResponseBody String getMessage(@PathVariable("name") String name, @PathVariable("age") String age) {

		String msg = String.format("%s is %s years old", name, age);

		return msg;
	}

	/**
	 * Generar metodos:
	 * 
	 * POST: /login body: user, pass Devuelve un numero aleatorio de 10 digitos
	 * GET: /welcome/{user} : @RequestParam user, aleatorio. Devuelve lista productos (mock)
	 * DELETE: /{user}: @RequestParam user, aleatorio. Devuelve "OK"
	 * 
	 * @param loginForm
	 * @return
	 */

	@PostMapping(path = "/login")
	public @ResponseBody int loginUser(@RequestBody LoginForm loginForm) {
		int aleatorio = 1234567890;
		return aleatorio;
	}

	@GetMapping(path = "/welcome/{user}")
	public @ResponseBody List<Producto> welcome(@RequestParam int aleatorio, @PathVariable String user) {
		List<Producto> prodList = new ArrayList<Producto>();

		Producto prod1 = new Producto(1, "Nokia 1100", 100);
		Producto prod2 = new Producto(2, "Motorola Startack", 200);
		prodList.add(prod1);
		prodList.add(prod2);

		return prodList;
	}
	
	

}
