package com.kc.restwebservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "user_details")
public class User {

	protected User() {
		
	}
	@javax.persistence.Id 
	@GeneratedValue
	private Integer Id;
	
	@Size(min = 2,message = "Name should be atleast 2 characters")
	@Column
	private String name;
	
	@Past(message ="BirthDate cannot be in past")
	@Column
	private LocalDate birthDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Post> post;
	
	
	public User(Integer id, String name, LocalDate birthDate) {
		super();
		Id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "User [Id=" + Id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

}
