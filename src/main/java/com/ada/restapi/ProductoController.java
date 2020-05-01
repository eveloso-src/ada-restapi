package com.ada.restapi;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/v1") 
public class ProductoController {

	@GetMapping(path="/test")
	public @ResponseBody String getDate(@RequestParam String nombre) {
		Date date = new Date();
		return "Hola " + nombre + "Fecha: " + date;
	}

	@GetMapping(path="login")
	public @ResponseBody String login(@RequestParam String usuario, @RequestParam String contrasena) {

		return "Hola " + usuario+ " Fecha: " + new Date();
	}

}
