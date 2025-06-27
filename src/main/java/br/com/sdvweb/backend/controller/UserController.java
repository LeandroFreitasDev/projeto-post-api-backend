package br.com.sdvweb.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sdvweb.backend.DTO.UserDTO;
import br.com.sdvweb.backend.service.UserService;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(value = "/usuario")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<UserDTO> listarTodos() {
		return userService.listarTodos();

	}

	@PostMapping
	public void inserir(@Valid @RequestBody UserDTO usuario) {
		userService.inserir(usuario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> alterar(@PathVariable Long id, @Valid @RequestBody UserDTO usuario) {
	    usuario.setId(id);
	    UserDTO atualizado = userService.alterar(usuario);
	    return ResponseEntity.ok(atualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
		userService.excluir(id);
		return ResponseEntity.ok().build();
	}

}
