package com.blockchain;

import java.util.UUID;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class getKeyPair {

	Web3j web3 = Web3j.build(new HttpService());

	public Credentials getCredentials() {

		byte[] reci = Hash.sha3(UUID.randomUUID().toString().getBytes());
		ECKeyPair keyPair = ECKeyPair.create(reci);
		Credentials creds = Credentials.create(keyPair);
	
		return creds;
	}

	/*
	 * public Credentials getCredentials() { return creds; }
	 */

}
