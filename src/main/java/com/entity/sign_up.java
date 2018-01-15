package com.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class sign_up {

	@Id
	@Column(name = "Id")
	public String Id;

	@Column(name = "username")
	public String username;

	@Column(name = "password")
	public String password;

	@Column(name = "created_on")
	public Timestamp created_on;

	@Column(name = "address")
	public String address;

	@Column(name = "role")
	public int role;

}
