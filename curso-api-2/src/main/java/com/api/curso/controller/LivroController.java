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

import com.api.curso.data.vo.v1.LivroVO;
//import com.api.curso.data.vo.v2.LivroVOV2;
import com.api.curso.services.LivroServices;
import com.api.curso.util.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


// caso o swagger reclame , tenho que adicionar o producer nas requisições
@RequestMapping("/api/livro/v1")
@RestController
@Tag(name="Livros" ,description = "Endpoint para gerenciamento de livros ") // customizando a geração da página do swagger no controller
public class LivroController {
	
	@Autowired
	private LivroServices service;
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML 
			})
	@Operation(summary = "Busca todos livros" ,description = "Busca todos livros" ,tags = {"Livros"},responses = {
			@ApiResponse(description = "Success" ,responseCode = "200" ,
					content = {
							@Content(
									mediaType = "application/json",
									array=@ArraySchema(schema = @Schema(implementation = LivroVO.class) ))
					}),
			@ApiResponse(description = "Bad Request" ,responseCode = "400" , content = @Content),
			@ApiResponse(description = "Unauthorized" ,responseCode = "401" , content = @Content),
			@ApiResponse(description = "Not Found" ,responseCode = "404" , content = @Content),
			@ApiResponse(description = "Internal Error" ,responseCode = "500" , content = @Content)
	})
	public List<LivroVO> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}",
		produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
				MediaType.APPLICATION_YML  })
	@Operation(summary = "Busca um livro " ,description = "Busca um livro" ,tags = {"Livro"},responses = {
			@ApiResponse(description = "Success" ,responseCode = "200" ,
					content = @Content(schema=@Schema(implementation = LivroVO.class))
						
			),
			@ApiResponse(description = "No Content" ,responseCode = "204" , content = @Content),
			@ApiResponse(description = "Bad Request" ,responseCode = "400" , content = @Content),
			@ApiResponse(description = "Unauthorized" ,responseCode = "401" , content = @Content),
			@ApiResponse(description = "Not Found" ,responseCode = "404" , content = @Content),
			@ApiResponse(description = "Internal Error" ,responseCode = "500" , content = @Content)
	})
	public LivroVO findById(@PathVariable(value = "id") Long id) throws Exception {
		return service.FindById(id);
	}
	
	@PostMapping(
		consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
				MediaType.APPLICATION_YML  },
		produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
				MediaType.APPLICATION_YML  })
	@Operation(summary = "Cria um livro " ,description = "Cria um livro" ,tags = {"Livro"},responses = {
			@ApiResponse(description = "Success" ,responseCode = "200" ,
					content = @Content(schema=@Schema(implementation = LivroVO.class))
						
			),
			@ApiResponse(description = "Bad Request" ,responseCode = "400" , content = @Content),
			@ApiResponse(description = "Unauthorized" ,responseCode = "401" , content = @Content),
			@ApiResponse(description = "Internal Error" ,responseCode = "500" , content = @Content)
		}
	)
	public LivroVO create(@RequestBody LivroVO person) throws Exception {
		return service.create(person);
	}
	
	@PutMapping(
		consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
				MediaType.APPLICATION_YML  },
		produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
				MediaType.APPLICATION_YML  })
	@Operation(summary = "Atualiza um livro" ,description = "Atualiza um livr" ,tags = {"Livro"},responses = {
			@ApiResponse(description = "Success" ,responseCode = "200" ,
					content = @Content(schema=@Schema(implementation = LivroVO.class))
						
			),
			
			@ApiResponse(description = "Bad Request" ,responseCode = "400" , content = @Content),
			@ApiResponse(description = "Unauthorized" ,responseCode = "401" , content = @Content),
			@ApiResponse(description = "Not Found" ,responseCode = "404" , content = @Content),
			@ApiResponse(description = "Internal Error" ,responseCode = "500" , content = @Content)
	})
	public LivroVO update(@RequestBody LivroVO person) throws Exception {
		return service.update(person);
	}
	
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deleta um livro " ,description = "Deleta um livro" ,tags = {"Livro"},responses = {
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
//	public LivroVOV2 createV2(@RequestBody LivroVOV2 person) {
//		return personServices.createV2(person);
//	}
	
	

}
