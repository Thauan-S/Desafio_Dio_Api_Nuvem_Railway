package com.api.curso.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
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

import com.api.curso.data.vo.v1.PersonVO;
import com.api.curso.exceptions.RequiredObjectsIsNullException;
import com.api.curso.model.Person;
import com.api.curso.repositories.PersonRepository;
import com.api.curso.services.PersonServices;
import com.api.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS) // definindo ciclo de vida por instancia de classe;
@ExtendWith(MockitoExtension.class) // adicionando a extensão do mockito
class PersonServicesTest {

	MockPerson input;

	@InjectMocks // injeta um mock para repository
	private PersonServices service;

	@Mock
	PersonRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<Person> list = input.mockEntityList();
		// quando houver a chamada do fyndbyid , ele não vai ao banco
		// mas sim retornar um mock com esses valores e retornar a pessoa
		when(repository.findAll()).thenReturn(list);

		var pessoas = service.findAll();
		
		assertNotNull(pessoas);
		assertEquals(14,pessoas.size());
		
		var personOne = pessoas.get(1);
		assertNotNull(personOne);
		assertNotNull(personOne.getKey());// não pode ser nulo
		assertNotNull(personOne.getLinks());// não pode ser nulo
		assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", personOne.getAddres());
		assertEquals("First Name Test1", personOne.getFirstName());
		assertEquals("Last Name Test1", personOne.getLastName());
		assertEquals("Female", personOne.getGender());
		
		var personFour = pessoas.get(4);
		assertNotNull(personFour);
		assertNotNull(personFour.getKey());// não pode ser nulo
		assertNotNull(personFour.getLinks());// não pode ser nulo
		
		assertTrue(personFour.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
		assertEquals("Addres Test4", personFour.getAddres());
		assertEquals("First Name Test4", personFour.getFirstName());
		assertEquals("Last Name Test4", personFour.getLastName());
		assertEquals("Male", personFour.getGender());
	}

	@Test
	void testFindById() throws Exception {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		// quando houver a chamada do fyndbyid , ele não vai ao banco
		// mas sim retornar um mock com esses valores e retornar a pessoa
		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		var result = service.FindById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());// não pode ser nulo
		assertNotNull(result.getLinks());// não pode ser nulo
		
		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddres());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
		
	}

	@Test
	void testCreate() throws Exception   {
		Person persisted = input.mockEntity(1); 
		persisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.save(any(Person.class))).thenReturn(persisted);
		
		var result = service.create(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddres());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	
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
		Person entity = input.mockEntity(1);// antes de chamar o repositorio
		entity.setId(1L);

		Person persisted = entity;// depois de chamar
		persisted.setId(1L);

		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());// não pode ser nulo
		assertNotNull(result.getLinks());// não pode ser nulo

		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddres());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
		
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
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		// quando houver a chamada do fyndbyid , ele não vai ao banco
		// mas sim vai retornar um mock com esses valores e retornar a pessoa
		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		 service.delete(1L);
		
	}

}
