package br.com.sdvweb.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sdvweb.backend.DTO.PostagemDTO;
import br.com.sdvweb.backend.service.PostagemService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/postagem")
public class PostagemController {

	@Autowired
	private PostagemService postagemService;

	@GetMapping
	public List<PostagemDTO> listarTodos() {
		return postagemService.listarTodos();
	}

	@PostMapping
	public void inserir(@Valid @RequestBody PostagemDTO post) {
		postagemService.inserir(post);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostagemDTO> alterar(@PathVariable Long id, @Valid @RequestBody PostagemDTO post) {
		post.setId(id);
		PostagemDTO atualizado = postagemService.alterar(post);
		return ResponseEntity.ok(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
		postagemService.excluir(id);
		return ResponseEntity.ok().build();
	}

}
