package com.api.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.curso.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
