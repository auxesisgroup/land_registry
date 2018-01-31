package com.entity;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "propertyDetails")
public class propertyDetails {

	@Id
	@Column(name = "Id")
	public String Id;

	@Column(name = "description")
	public String description;

	@Column(name = "fileHash")
	public String fileHash;

	@Column(name = "status")
	public BigInteger status;

	@Column(name = "buyerAddress")
	public String buyerAddress;

	@Column(name = "price")
	public BigInteger price;

	@Column(name = "sellerAddress")
	public String sellerAddress;

	@Column(name = "propertyAddress")
	public String propertyAddress;

	@Column(name = "brokerAddress")
	public String brokerAddress;

	@Column(name = "contractAddress")
	public String contractAddress;

	@Column(name = "createdOn")
	public Date createdOn;

	@Column(name = "confirmPrice")
	public BigInteger confirmPrice;

	@Column(name = "confirmBuyer")
	public String confirmBuyer;

}
