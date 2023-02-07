package br.caio303.RESTapi.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.caio303.RESTapi.models.UserModel;
import br.caio303.RESTapi.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public UserModel save(UserModel userModel) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] senhaEmBytes = userModel.getSenha().getBytes(StandardCharsets.UTF_8);
		byte[] senhaEncriptada = md.digest(senhaEmBytes);
		userModel.setSenha(new String(senhaEncriptada, StandardCharsets.UTF_8));
		
		return userRepository.save(userModel);
	}
	
	public UserModel findByCpf(String cpf) {
		return userRepository.findByCpf(cpf);
	}
	
	public boolean existsByCpf(String cpf) {
		return userRepository.existsByCpf(cpf); 
	}
	
	@Transactional
	public void deleteByCpf(String cpf) {
		var user = userRepository.findByCpf(cpf);
		if(user != null) {
			userRepository.deleteById(user.getId());
		}
	}

}
