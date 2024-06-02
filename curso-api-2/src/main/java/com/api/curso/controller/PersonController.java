package com.api.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.curso.data.vo.v1.PersonVO;
//import com.api.curso.data.vo.v2.PersonVOV2;
import com.api.curso.services.PersonServices;
import com.api.curso.util.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


// caso o swagger reclame , tenho que adicionar o producer nas requisições
//@CrossOrigin()foi configurado de forma global no application properties  
@RequestMapping("/api/person/v1")
@RestController
@Tag(name="Pessoas" ,description = "Endpoint para gerenciamento de pessoas ") // customizando a geração da página do swagger no controller
public class PersonController {
	
	@Autowired
	private PersonServices service;
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML 
			})
	@Operation(summary = "Busca todas pessoas" ,description = "Busca todas pessoas" ,tags = {"Pessoas"},responses = {
			@ApiResponse(description = "Success" ,responseCode = "200" ,
					content = {
							@Content(
									mediaType = "application/json",
									array=@ArraySchema(schema = @Schema(implementation = PersonVO.class) ))
					}),
			@ApiResponse(description = "Bad Request" ,responseCode = "400" , content = @Content),
			@ApiResponse(description = "Unauthorized" ,responseCode = "401" , content = @Content),
			@ApiResponse(description = "Not Found" ,responseCode = "404" , content = @Content),
			@ApiResponse(description = "Internal Error" ,responseCode = "500" , content = @Content)
	})
	public List<PersonVO> findAll() {
		return service.findAll();
	}

	@GetMapping(value = "/{id}",
		produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
				MediaType.APPLICATION_YML  })
	@Operation(summary = "Busca uma pessoa" ,description = "Busca uma pessoa" ,tags = {"Pessoa"},responses = {
			@ApiResponse(description = "Success" ,responseCode = "200" ,
					content = @Content(schema=@Schema(implementation = PersonVO.class))
						
			),
			@ApiResponse(description = "No Content" ,responseCode = "204" , content = @Content),
			@ApiResponse(description = "Bad Request" ,responseCode = "400" , content = @Content),
			@ApiResponse(description = "Unauthorized" ,responseCode = "401" , content = @Content),
			@ApiResponse(description = "Not Found" ,responseCode = "404" , content = @Content),
			@ApiResponse(description = "Internal Error" ,responseCode = "500" , content = @Content)
	})
	public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {
		return service.FindById(id);
	}
	
	@PostMapping(
		consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
				MediaType.APPLICATION_YML  },
		produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
				MediaType.APPLICATION_YML  })
	@Operation(summary = "Cria uma pessoa" ,description = "Cria uma pessoa" ,tags = {"Pessoa"},responses = {
			@ApiResponse(description = "Success" ,responseCode = "200" ,
					content = @Content(schema=@Schema(implementation = PersonVO.class))
						
			),
			@ApiResponse(description = "Bad Request" ,responseCode = "400" , content = @Content),
			@ApiResponse(description = "Unauthorized" ,responseCode = "401" , content = @Content),
			@ApiResponse(description = "Internal Error" ,responseCode = "500" , content = @Content)
		}
	)
	public PersonVO create(@RequestBody PersonVO person) throws Exception {
		return service.create(person);
	}
	
	@PutMapping(
		consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
				MediaType.APPLICATION_YML  },
		produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
				MediaType.APPLICATION_YML  })
	@Operation(summary = "Atualiza uma pessoa" ,description = "atualiza uma pessoa" ,tags = {"Pessoa"},responses = {
			@ApiResponse(description = "Success" ,responseCode = "200" ,
					content = @Content(schema=@Schema(implementation = PersonVO.class))
						
			),
			@ApiResponse(description = "Bad Request" ,responseCode = "400" , content = @Content),
			@ApiResponse(description = "Unauthorized" ,responseCode = "401" , content = @Content),
			@ApiResponse(description = "Not Found" ,responseCode = "404" , content = @Content),
			@ApiResponse(description = "Internal Error" ,responseCode = "500" , content = @Content)
	})
	public PersonVO update(@RequestBody PersonVO person) throws Exception {
		return service.update(person);
	}
	
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deleta uma pessoa" ,description = "Deleta uma pessoa" ,tags = {"Pessoa"},responses = {
			@ApiResponse(description = "No content" ,responseCode = "204" ,content = @Content),
			@ApiResponse(description = "Bad Request" ,responseCode = "400" , content = @Content),
			@ApiResponse(description = "Unauthorized" ,responseCode = "401" , content = @Content),
			@ApiResponse(description = "Not Found" ,responseCode = "404" , content = @Content),
			@ApiResponse(description = "Internal Error" ,responseCode = "500" , content = @Content)
	})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
//	@RequestMapping("api/person/v2")//nova versão com o parametro birthday
//	@PostMapping
//	public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
//		return personServices.createV2(person);
//	}
	
	

}
