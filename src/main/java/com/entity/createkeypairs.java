package com.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wallet_details")
public class createkeypairs {

	@Id
	@Column(name = "Id")
	public String Id;

	@Column(name = "user_id")
	public String user_id;

	@Column(name = "address")
	public String address;

	@Column(name = "pubkey")
	public String pubkey;

	@Column(name = "privkey")
	public String privkey;

	@Column(name = "created_on")
	public Timestamp created_on;

	@Column(name = "signId")
	public String signId;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPubkey() {
		return pubkey;
	}

	public void setPubkey(String pubkey) {
		this.pubkey = pubkey;
	}

	public String getPrivkey() {
		return privkey;
	}

	public void setPrivkey(String privkey) {
		this.privkey = privkey;
	}

	public Timestamp getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Timestamp created_on) {
		this.created_on = created_on;
	}

	public String getSignId() {
		return signId;
	}

	public void setSignId(String signId) {
		this.signId = signId;
	}

	

			
	
}
