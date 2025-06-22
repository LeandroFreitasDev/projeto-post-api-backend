package br.com.sdvweb.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sdvweb.backend.entity.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>{

}
