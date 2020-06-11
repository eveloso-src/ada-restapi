package com.ada.restapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ada.restapi.form.LoginForm;
import com.ada.restapi.model.Producto;

@Controller
@RequestMapping(path = "/v1")
public class LoginController {

	Random ran = new Random(999999999);

	
	/**
	 * Generar metodos:
	 * 
	 * POST: /login body: user, pass Devuelve un numero aleatorio de 10 digitos GET:
	 * /welcome/{user} : @RequestParam user, aleatorio. Devuelve lista productos
	 * (mock) DELETE: /{user}: @RequestParam user, aleatorio. Devuelve "OK"
	 * 
	 * @param loginForm
	 * @return
	 */

	@PostMapping(path = "/login")
	public @ResponseBody int loginUser(@RequestBody LoginForm loginForm) {
		int aleatorio = 1234567890;

		// buscar el usuario en la base
		// si esta ok el usuario validar su contraseña
		if (loginForm.getUser() != null && !loginForm.getUser().equals("") && loginForm.getPass() != null
				&& !loginForm.getPass().equals("")) {

			System.out.println("validacion Ok");
			aleatorio = ran.nextInt();
			// registrar ese token con el usuario en tabla de usuarios activos
		}

		return aleatorio;
	}
	
	
	@PutMapping(path = "/product")
	public @ResponseBody Producto updateProduct(@RequestBody Producto prod) {
		return prod;
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
	
	@DeleteMapping(path="/delete/{userId}")
	public @ResponseBody String delete(@PathVariable Integer userId) {
		System.out.println("Borrar usuario " + userId);
		return "Usuario " + userId + " borrado";
	}

}
