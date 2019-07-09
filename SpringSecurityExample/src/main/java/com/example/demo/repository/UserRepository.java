package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("from User u where u.emailId =:email and u.password =:password")
	User login(@Param("email") String email,@Param("password")String password);
	
	//User FindByEmail(String email);
	@Query("from User u where u.emailId =:emailId")
	User findByEmail(@Param("emailId") String emailId);
	
	@Query("from User u where u.id =:uuid")
	User findById(@Param("uuid") String uuid);
}
