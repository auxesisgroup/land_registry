package com.blockchain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.utils.Numeric;
import com.entity.buyerDetails;
import com.entity.landRegistry;
import com.entity.propertyDetails;
import com.entity.response;
import com.sm.blockchain.getBalance;
import com.sm.blockchain.typeCasting;
import com.smartContract.PropertyDeal;
import com.smartContract.PropertyDeal.BidEvnEventResponse;
import com.smartContract.PropertyDeal.ConfirmBuyerEvnEventResponse;
import com.smartContract.PropertyDeal.PaymentEvnEventResponse;

@Service
public class masterConstractImp {

	landOwnershipContractImp landOwnershipContractImp = new landOwnershipContractImp();
	static Web3j web3 = Web3j.build(new HttpService("http://139.59.213.205:7007"));
	getBalance _getBalance = new getBalance();
	public String contractAddress = "";
	// public String contractAddress =
	// "0x69a45ace5719535ffcc75e1bfad9ead8cf0cbebc";
	String senderPrivKey = "44816426bedb635d8f2367a47828bb37aa99c6a247264a76517f1fa8f6ec5139";
	Credentials creds = Credentials.create(senderPrivKey);
	typeCasting typeCast = new typeCasting();

	public response createPropertySmartContract(propertyDetails _propertyDetails) {
		response res = new response();
		List<byte[]> description = typeCast.stringToBytes32Array(_propertyDetails.description);
		List<byte[]> fileHash = typeCast.stringToBytes32Array(_propertyDetails.fileHash);
		res.setMethod("masterSmartContractDeply");
		try {
			BigInteger _gasLimit = BigInteger.valueOf(2099756);
			BigInteger _garPrice = BigInteger.valueOf(1);
			PropertyDeal contract = PropertyDeal
					.deploy(web3, creds, _garPrice, _gasLimit, description, fileHash, _propertyDetails.price,
							_propertyDetails.status, _propertyDetails.propertyAddress, _propertyDetails.sellerAddress)
					.sendAsync().get();
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

	public response getPropertyDetails() {
		response res = new response();
		try {
			BigInteger _gasLimit = BigInteger.valueOf(1068756);
			BigInteger _garPrice = BigInteger.valueOf(1068756);
			PropertyDeal contract = PropertyDeal.load(contractAddress, web3, creds, _garPrice, _gasLimit);
			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				return res;
			}
			Tuple5<List<Bytes32>, List<Bytes32>, BigInteger, String, BigInteger> transact = contract
					.getPropertyDetails().sendAsync().get();

			propertyDetails _propertyDetails = new propertyDetails();
			_propertyDetails.description = typeCast.bytes32ArrayToString(transact.getValue1());
			_propertyDetails.fileHash = typeCast.bytes32ArrayToString(transact.getValue2());
			_propertyDetails.price = (transact.getValue3());
			_propertyDetails.propertyAddress = (transact.getValue4());
			_propertyDetails.status = (transact.getValue5());
			res.setMethod("setSite");
			res.setResult(_propertyDetails);
			return res;
		} catch (Exception ex) {
			// logger.error("Expection :" + ex);
			res.setMessage("Exception");
			res.setMethod("setSite");
			return res;
		}
	}

	public response SetBroker(String brokerAddress) {
		response res = new response();
		try {
			BigInteger _gasLimit = BigInteger.valueOf(1068756);
			BigInteger _garPrice = BigInteger.valueOf(1068756);
			PropertyDeal contract = PropertyDeal.load(contractAddress, web3, creds, _garPrice, _gasLimit);
			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				return res;
			}
			TransactionReceipt transact = contract.addBroker(brokerAddress).sendAsync().get();
			res.setMethod("setSite");
			res.setResult(transact);
			return res;
		} catch (Exception ex) {
			// logger.error("Expection :" + ex);
			res.setMessage("Exception");
			res.setMethod("setSite");
			return res;
		}
	}

	public response SetBid(buyerDetails buyerDetails) {
		response res = new response();
		try {
			final String uuid = UUID.randomUUID().toString().replace("-", "");
			byte[] bidId = new byte[32];
			bidId = Numeric.hexStringToByteArray(typeCast.asciiToHex(uuid));
			BigInteger _gasLimit = BigInteger.valueOf(1068756);
			BigInteger _garPrice = BigInteger.valueOf(1068756);
			PropertyDeal contract = PropertyDeal.load(contractAddress, web3, creds, _garPrice, _gasLimit);
			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				return res;
			}
			TransactionReceipt transact = contract.bid(buyerDetails.address.toString(), buyerDetails.price, bidId)
					.sendAsync().get();
			List<BidEvnEventResponse> evnRes = contract.getBidEvnEvents(transact);
			res.setMethod("setSite");
			res.setResult(transact);
			return res;
		} catch (Exception ex) {
			// logger.error("Expection :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("setSite");
			return res;
		}
	}

	public response getBidDetails() {
		response res = new response();
		try {

			BigInteger _gasLimit = BigInteger.valueOf(1068756);
			BigInteger _garPrice = BigInteger.valueOf(1068756);
			PropertyDeal contract = PropertyDeal.load(contractAddress, web3, creds, _garPrice, _gasLimit);
			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				return res;
			}
			Tuple3<List<Address>, List<Uint256>, List<Bytes32>> transact = contract.getBidDetails().sendAsync().get();
			List<buyerDetails> buyerDetailsList = new ArrayList<buyerDetails>();
			System.out.println("######size 2 #### \n" + transact.getValue1().size());
			for (int i = 0; i < transact.getValue1().size(); i++) {
				buyerDetails buyerDetails = new buyerDetails();
				buyerDetails.address = transact.getValue1().get(i).getValue();
				buyerDetails.price = transact.getValue2().get(i).getValue();
				buyerDetails.bidId = StringUtils.newStringUsAscii(transact.getValue3().get(i).getValue());
				buyerDetailsList.add(buyerDetails);
			}
			res.setMethod("setSite");
			res.setResult(buyerDetailsList);
			return res;
		} catch (Exception ex) {
			// logger.error("Expection :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("setSite");
			return res;
		}
	}

	public response confirmBuyer(String _bidId) {
		response res = new response();
		try {
			byte[] bidId = Numeric.hexStringToByteArray(typeCast.asciiToHex(_bidId));
			BigInteger _gasLimit = BigInteger.valueOf(1068756);
			BigInteger _garPrice = BigInteger.valueOf(1068756);
			PropertyDeal contract = PropertyDeal.load(contractAddress, web3, creds, _garPrice, _gasLimit);
			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				return res;
			}
			TransactionReceipt transact = contract.confirmBuyer(bidId).sendAsync().get();
			List<ConfirmBuyerEvnEventResponse> evnRes = contract.getConfirmBuyerEvnEvents(transact);
			res.setMethod("setSite");
			res.setResult(transact);
			return res;

		} catch (Exception ex) {
			System.out.println("Ex::::::  " + ex);
			// logger.error("Expection :" + ex);
			res.setMessage("Exception");
			res.setMethod("setSite");
			return res;
		}
	}

	public response getConfirmBuyerDetails() {
		response res = new response();
		try {
			BigInteger _gasLimit = BigInteger.valueOf(1068756);
			BigInteger _garPrice = BigInteger.valueOf(1068756);
			PropertyDeal contract = PropertyDeal.load(contractAddress, web3, creds, _garPrice, _gasLimit);
			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				return res;
			}
			Tuple3<String, BigInteger, byte[]> transact = contract.getConfirmBuyerDetails().sendAsync().get();
			buyerDetails buyerDetails = new buyerDetails();
			buyerDetails.address = transact.getValue1();
			buyerDetails.price = transact.getValue2();
			buyerDetails.bidId = new String(transact.getValue3());
			res.setMethod("setSite");
			res.setResult(buyerDetails);
			return res;
		} catch (Exception ex) {
			// logger.error("Expection :" + ex);
			res.setMessage("Exception");
			res.setMethod("setSite");
			return res;
		}
	}

	public response getBuyerDetails() {
		response res = new response();
		try {
			BigInteger _gasLimit = BigInteger.valueOf(1068756);
			BigInteger _garPrice = BigInteger.valueOf(1068756);
			PropertyDeal contract = PropertyDeal.load(contractAddress, web3, creds, _garPrice, _gasLimit);
			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				return res;
			}
			Tuple2<String, BigInteger> transact = contract.getBuyerDetails().sendAsync().get();
			buyerDetails buyerDetails = new buyerDetails();
			buyerDetails.address = transact.getValue1();
			buyerDetails.price = transact.getValue2();
			res.setMethod("setSite");
			res.setResult(buyerDetails);
			return res;
		} catch (Exception ex) {
			// logger.error("Expection :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("setSite");
			return res;
		}
	}

	public response deposit(BigInteger _value) {
		response res = new response();
		try {
			// BigInteger value = BigInteger.valueOf(_value);
			BigInteger _gasLimit = BigInteger.valueOf(1068756);
			BigInteger _garPrice = BigInteger.valueOf(1068756);
			PropertyDeal contract = PropertyDeal.load(contractAddress, web3, creds, _garPrice, _gasLimit);
			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				return res;
			}
			TransactionReceipt transact = contract.deposit(_value).sendAsync().get();

			List<PaymentEvnEventResponse> evntRes = contract.getPaymentEvnEvents(transact);
			if (evntRes.get(0) == null) {

				res.setMessage("Event response null");
				return res;
			}
			landRegistry _landRegistry = new landRegistry();
			_landRegistry.price = evntRes.get(0).value;
			_landRegistry.sellerAddress = evntRes.get(0).owner;
			_landRegistry.buyerAddress = evntRes.get(0).from;
			_landRegistry.propertyAddress = evntRes.get(0).propertyAddress;
			landOwnershipContractImp.transferOwnership(_landRegistry);
			res.setMethod("deposit");
			res.setResult(evntRes);
			return res;
		} catch (Exception ex) {
			// logger.error("Expection :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("setSite");
			return res;
		}
	}

	public response getOwnerDetails() {
		response res = new response();
		try {
			BigInteger _gasLimit = BigInteger.valueOf(1068756);
			BigInteger _garPrice = BigInteger.valueOf(1068756);
			PropertyDeal contract = PropertyDeal.load(contractAddress, web3, creds, _garPrice, _gasLimit);
			if (!contract.isValid()) {
				res.setMessage("Invalid contract");
				return res;
			}
			Tuple3<String, String, String> transact = contract.getOwnerDetails().sendAsync().get();
			landRegistry landRegistry = new landRegistry();
			landRegistry.sellerAddress = transact.getValue1();
			landRegistry.brokerAddress = transact.getValue2();
			landRegistry.buyerAddress = transact.getValue3();
			res.setMethod("deposit");
			res.setResult(landRegistry);
			return res;
		} catch (Exception ex) {
			// logger.error("Expection :" + ex);
			res.setMessage("Exception" + ex);
			res.setMethod("setSite");
			return res;
		}
	}

	// public response getBuyerDetailsEvt() {
	// response res = new response();
	// try {
	// BigInteger _gasLimit = BigInteger.valueOf(1068756);
	// BigInteger _garPrice = BigInteger.valueOf(1068756);
	// PropertyDeal contract = PropertyDeal.load(contractAddress, web3, creds,
	// _garPrice, _gasLimit);
	// if (!contract.isValid()) {
	// res.setMessage("Invalid contract");
	// return res;
	// }
	// //TransactionReceipt transact = "";
	// Tuple2<String, BigInteger> transact =
	// contract.getPaymentEvnEvents().sendAsync().get();
	// buyerDetails buyerDetails = new buyerDetails();
	// buyerDetails.address = transact.getValue1();
	// buyerDetails.price = transact.getValue2();
	// res.setMethod("setSite");
	// res.setResult(buyerDetails);
	// return res;
	//
	// } catch (Exception ex) {
	// // logger.error("Expection :" + ex);
	// res.setMessage("Exception" + ex);
	// res.setMethod("setSite");
	// return res;
	// }
	// }

}
