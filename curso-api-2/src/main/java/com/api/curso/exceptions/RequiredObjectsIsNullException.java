package com.api.curso.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)// retorna um código de erro do tipo bad request
public class RequiredObjectsIsNullException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public RequiredObjectsIsNullException(String ex) {//recebe uma mensagem no construtor
		super(ex);
	}
	
	public RequiredObjectsIsNullException() {//recebe uma mensagem no construtor
		super("Não é permitido persistir um objeto nulo !");
	}
	
}
