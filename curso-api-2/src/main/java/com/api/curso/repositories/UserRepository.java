package com.api.curso.repositories;

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.api.curso.model.User;
//
//
//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//	//u é apenas um alias , User é o nome da tabela, deve ser exatamente assim , SELECT maiusculo
//	@Query("SELECT u FROM User u WHERE u.userName =:userName")
//	User findByUsername(@Param("userName") String userName);
//}