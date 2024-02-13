package com.joalvarez.keycloakauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

	@GetMapping("admin")
	public String helloAdmin() {
		return "Hola Spring Boot with KeyCloak | ADMIN";
	}

	@GetMapping("user")
	public String helloUser() {
		return "Hola Spring Boot with KeyCloak | USER";
	}
}
