package com.api.curso.services;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.curso.controller.PersonController;
import com.api.curso.data.vo.v1.PersonVO;
import com.api.curso.exceptions.RequiredObjectsIsNullException;
import com.api.curso.exceptions.ResourceNotFoundException;
import com.api.curso.mapper.Mapper;
import com.api.curso.mapper.custom.PersonMapper;
import com.api.curso.model.Person;
import com.api.curso.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger=Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository personRepository;
	
	
	
	@Autowired
	PersonMapper mapper;
	
	public List<PersonVO> findAll(){
		logger.info("Finding all people!");
		var persons= Mapper.parseListObjects(personRepository.findAll(), PersonVO.class);
		persons
		.stream()
		.forEach(i-> {
			try {
				i.add(linkTo(methodOn(PersonController.class).findById(i.getKey())).withSelfRel());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		});
		return persons;
	}
	public PersonVO FindById(Long  id) throws Exception {
		logger.info("Finding one person!");
		var entity= personRepository.findById(id)
		.orElseThrow(()-> new ResourceNotFoundException("nenum registro encontrado para este id") );
		var vo = Mapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	
	
	public PersonVO create( PersonVO person) throws Exception {
		if(person == null) throw  new RequiredObjectsIsNullException();
		logger.info("Creating one person!");
		var entity=  Mapper.parseObject(person, Person.class);
		var vo= Mapper.parseObject(personRepository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
//	public PersonVOV2 createV2( PersonVOV2 person) {
//		logger.info("Criando um cliente com a versão 2");
//		var entity=  mapper.converterVoParaEntidade(person);
//		var vo= mapper.converterEntidadeParaVo(personRepository.save(entity));
//		vo.add(linkTo(methodOn(PersonController.class).FindById(id)).withSelfRel());
//		return vo;
//	}
	
	public PersonVO update( PersonVO oldPerson) throws Exception {
		if(oldPerson == null) throw  new RequiredObjectsIsNullException();
		logger.info("Updating one person!");
		var entity=personRepository.findById(oldPerson.getKey())
				.orElseThrow(()-> new ResourceNotFoundException("nenhuma pessoa encontrada com esse id"+oldPerson.getKey()));
		entity.setFirstName(oldPerson.getFirstName());
		entity.setLastName(oldPerson.getLastName());
		entity.setAddres(oldPerson.getAddres());
		entity.setGender(oldPerson.getGender());
		var vo= Mapper.parseObject(personRepository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	public void delete(Long id) {
		logger.info("Deleting one person!");
		var entity =personRepository.findById(id)
		.orElseThrow(()-> new ResourceNotFoundException("Pessoa não encontrada na base de dados"+id));
		personRepository.delete(entity);
	}
}
