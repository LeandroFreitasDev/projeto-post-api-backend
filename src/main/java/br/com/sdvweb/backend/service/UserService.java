package br.com.sdvweb.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sdvweb.backend.DTO.UserDTO;
import br.com.sdvweb.backend.entity.User;
import br.com.sdvweb.backend.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	

	public List<UserDTO> listarTodos() {
		List<User> user = userRepository.findAll();
		return user.stream().map(UserDTO::new).toList();
	}

	public void inserir(UserDTO usuario) {
		User user = new User(usuario);
		user.setSenha(passwordEncoder.encode(user.getSenha()));
		userRepository.save(user);
	}

	public UserDTO alterar(UserDTO usuario) {
		User user = new User(usuario);
		return new UserDTO(userRepository.save(user));
	}

	public void excluir(Long id) {
		User user = userRepository.findById(id).get();
		userRepository.delete(user);
	}

}
