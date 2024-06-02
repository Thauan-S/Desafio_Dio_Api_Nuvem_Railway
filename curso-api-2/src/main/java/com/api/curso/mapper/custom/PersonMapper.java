package com.api.curso.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.api.curso.data.vo.v2.PersonVOV2;
import com.api.curso.model.Person;

@Service
public class PersonMapper {

	public PersonVOV2 converterEntidadeParaVo(Person person) {
		PersonVOV2 vo= new PersonVOV2();
		vo.setId(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setAddres(person.getAddres());
		vo.setBirtDay(new Date());
		vo.setGender(person.getGender());
		return vo;
				
	}
	public Person converterVoParaEntidade(PersonVOV2 person) {
		Person entity= new Person();
		entity.setId(person.getId());
		entity.setFirstName(person.getFirstName());
		//vo.setBirtDay(new Date());
		entity.setLastName(person.getLastName());
		entity.setAddres(person.getAddres());
		entity.setGender(person.getGender());
		return entity;
				
	}
}
