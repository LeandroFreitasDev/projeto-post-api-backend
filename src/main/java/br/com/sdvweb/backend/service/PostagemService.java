package br.com.sdvweb.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sdvweb.backend.DTO.PostagemDTO;
import br.com.sdvweb.backend.entity.Postagem;
import br.com.sdvweb.backend.repository.PostagemRepository;

@Service
public class PostagemService {

	@Autowired
	public PostagemRepository postagemRepository;

	public List<PostagemDTO> listarTodos() {
		List<Postagem> postagem = postagemRepository.findAll();
		return postagem.stream().map(PostagemDTO::new).toList();
	}

	public void inserir(PostagemDTO post) {
		Postagem postagem = new Postagem(post);
		postagemRepository.save(postagem);
	}

	public PostagemDTO alterar(PostagemDTO post) {
		Postagem postagem = new Postagem(post);
		return new PostagemDTO(postagemRepository.save(postagem));
	}

	public void excluir(Long id) {
		Postagem postagem = postagemRepository.findById(id).get();
		postagemRepository.delete(postagem);
	}

}
