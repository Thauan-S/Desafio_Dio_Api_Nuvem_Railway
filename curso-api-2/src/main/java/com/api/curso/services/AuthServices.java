package com.api.curso.services;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.api.curso.data.vo.v1.security.AccountCredentialsVO;
//import com.api.curso.data.vo.v1.security.TokenVO;
//import com.api.curso.repositories.UserRepository;
//import com.api.security.jwt.JwtTokenProvider;
//
//@Service
//public class AuthServices {
//
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	private JwtTokenProvider tokenProvider;
//	
//	@Autowired
//	private UserRepository repository;
//	
//	@SuppressWarnings("rawtypes")
//	public ResponseEntity signin(AccountCredentialsVO data) {
//		data.setPassword("a");
//		data.setUsername("u");
//		System.out.println(data.getPassword()+ data.getUsername());
//		try {
//			var username = data.getUsername();
//			var password = data.getPassword();
//			authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(username, password));
//			
//			var user = repository.findByUsername(username);
//			
//			var tokenResponse = new TokenVO();
//			if (user != null) {
//				tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
//			} else {
//				throw new UsernameNotFoundException("Username " + username + " not found!");
//			}
//			return ResponseEntity.ok(tokenResponse);
//		} catch (Exception e) {
//			throw new BadCredentialsException("Invalid username/password supplied!");
//		}
//	}
//	
//	@SuppressWarnings("rawtypes")
//	public ResponseEntity refreshToken(String username, String refreshToken) {
//		var user = repository.findByUsername(username);
//		
//		var tokenResponse = new TokenVO();
//		if (user != null) {
//			tokenResponse = tokenProvider.refreshToken(refreshToken);
//		} else {
//			throw new UsernameNotFoundException("Username " + username + " not found!");
//		}
//		return ResponseEntity.ok(tokenResponse);
//	}
//	
//	
//}
