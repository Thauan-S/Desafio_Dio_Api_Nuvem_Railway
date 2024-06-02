package com.api.curso.services;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.curso.controller.LivroController;
import com.api.curso.controller.PersonController;
import com.api.curso.data.vo.v1.LivroVO;
import com.api.curso.exceptions.RequiredObjectsIsNullException;
import com.api.curso.exceptions.ResourceNotFoundException;
import com.api.curso.mapper.Mapper;
import com.api.curso.model.Livro;
import com.api.curso.repositories.LivroRepository;

@Service
public class LivroServices {
	
	private Logger logger=Logger.getLogger(LivroServices.class.getName());
	
	@Autowired
LivroRepository livroRepository;
	
	

	
	
	public List<LivroVO> findAll(){
		logger.info("Finding all people!");
		var livros= Mapper.parseListObjects(livroRepository.findAll(), LivroVO.class);
		livros
		.stream()
		.forEach(i-> {
			try {
				i.add(linkTo(methodOn(PersonController.class).findById(i.getKey())).withSelfRel());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		});
		return livros;
	}
	public LivroVO FindById(Long  id) throws Exception {
		logger.info("Finding one livro!");
		var entity= livroRepository.findById(id)
		.orElseThrow(()-> new ResourceNotFoundException("nenum registro encontrado para este id") );
		var vo = Mapper.parseObject(entity, LivroVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	
	
	public LivroVO create( LivroVO livro) throws Exception {
		if(livro == null) throw  new RequiredObjectsIsNullException();
		logger.info("Creating one livro!");
		var entity=  Mapper.parseObject(livro, Livro.class);
		var vo= Mapper.parseObject(livroRepository.save(entity), LivroVO.class);
		vo.add(linkTo(methodOn(LivroController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
//	public LivroVOV2 createV2( LivroVOV2 livro) {
//		logger.info("Criando um cliente com a versão 2");
//		var entity=  mapper.converterVoParaEntidade(livro);
//		var vo= mapper.converterEntidadeParaVo(livroRepository.save(entity));
//		vo.add(linkTo(methodOn(PersonController.class).FindById(id)).withSelfRel());
//		return vo;
//	}
	
	public LivroVO update( LivroVO oldPerson) throws Exception {
		if(oldPerson == null) throw  new RequiredObjectsIsNullException();
		logger.info("Updating one livro!");
		var entity=livroRepository.findById(oldPerson.getKey())
				.orElseThrow(()-> new ResourceNotFoundException("nenhuma pessoa encontrada com esse id"+oldPerson.getKey()));
		entity.setAutor(oldPerson.getAutor());
		entity.setId(oldPerson.getKey());
		entity.setDataLancamento(oldPerson.getDataLancamento());
		entity.setPreco(oldPerson.getPreco());
		entity.setTitulo(oldPerson.getTitulo());
		var vo= Mapper.parseObject(livroRepository.save(entity), LivroVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	public void delete(Long id) {
		logger.info("Deleting one livro!");
		var entity =livroRepository.findById(id)
		.orElseThrow(()-> new ResourceNotFoundException("Pessoa não encontrada na base de dados"+id));
		livroRepository.delete(entity);
	}
}
