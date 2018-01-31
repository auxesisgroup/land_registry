package com.smartContract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

public final class LandOwnershipContract extends Contract {
	private static final String BINARY = "0x6060604052341561000f57600080fd5b6102b18061001e6000396000f3006060604052600436106100565763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416638299e51f811461005b578063a99163a61461009d578063b9534600146100f2575b600080fd5b341561006657600080fd5b610089600435600160a060020a0360243581169060443581169060643516610105565b604051901515815260200160405180910390f35b34156100a857600080fd5b6100bc600160a060020a0360043516610202565b604051938452600160a060020a039283166020850152908216604080850191909152911660608301526080909101905180910390f35b34156100fd57600080fd5b6100bc61023e565b600061010f61025e565b60806040519081016040908152878252600160a060020a0380881660208085019190915287821683850152908616606084018190526000908152600490915220909150819081518155602082015160018201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055604082015160028201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03929092169190911790556060820151600391909101805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a039092169190911790555060019695505050505050565b600160a060020a039081166000908152600460205260409020805460018201546002830154600390930154919490841693928316929190911690565b600054600154600254600354600160a060020a0392831692918216911684565b608060405190810160409081526000808352602083018190529082018190526060820152905600a165627a7a72305820533da02fc3bf5052054ba6e5d5ef201f78bf776f13428beba569063f97c9cd8b0029";

	private LandOwnershipContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	private LandOwnershipContract(String contractAddress, Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
	}

	public RemoteCall<TransactionReceipt> transferOwnership(BigInteger price, String seller, String buyer,
			String propertyId) {
		Function function = new Function("transferOwnership",
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(price),
						new org.web3j.abi.datatypes.Address(seller), new org.web3j.abi.datatypes.Address(buyer),
						new org.web3j.abi.datatypes.Address(propertyId)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<Tuple4<BigInteger, String, String, String>> getPropertyOwnershipDetails(String _propertyId) {
		final Function function = new Function("getPropertyOwnershipDetails",
				Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_propertyId)),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}, new TypeReference<Address>() {
				}, new TypeReference<Address>() {
				}, new TypeReference<Address>() {
				}));
		return new RemoteCall<Tuple4<BigInteger, String, String, String>>(
				new Callable<Tuple4<BigInteger, String, String, String>>() {
					@Override
					public Tuple4<BigInteger, String, String, String> call() throws Exception {
						List<Type> results = executeCallMultipleValueReturn(function);
						;
						return new Tuple4<BigInteger, String, String, String>((BigInteger) results.get(0).getValue(),
								(String) results.get(1).getValue(), (String) results.get(2).getValue(),
								(String) results.get(3).getValue());
					}
				});
	}

	public RemoteCall<Tuple4<BigInteger, String, String, String>> landOwnerships() {
		final Function function = new Function("landOwnerships", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}, new TypeReference<Address>() {
				}, new TypeReference<Address>() {
				}, new TypeReference<Address>() {
				}));
		return new RemoteCall<Tuple4<BigInteger, String, String, String>>(
				new Callable<Tuple4<BigInteger, String, String, String>>() {
					@Override
					public Tuple4<BigInteger, String, String, String> call() throws Exception {
						List<Type> results = executeCallMultipleValueReturn(function);
						;
						return new Tuple4<BigInteger, String, String, String>((BigInteger) results.get(0).getValue(),
								(String) results.get(1).getValue(), (String) results.get(2).getValue(),
								(String) results.get(3).getValue());
					}
				});
	}

	public static RemoteCall<LandOwnershipContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		return deployRemoteCall(LandOwnershipContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
	}

	public static RemoteCall<LandOwnershipContract> deploy(Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit) {
		return deployRemoteCall(LandOwnershipContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
	}

	public static LandOwnershipContract load(String contractAddress, Web3j web3j, Credentials credentials,
			BigInteger gasPrice, BigInteger gasLimit) {
		return new LandOwnershipContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	public static LandOwnershipContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit) {
		return new LandOwnershipContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
	}
}
