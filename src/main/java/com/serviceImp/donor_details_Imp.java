package com.serviceImp;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;

import com.blockchain.masterConstractImp;
import com.daoInt.donor_details_daoInt;

import com.entity.createkeypairs;

import com.entity.propertyDetails;
import com.entity.response;
import com.entity.sellerContractMapping;
import com.entity.sign_up;

import com.serviceInt.donor_details_Int;

@Service
public class donor_details_Imp implements donor_details_Int {

	masterConstractImp contract = new masterConstractImp();

	@Autowired
	donor_details_daoInt _donor_details_daoInt;

	public sign_up login(sign_up sign_up) {
		return _donor_details_daoInt.login(sign_up);
	}

	@Override
	public response donorSignUp(sign_up sign_up) {
		response res = new response();
		try {
			sign_up user = new sign_up();
			user = _donor_details_daoInt.userById(sign_up.username);
			if (user.Id != null) {
				res.setMessage("username already exist");
				return res;
			}
			System.out.println("true:::::::::::::::::::::");
			String userId = UUID.randomUUID().toString();
			sign_up.Id = userId;
			System.out.println("username  " + sign_up.username);
			System.out.println("password  " + sign_up.password);
			String signId = UUID.randomUUID().toString();
			byte[] reci = Hash.sha3(signId.getBytes());
			ECKeyPair keyPair = ECKeyPair.create(reci);
			Credentials creds = Credentials.create(keyPair);
			createkeypairs keypair = new createkeypairs();
			keypair.Id = UUID.randomUUID().toString();
			keypair.user_id = userId;
			keypair.address = creds.getAddress();
			keypair.pubkey = creds.getEcKeyPair().getPublicKey().toString();
			keypair.privkey = creds.getEcKeyPair().getPrivateKey().toString();
			keypair.signId = signId;
			sign_up.address = creds.getAddress();
			_donor_details_daoInt.donorSignUp(sign_up, keypair);
			res.setResult("user signIn");
			return res;
		} catch (Exception e) {
			res.setMessage("Error");
			return res;
		}

	}

	// @Autowired
	// donor_details_daoInt _donor_details_daoInt;
	//

	@Override
	public response SetBroker(String id) {
		return contract.SetBroker(id);
	}

	@Override
	public List<sign_up> getBrokerList() {
		return _donor_details_daoInt.getBrokerList();
	}

	@Override
	public boolean addPropertyDetails(propertyDetails propertyDetails) {
		return _donor_details_daoInt.addPropertyDetails(propertyDetails);
	}

	@Override
	public createkeypairs getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<propertyDetails> getUserContactDetails(String address) {
		return _donor_details_daoInt.getUserContactDetails(address);
	}

	@Override
	public int addBrokerContractMapping(propertyDetails propertyDetails) {
		return _donor_details_daoInt.addBrokerContractMapping(propertyDetails);
	}

	@Override
	public List<propertyDetails> getBrokerContactDetails(String address) {
		return _donor_details_daoInt.getBrokerContactDetails(address);
	}

	@Override
	public List<sign_up> getSellerList() {
		return _donor_details_daoInt.getSellerList();
	}

	@Override
	public boolean addSellerToContract(sellerContractMapping sellerContractMapping) {
		return _donor_details_daoInt.addSellerToContract(sellerContractMapping);
	}

	@Override
	public List<propertyDetails> getBuyerContactDetails(String address) {
		return _donor_details_daoInt.getBuyerContactDetails(address);
	}

}