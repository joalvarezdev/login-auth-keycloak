package com.joalvarez.keycloakauth.controller;

import com.joalvarez.keycloakauth.data.dto.LoginDTO;
import com.joalvarez.keycloakauth.data.dto.LoginResponseDTO;
import com.joalvarez.keycloakauth.data.dto.UserRepresentationDTO;
import com.joalvarez.keycloakauth.service.KeycloakService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/keycloak/users")
public class KeycloakController {

	private final KeycloakService service;

	public KeycloakController(KeycloakService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<UserRepresentationDTO>> findAll() {
		return ResponseEntity.ok(this.service.findAll());
	}

	@GetMapping("{username}")
	public ResponseEntity<UserRepresentationDTO> findById(@RequestParam String username) {
		return ResponseEntity.ok(this.service.findById(username));
	}

	@PostMapping
	public ResponseEntity<UserRepresentationDTO> create(@RequestBody UserRepresentationDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(dto));
	}

	@PutMapping
	public ResponseEntity<UserRepresentationDTO> update(@RequestBody UserRepresentationDTO dto) {
		return ResponseEntity.ok(this.service.update(dto));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@RequestParam String id) {
		this.service.delete(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO dto) {
		return ResponseEntity.ok(this.service.login(dto));
	}
}
