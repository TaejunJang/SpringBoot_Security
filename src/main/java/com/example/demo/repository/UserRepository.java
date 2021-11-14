package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

//CRUD 함수를 JpaRepository가 들고있음
// @Repository라는 어노테이션이 없어도 ioc된다 JpaRepository를 상속했기때문에
public interface UserRepository extends JpaRepository<User, Long>{

	public User findUserById(String id);
	
}
