package com.daoInt;

import java.util.List;

import com.entity.buyerContractMapping;
import com.entity.createkeypairs;
import com.entity.propertyDetails;
import com.entity.response;
import com.entity.sign_up;

public interface donor_details_daoInt {

	public sign_up login(sign_up sign_up);

	public createkeypairs getById(String id);

	public boolean donorSignUp(sign_up sign_up, createkeypairs createkeypairs);

	public response getWalletDetails(String user_id);

	public List<sign_up> getBrokerList();

	public boolean addPropertyDetails(propertyDetails propertyDetails);

	public sign_up userById(String username);

	public List<propertyDetails> getUserContactDetails(String address);
	public int addBrokerContractMapping(propertyDetails propertyDetails) ;
	public List<propertyDetails> getBrokerContactDetails(String address);
	public List<sign_up> getSellerList();
	public boolean addBuyerToContract(buyerContractMapping buyerContractMapping);
	public List<propertyDetails> getBuyerContactDetails(String address) ;
}
