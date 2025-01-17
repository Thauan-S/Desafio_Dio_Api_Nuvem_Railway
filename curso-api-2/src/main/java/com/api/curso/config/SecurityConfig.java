//package com.api.curso.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.api.security.jwt.JwtTokenFilter;
//import com.api.security.jwt.JwtTokenProvider;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//	@Autowired
//	private JwtTokenProvider jwtTokenProvider;
//
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		Map<String, PasswordEncoder> encoders = new HashMap<>();
//
//		Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000,
//				SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
//		encoders.put("pbkdf2", pbkdf2Encoder);
//		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
//		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
//		return passwordEncoder;
//
//	}
//
//	@Bean
//	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//		return configuration.getAuthenticationManager();
//	}
//
//@Bean 
//SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
//	JwtTokenFilter customFilter =new JwtTokenFilter(jwtTokenProvider);
//	//@formater:off
//	return httpSecurity
//             .httpBasic(basic -> basic.disable())
//             .csrf(csrf -> csrf.disable())
//             .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
//             .sessionManagement(
//                     session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .authorizeHttpRequests(
//                     authorizeHttpRequests -> authorizeHttpRequests
//                             .requestMatchers(
//                                     "/auth/signin",
//                                     "/auth/refresh/**",
//                                     "/swagger-ui/**",
//                                     "/v3/api-docs/**"
//                             ).permitAll()
//                             .requestMatchers("/api/**").authenticated()
//                             .requestMatchers("/users").denyAll() // obrigatório , pois dependendo da versão do spring, ou se adicionar a dependencia spring data rest, iremos expor um endpoint users sem saber.
//             )
//             .cors(Customizer.withDefaults()) // reverificar caso de erro
//             .build();
//	//@formater:on 
//			
//}
//
//}
