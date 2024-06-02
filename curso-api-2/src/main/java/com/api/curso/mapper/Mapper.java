package com.api.curso.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.api.curso.data.vo.v1.PersonVO;
import com.api.curso.model.Person;

//dozer foi descontinuado , recomenda-se usar o model mapper
public class Mapper {
	
	private static ModelMapper mapper= new ModelMapper();

	static {
		mapper.createTypeMap(Person.class,
				PersonVO.class)
		.addMapping(Person::getId, PersonVO::setKey);
		mapper.createTypeMap(PersonVO.class,
				Person.class)
		.addMapping(PersonVO::getKey, Person::setId);
	
	}
	public static <O,D> D parseObject (O origem,Class<D> destino ) {
		return mapper.map(origem, destino);
	}
	
	public static <O,D> List<D> parseListObjects (List<O>origem,Class<D> destino ) {
		List<D> objetosDeDestino=new ArrayList<D>();
		for (O o : origem) {
			objetosDeDestino.add(mapper.map(o, destino));
		}
		return objetosDeDestino;
	}
}
