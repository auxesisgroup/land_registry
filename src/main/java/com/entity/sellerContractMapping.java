package com.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sellerContractMapping")
public class sellerContractMapping {

	
	@Id
	@Column(name = "Id")
	public String Id;

	@Column(name = "sellerAddress")
	public String sellerAddress;

	@Column(name = "contractAddress")
	public String contractAddress;

	@Column(name = "createdOn")
	public BigInteger createdOn;

}
