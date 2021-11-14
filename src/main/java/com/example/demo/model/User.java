package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "tb_user")
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_seq;
	private String id;
	private String password;
	private String user_role;
	private String email;
	
	private String provider;
	private String providerId;
	
	@Builder
	public User(String id, String user_role, String email, String provider, String providerId) {
		super();
		this.id = id;
		this.user_role = user_role;
		this.email = email;
		this.provider = provider;
		this.providerId = providerId;
	}
	
	
	
}
