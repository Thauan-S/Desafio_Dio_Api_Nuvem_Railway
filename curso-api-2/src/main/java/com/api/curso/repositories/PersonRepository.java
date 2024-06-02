package com.api.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.api.curso.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
