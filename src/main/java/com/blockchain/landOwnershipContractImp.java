package com.blockchain;

import java.math.BigInteger;
import java.util.UUID;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.utils.Numeric;

import com.entity.landRegistry;
import com.entity.response;
import com.sm.blockchain.getBalance;
import com.sm.blockchain.typeCasting;
import com.smartContract.LandOwnershipContract;

public class landOwnershipContractImp {

	static Web3j web3 = Web3j.build(new HttpService("http://139.59.213.205:7007"));
	getBalance _getBalance = new getBalance();
	String contractAddress = "0x31f0a83b07f127347592bc8060f62d7b5777c311";
	String senderPrivKey = "44816426bedb635d8f2367a47828bb37aa99c6a247264a76517f1fa8f6ec5139";
	Credentials creds = Credentials.create(senderPrivKey);
	typeCasting typeCast = new typeCasting();

	public response landOwnershipContractSmartContract() {
		response res = new response();
		try {
			BigInteger _gasLimit = BigInteger.valueOf(2099756);
			BigInteger _garPrice = BigInteger.valueOf(1);
			LandOwnershipContract contract = LandOwnershipContract.deploy(web3, creds, _garPrice, _gasLimit).sendAsync()
					.get();
			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				return res;
			}
			res.setResult(contract.getContractAddress());
			return res;
		} catch (Exception ex) {
			// logger.error("Expection :" + ex);
			res.setMessage("Error");
			return res;
		}
	}

	public response transferOwnership(landRegistry landRegistry) {
		response res = new response();
		try {
			BigInteger _gasLimit = BigInteger.valueOf(1068756);
			BigInteger _garPrice = BigInteger.valueOf(1068756);
			LandOwnershipContract contract = LandOwnershipContract.load(contractAddress, web3, creds, _garPrice,
					_gasLimit);
			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				return res;
			}
			TransactionReceipt transact = contract.transferOwnership(landRegistry.price, landRegistry.sellerAddress,
					landRegistry.buyerAddress, landRegistry.propertyAddress).sendAsync().get();

			res.setResult(transact);
			return res;
		} catch (Exception ex) {
			// logger.error("Expection :" + ex);
			res.setMessage("Exception");
			res.setMethod("setSite");
			return res;
		}
	}

	public response getPropertyOwnershipDetails(String propertyAddress) {
		response res = new response();
		try {

			final String uuid = UUID.randomUUID().toString().replace("-", "");
			byte[] bidId = new byte[32];
			bidId = Numeric.hexStringToByteArray(typeCast.asciiToHex(uuid));
			BigInteger _gasLimit = BigInteger.valueOf(1068756);
			BigInteger _garPrice = BigInteger.valueOf(1068756);
			LandOwnershipContract contract = LandOwnershipContract.load(contractAddress, web3, creds, _garPrice,
					_gasLimit);
			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				return res;
			}
			Tuple4<BigInteger, String, String, String> transact = contract.getPropertyOwnershipDetails(propertyAddress)
					.sendAsync().get();
			res.setResult(transact);
			res.setMethod("setSite");
			return res;
		} catch (Exception ex) {
			// logger.error("Expection :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("setSite");
			return res;
		}
	}

}
