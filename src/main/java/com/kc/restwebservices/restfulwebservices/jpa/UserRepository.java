package com.kc.restwebservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kc.restwebservices.restfulwebservices.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
