package com.blockchain;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import com.entity.response;;;

public class checkNode {
	static Web3j web3 = Web3j.build(new HttpService("http://139.59.213.205:7007"));

	public response getConnection() {
		response res = new response();
		try {
			Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			System.out.println("Connect blockchain server..." + clientVersion);
			res.setMessage("Connect blockchain server..." + clientVersion);
			res.setResult(true);
			return (res);
		} catch (Exception ex) {
			System.out.println("Unable to connect blockchain server...");
			System.out.println(" Exception" + ex);
			res.setMessage("Unable to connect blockchain server...");
			res.setResult(false);
			return (res);
		}
	}

}
