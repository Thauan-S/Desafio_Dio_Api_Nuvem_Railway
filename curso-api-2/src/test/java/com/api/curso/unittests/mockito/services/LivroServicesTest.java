package com.api.curso.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.curso.data.vo.v1.LivroVO;
import com.api.curso.exceptions.RequiredObjectsIsNullException;
import com.api.curso.model.Livro;
import com.api.curso.repositories.LivroRepository;
import com.api.curso.services.LivroServices;
import com.api.unittests.mapper.mocks.MockLivro;

@TestInstance(Lifecycle.PER_CLASS) // definindo ciclo de vida por instancia de classe;
@ExtendWith(MockitoExtension.class) // adicionando a extensão do mockito
class LivroServicesTest {

	MockLivro input;

	@InjectMocks // injeta um mock para repository
	private LivroServices service;

	@Mock
	LivroRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockLivro();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<Livro> list = input.mockEntityList();
		// quando houver a chamada do fyndbyid , ele não vai ao banco
		// mas sim retornar um mock com esses valores e retornar a pessoa
		when(repository.findAll()).thenReturn(list);

		var livros = service.findAll();
		
		assertNotNull(livros);
		assertEquals(14,livros.size());
		
		var livroOne = livros.get(1);
		
		assertNotNull(livroOne);
		assertNotNull(livroOne.getKey());// não pode ser nulo
		assertNotNull(livroOne.getLinks());// não pode ser nulo
		
		assertTrue(livroOne.toString().contains("links: [</api/livro/v1/1>;rel=\"self\"]"));
		assertEquals("autor Teste1", livroOne.getAutor());
		assertEquals(200D, livroOne.getPreco());
		assertEquals("Titutlo teste1", livroOne.getTitulo());
		assertNotNull(livroOne.getDataLancamento());
		
		var personFour = livros.get(4);
		assertNotNull(personFour);
		assertNotNull(personFour.getKey());// não pode ser nulo
		assertNotNull(personFour.getLinks());// não pode ser nulo
		
		assertTrue(personFour.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
		assertEquals("autor Teste4", livroOne.getAutor());
		assertEquals(200D, livroOne.getPreco());
		assertEquals("Titutlo teste4", livroOne.getTitulo());
		assertEquals(2024-23-01, livroOne.getDataLancamento());

		var bookSeven = livros.get(7);
		
		assertNotNull(bookSeven);
		assertNotNull(bookSeven.getKey());
		assertNotNull(bookSeven.getLinks());
		
		assertTrue(bookSeven.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));
		assertEquals("autor Teste7", bookSeven.getAutor());
		assertEquals("Titutlo teste7", bookSeven.getTitulo());
		assertEquals(25D, bookSeven.getPreco());
		assertNotNull(bookSeven.getDataLancamento());
	}

	@Test
	void testFindById() throws Exception {
		Livro entity = input.mockEntity(1);
		entity.setId(1L);
		// quando houver a chamada do fyndbyid , ele não vai ao banco
		// mas sim retornar um mock com esses valores e retornar a pessoa
		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		var result = service.FindById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());// não pode ser nulo
		assertNotNull(result.getLinks());// não pode ser nulo
		
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("autor Teste1", result.getAutor());
		assertEquals(200D, result.getPreco());
		assertEquals("Titulo teste1", result.getTitulo());
		assertNotNull(result.getDataLancamento());
		
	}

	@Test
	void testCreate() throws Exception   {
		Livro entity = input.mockEntity(1); 
		entity.setId(1L);
		
		Livro persisted = entity;
		persisted.setId(1L);
		
		LivroVO vo= input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.save(any(Livro.class))).thenReturn(persisted);
		
		var result = service.create(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("autor Teste1", result.getAutor());
		assertEquals(200D, result.getPreco());
		assertEquals("Titutlo teste1", result.getTitulo());
		assertNotNull(result.getDataLancamento());
	
	}
	@Test
	void testCreateWithNullPerson() {
		
		Exception exception =assertThrows(RequiredObjectsIsNullException.class, ()-> {
			service.create(null);
		} );
		String expectedMessage="Não é permitido persistir um objeto nulo";
		String actualMessage=exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
		
		
	}

	@Test
	void testUpdate() throws Exception {
		Livro entity = input.mockEntity(1);// antes de chamar o repositorio
	
		Livro persisted = entity;// depois de chamar
		persisted.setId(1L);

		LivroVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());// não pode ser nulo
		assertNotNull(result.getLinks());// não pode ser nulo

		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("autor Teste1", result.getAutor());
		assertEquals(200D, result.getPreco());
		assertEquals("Titutlo teste1", result.getTitulo());
		assertNotNull(result.getDataLancamento());
		
	}
	@Test
	void testUpdateWithNullPerson() {
		Exception exception =assertThrows(RequiredObjectsIsNullException.class, ()-> {
			service.create(null);
		} );
		String expectedMessage="Não é permitido persistir um objeto nulo";
		String actualMessage=exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
		
		
	}
	@Test
	void testDelete() {
		Livro entity = input.mockEntity(1);
		entity.setId(1L);
		// quando houver a chamada do fyndbyid , ele não vai ao banco
		// mas sim vai retornar um mock com esses valores e retornar a pessoa
		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		 service.delete(1L);
		
	}

}
