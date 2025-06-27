package br.com.sdvweb.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sdvweb.backend.DTO.PostagemDTO;
import br.com.sdvweb.backend.entity.Postagem;
import br.com.sdvweb.backend.entity.User;
import br.com.sdvweb.backend.repository.PostagemRepository;
import br.com.sdvweb.backend.repository.UserRepository;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private UserRepository userRepository;

	public List<PostagemDTO> listarTodos() {
		List<Postagem> postagens = postagemRepository.findAll();
		return postagens.stream().map(PostagemDTO::new).toList();
	}

	public void inserir(PostagemDTO postagemDTO) {
		User usuario = userRepository.findById(postagemDTO.getUsuarioId()).orElseThrow(
				() -> new RuntimeException("Usuário não encontrado com ID: " + postagemDTO.getUsuarioId()));

		Postagem postagem = new Postagem(postagemDTO);
		postagem.setUsuario(usuario); // 👈 ESSENCIAL

		postagemRepository.save(postagem);
	}

	public PostagemDTO alterar(PostagemDTO postagemDTO) {
		User usuario = userRepository.findById(postagemDTO.getUsuarioId()).orElseThrow(
				() -> new RuntimeException("Usuário não encontrado com ID: " + postagemDTO.getUsuarioId()));

		Postagem postagem = new Postagem(postagemDTO);
		postagem.setUsuario(usuario);

		return new PostagemDTO(postagemRepository.save(postagem));
	}

	public void excluir(Long id) {
		Postagem postagem = postagemRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Postagem não encontrada com ID: " + id));

		postagemRepository.delete(postagem);
	}
}
