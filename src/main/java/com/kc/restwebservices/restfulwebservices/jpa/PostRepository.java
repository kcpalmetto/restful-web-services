package com.kc.restwebservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kc.restwebservices.restfulwebservices.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
