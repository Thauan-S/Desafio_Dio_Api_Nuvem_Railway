package com.api.curso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenAPI() {

		return new OpenAPI().info(new Info().title("titulo da api").version("v1").description("Descrição da api")
				.termsOfService(" outro link de termos de serviço")
				.license(new License().name("Apache 2.0").url("url da minha licença")));
	}
}
