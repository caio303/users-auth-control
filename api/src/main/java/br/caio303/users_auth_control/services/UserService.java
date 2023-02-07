package br.caio303.users_auth_control.services;

import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.caio303.users_auth_control.exceptions.StatusCodeException;
import br.caio303.users_auth_control.exceptions.UserNotFoundException;
import br.caio303.users_auth_control.models.UserModel;
import br.caio303.users_auth_control.repositories.UserRepository;
import br.caio303.users_auth_control.utils.HashUtils;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public UserModel save(UserModel userModel) throws NoSuchAlgorithmException {
		userModel.setSenha(HashUtils.toSha256(userModel.getSenha()));		
		return userRepository.save(userModel);
	}
	
	public UserModel findByCpf(String cpf) {
		return userRepository.findByCpf(cpf);
	}
	
	public boolean existsByCpf(String cpf) {
		return userRepository.existsByCpf(cpf); 
	}
	
	@Transactional
	public void deleteByCpf(String cpf) throws StatusCodeException {
		var user = userRepository.findByCpf(cpf);
		if(user == null)
			throw new UserNotFoundException(cpf);
		
		userRepository.deleteById(user.getId());
	}

}
