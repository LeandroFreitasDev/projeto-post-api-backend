package br.com.sdvweb.backend.entity;


import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.BeanUtils;

import br.com.sdvweb.backend.DTO.PostagemDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	private byte[] imagem;
	private String evento;
	private LocalDate data;
	private LocalTime hora;
	private String local;
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private User usuario;

	public Postagem() {}

	public Postagem(PostagemDTO dto) {
		BeanUtils.copyProperties(dto, this);
		// IMPORTANTE: o campo "usuario" deve ser setado manualmente depois
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
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

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	
	
}