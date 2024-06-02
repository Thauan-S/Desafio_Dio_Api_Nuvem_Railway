package com.api.curso.controller;


//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.api.curso.data.vo.v1.security.AccountCredentialsVO;
//import com.api.curso.services.AuthServices;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//
//@Tag(name = "Authentication Endpoint")
//@RestController
//@RequestMapping("/auth")
//public class AuthenticateController {
//
//	@Autowired
//	AuthServices authServices;
//	
//	@SuppressWarnings("rawtypes")
//	@Operation(summary = "Authenticates a user and returns a token")
//	@PostMapping(value = "/signin")
//	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
//		if (checkIfParamsIsNotNull(data)) // movi o if para um método para ficar mais limpo
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
//		var token = authServices.signin(data);
//		if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
//		return token;
//	}
//	
//	@SuppressWarnings("rawtypes")
//	@Operation(summary = "Refresh token for authenticated user and returns a token")
//	@PutMapping(value = "/refresh/{username}")// recebe username no path
//	public ResponseEntity refreshToken(@PathVariable("username") String username,
//			@RequestHeader("Authorization") String refreshToken) {
//		if (checkIfParamsIsNotNull(username, refreshToken))
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
//		var token = authServices.refreshToken(username, refreshToken);
//		if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
//		return token;
//	}
//	//posso passar esse checkIfParamsIsNotNull para um serviço
//	private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
//		return refreshToken == null || refreshToken.isBlank() ||
//				username == null || username.isBlank();
//	}
//
//	private boolean checkIfParamsIsNotNull(AccountCredentialsVO data) {
//		return data == null || data.getUsername() == null || data.getUsername().isBlank()
//				 || data.getPassword() == null || data.getPassword().isBlank();
//	}
//}
//
