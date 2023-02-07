package br.caio303.users_auth_control.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.caio303.users_auth_control.dtos.LoginCredentialsDto;
import br.caio303.users_auth_control.dtos.LoginResponseDto;
import br.caio303.users_auth_control.dtos.UpdateCredentialsDto;
import br.caio303.users_auth_control.dtos.UserDto;
import br.caio303.users_auth_control.exceptions.StatusCodeException;
import br.caio303.users_auth_control.exceptions.TokenNotValidException;
import br.caio303.users_auth_control.models.UserModel;
import br.caio303.users_auth_control.services.UserService;
import br.caio303.users_auth_control.utils.HashUtils;
import br.caio303.users_auth_control.utils.JWTUtil;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuario")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Register a user in the database.")
	@PostMapping(path = "/cadastro")
	public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto) throws Exception {
		if (userService.existsByCpf(userDto.getCpf())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new Exception("User alredy exists"));
		}
		var userModel = new UserModel();
		BeanUtils.copyProperties(userDto, userModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(new UserModel(userDto)));
	}

	@ApiOperation(value = "Attempt to sign in, if successful returns JWT token.")
	@PostMapping(path = "/login")
	public ResponseEntity<Object> login(@RequestBody @Valid LoginCredentialsDto credentialsDto) {

		UserModel user = userService.findByCpf(credentialsDto.getCpf());
		String senhaRecebidaEncriptada = HashUtils.toSha256(credentialsDto.getSenha());

		if (!user.getSenha().equals(senhaRecebidaEncriptada)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Credentials");
		}
		
		LoginResponseDto responseModel = new LoginResponseDto(user);		
		responseModel.setToken("Bearer " + JWTUtil.generateToken(user.getCpf()));
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(responseModel);
	}
	
	@ApiOperation(value = "Validates request token, and returns a user by its cpf.")
	@GetMapping("/{cpf}")
	public ResponseEntity<Object> showUser(@PathVariable String cpf, @RequestHeader("Authentication") String authHeader)
			throws StatusCodeException {

		if (authHeader == null || !JWTUtil.isTokenValid(authHeader.substring(7)))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new IOException("Unauthorized request."));

		String token = authHeader.substring(7);
		UserModel user = userService.findByCpf(cpf);
		
		if(!JWTUtil.getSubject(token).equals(user.getCpf()))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new IOException("Unauthorized request."));
			
		return ResponseEntity.ok(user);
	}
	
	@ApiOperation(value = "Validates request token, and deletes a user by its cpf.")
	@DeleteMapping("/{cpf}")
	public ResponseEntity<Object> deleteUser(@PathVariable String cpf, @RequestHeader("Authentication") String authHeader) throws StatusCodeException {

		// TODO: Add interceptor for that
		if (authHeader == null || !JWTUtil.isTokenValid(authHeader.substring(7)))
			throw new TokenNotValidException();

		String token = authHeader.substring(7);
		UserModel user = userService.findByCpf(cpf);
		
		if(!JWTUtil.getSubject(token).equals(user.getCpf()))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new IOException("Unauthorized request."));
		
		userService.deleteByCpf(user.getCpf());
		
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@ApiOperation(value = "Validates request token, and returns a updated user by its cpf.")
	@PutMapping("/{cpf}")
	public ResponseEntity<Object> updateUser(
				@PathVariable String cpf, 
				@RequestHeader("Authentication") String authHeader,
				@RequestBody @Valid UpdateCredentialsDto updateCredentialsDto
			)
			throws Exception {
		
		UserModel user = userService.findByCpf(cpf);
		if(user == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new IOException("User not found."));
		
		String token = authHeader.substring(7);

		if (authHeader == null || !JWTUtil.isTokenValid(token))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new IOException("Unauthorized request."));
		
		if(!JWTUtil.getSubject(token).equals(user.getCpf()))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new IOException("Unauthorized request."));
		
		var updatedUser = new UserModel();
		BeanUtils.copyProperties(user, updatedUser);
				
		updatedUser.setNome(updateCredentialsDto.getNome());
		updatedUser.setEmail(updateCredentialsDto.getEmail());
		updatedUser.setDescricao(updateCredentialsDto.getBiografia());
		updatedUser.setSenha(updateCredentialsDto.getSenha());
		updatedUser.setDataNasc(updateCredentialsDto.getDataNasc());
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.save(updatedUser));
		
	}
}