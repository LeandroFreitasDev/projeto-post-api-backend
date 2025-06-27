package br.com.sdvweb.backend.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.sdvweb.backend.entity.Postagem;

public class PostagemDTO {

	private Long id;
	private String imagem;
	private String evento;
	private LocalDate data;
	private LocalTime hora;
	private String local;
	private String descricao;
	private Long usuarioId;

	public PostagemDTO() {
	}

	public PostagemDTO(Postagem postagem) {
		this.id = postagem.getId();
		this.imagem = postagem.getImagem();
		this.evento = postagem.getEvento();
		this.data = postagem.getData();
		this.hora = postagem.getHora();
		this.local = postagem.getLocal();
		this.descricao = postagem.getDescricao();
		this.usuarioId = postagem.getUsuario().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

}