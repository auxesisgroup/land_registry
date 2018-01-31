package com.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "buyerContractMapping")
public class buyerContractMapping {

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
