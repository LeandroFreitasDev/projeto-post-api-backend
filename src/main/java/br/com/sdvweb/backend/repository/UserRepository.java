package br.com.sdvweb.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sdvweb.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional <User> findByEmail(String email);

}
