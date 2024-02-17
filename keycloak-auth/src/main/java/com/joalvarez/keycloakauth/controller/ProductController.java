package com.joalvarez.keycloakauth.controller;

import com.joalvarez.keycloakauth.data.dto.ProductDTO;
import com.joalvarez.keycloakauth.service.interfaces.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

	private final IProductService service;

	public ProductController (IProductService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		return ResponseEntity.ok(this.service.findAll());
	}
}
