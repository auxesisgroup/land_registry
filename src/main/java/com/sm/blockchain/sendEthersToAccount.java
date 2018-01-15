package com.sm.blockchain;

import java.math.BigDecimal;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert.Unit;

public class sendEthersToAccount {

	static Web3j web3 = Web3j.build(new HttpService());

	public TransactionReceipt sendEther(Credentials credentials, String address) {
		TransactionReceipt transactionReceipt = new TransactionReceipt();
		try {
			transactionReceipt = Transfer.sendFunds(web3, credentials, address, BigDecimal.valueOf(1), Unit.ETHER)
					.send();
			return transactionReceipt;
		} catch (Exception e) {
			return transactionReceipt;
		}
	}

}
