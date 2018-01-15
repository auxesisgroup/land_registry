package com.smartContract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>
 * Auto generated code.
 * <p>
 * <strong>Do not modify!</strong>
 * <p>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j
 * command line tools</a>, or the
 * org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen
 * module</a> to update.
 *
 * <p>
 * Generated with web3j version 3.0.2.
 */
public final class PropertyDeal extends Contract {
	private static final String BINARY = "0x606060405234156200001057600080fd5b60405162000c4a38038062000c4a833981016040528080518201919060200180518201919060200180519190602001805191906020018051919060200180519150600090508062000060620001d9565b6200006a620001d9565b89519350836040518059106200007d5750595b90808252806020026020018201604052509150600092505b83831015620000d857898381518110620000ab57fe5b90602001906020020151828481518110620000c257fe5b6020908102909101015260019092019162000095565b8851935083604051805910620000eb5750595b90808252806020026020018201604052509050600092505b8383101562000146578883815181106200011957fe5b906020019060200201518184815181106200013057fe5b6020908102909101015260019092019162000103565b600a8280516200015b929160200190620001eb565b50600b81805162000171929160200190620001eb565b505050600c9590955550600d805460ff909416740100000000000000000000000000000000000000000260a060020a60ff0219600160a060020a03938416600160a060020a03199687161716179055600080549290911691909216179055506200025d915050565b60206040519081016040526000815290565b8280548282559060005260206000209081019282156200022b579160200282015b828111156200022b57825182556020909201916001909101906200020c565b50620002399291506200023d565b5090565b6200025a91905b8082111562000239576000815560010162000244565b90565b6109dd806200026d6000396000f3006060604052600436106100a35763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663133a39ff81146100a85780633c471725146100d7578063439f4c531461011b57806357ea94ba1461015c578063836cae6514610195578063861d7799146101b45780639657b0b3146102a55780639a5af468146102da578063d0e30db0146102f0578063d81bc271146102fa575b600080fd5b34156100b357600080fd5b6100bb6103c8565b604051600160a060020a03909116815260200160405180910390f35b34156100e257600080fd5b6100ea6103d8565b604051600160a060020a03938416815291831660208301529091166040808301919091526060909101905180910390f35b341561012657600080fd5b61012e6103f5565b604051600160a060020a03909316835260208301919091526040808301919091526060909101905180910390f35b341561016757600080fd5b610181600160a060020a036004351660243560443561040f565b604051901515815260200160405180910390f35b34156101a057600080fd5b610181600160a060020a036004351661052a565b34156101bf57600080fd5b6101c761054c565b60405180806020018060200180602001848103845287818151815260200191508051906020019060200280838360005b8381101561020f5780820151838201526020016101f7565b50505050905001848103835286818151815260200191508051906020019060200280838360005b8381101561024e578082015183820152602001610236565b50505050905001848103825285818151815260200191508051906020019060200280838360005b8381101561028d578082015183820152602001610275565b50505050905001965050505050505060405180910390f35b34156102b057600080fd5b6102b86106c2565b604051600160a060020a03909216825260208201526040908101905180910390f35b34156102e557600080fd5b6101816004356106d6565b6102f8610777565b005b341561030557600080fd5b61030d610815565b60405160408101849052600160a060020a038316606082015260ff8216608082015260a080825281906020820190820188818151815260200191508051906020019060200280838360005b83811015610370578082015183820152602001610358565b50505050905001838103825287818151815260200191508051906020019060200280838360005b838110156103af578082015183820152602001610397565b5050505090500197505050505050505060405180910390f35b600154600160a060020a03165b90565b600054600154600254600160a060020a0392831693918316921690565b600654600754600854600160a060020a0390921691909192565b6000610419610918565b60606040519081016040908152600160a060020a03871682526020820186905281018490526009805491925090600181016104548382610938565b6000928352602090922083916003020181518154600160a060020a031916600160a060020a039190911617815560208201518160010155604082015160029091015550506000838152600360205260409020819081518154600160a060020a031916600160a060020a0391909116178155602082015181600101556040820151600290910155507f56c19b6a809a6e0657c30ba9c1310307027995bd583a82d097528de0148fef9b8585604051600160a060020a03909216825260208201526040908101905180910390a1506001949350505050565b60018054600160a060020a038316600160a060020a0319909116178155919050565b610554610969565b61055c610969565b610564610969565b600061056e610969565b610576610969565b61057e610969565b60095493506000846040518059106105935750595b90808252806020026020018201604052509350846040518059106105b45750595b90808252806020026020018201604052509250846040518059106105d55750595b90808252806020026020018201604052509150600090505b848110156106b457600980548290811061060357fe5b6000918252602090912060039091020154600160a060020a031684828151811061062957fe5b600160a060020a03909216602092830290910190910152600980548290811061064e57fe5b90600052602060002090600302016001015483828151811061066c57fe5b60209081029091010152600980548290811061068457fe5b9060005260206000209060030201600201548282815181106106a257fe5b602090810290910101526001016105ed565b509196909550909350915050565b600454600554600160a060020a0390911691565b600081815260036020526040808220805460068054600160a060020a031916600160a060020a03928316179055600182018054600755600283015460088190558354915493947fef991712cd180dbcf6c5c55a070215a14c3aab55f78cc5659412406c5462a0e494929093169251600160a060020a03909316835260208301919091526040808301919091526060909101905180910390a150600192915050565b600580543490810190915560048054600160a060020a0333818116600160a060020a0319938416811790945560028054909316909317909155600054600d547f3d1d0f791c37625e49882af095bd49fc4f9f60379fe1b910612d6193f5341ca494929182169116604051600160a060020a039485168152602081019390935290831660408084019190915292166060820152608001905180910390a1565b61081d610969565b610825610969565b600c54600d54600a80546000938493849392600b9291600160a060020a038116917401000000000000000000000000000000000000000090910460ff169085906020808202016040519081016040528092919081815260200182805480156108ad57602002820191906000526020600020905b81548152600190910190602001808311610898575b505050505094508380548060200260200160405190810160405280929190818152602001828054801561090057602002820191906000526020600020905b815481526001909101906020018083116108eb575b50505050509350945094509450945094509091929394565b606060405190810160409081526000808352602083018190529082015290565b81548183558181151161096457600302816003028360005260206000209182019101610964919061097b565b505050565b60206040519081016040526000815290565b6103d591905b808211156109ad578054600160a060020a03191681556000600182018190556002820155600301610981565b50905600a165627a7a72305820e441c749b395864720fd1ff33e1c80a751d7dcca4b51f7cfb17e30affee862470029";

	private PropertyDeal(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	private PropertyDeal(String contractAddress, Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
	}

	public List<BidEvnEventResponse> getBidEvnEvents(TransactionReceipt transactionReceipt) {
		final Event event = new Event("bidEvn", Arrays.<TypeReference<?>>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}, new TypeReference<Uint256>() {
				}));
		List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
		ArrayList<BidEvnEventResponse> responses = new ArrayList<BidEvnEventResponse>(valueList.size());
		for (EventValues eventValues : valueList) {
			BidEvnEventResponse typedResponse = new BidEvnEventResponse();
			typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
			typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
			responses.add(typedResponse);
		}
		return responses;
	}

	public Observable<BidEvnEventResponse> bidEvnEventObservable(DefaultBlockParameter startBlock,
			DefaultBlockParameter endBlock) {
		final Event event = new Event("bidEvn", Arrays.<TypeReference<?>>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}, new TypeReference<Uint256>() {
				}));
		EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
		filter.addSingleTopic(EventEncoder.encode(event));
		return web3j.ethLogObservable(filter).map(new Func1<Log, BidEvnEventResponse>() {
			@Override
			public BidEvnEventResponse call(Log log) {
				EventValues eventValues = extractEventParameters(event, log);
				BidEvnEventResponse typedResponse = new BidEvnEventResponse();
				typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
				typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
				return typedResponse;
			}
		});
	}

	public List<AddBrokerEvnEventResponse> getAddBrokerEvnEvents(TransactionReceipt transactionReceipt) {
		final Event event = new Event("addBrokerEvn", Arrays.<TypeReference<?>>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}));
		List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
		ArrayList<AddBrokerEvnEventResponse> responses = new ArrayList<AddBrokerEvnEventResponse>(valueList.size());
		for (EventValues eventValues : valueList) {
			AddBrokerEvnEventResponse typedResponse = new AddBrokerEvnEventResponse();
			typedResponse.brokerAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
			responses.add(typedResponse);
		}
		return responses;
	}

	public Observable<AddBrokerEvnEventResponse> addBrokerEvnEventObservable(DefaultBlockParameter startBlock,
			DefaultBlockParameter endBlock) {
		final Event event = new Event("addBrokerEvn", Arrays.<TypeReference<?>>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}));
		EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
		filter.addSingleTopic(EventEncoder.encode(event));
		return web3j.ethLogObservable(filter).map(new Func1<Log, AddBrokerEvnEventResponse>() {
			@Override
			public AddBrokerEvnEventResponse call(Log log) {
				EventValues eventValues = extractEventParameters(event, log);
				AddBrokerEvnEventResponse typedResponse = new AddBrokerEvnEventResponse();
				typedResponse.brokerAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
				return typedResponse;
			}
		});
	}

	public List<ConfirmBuyerEvnEventResponse> getConfirmBuyerEvnEvents(TransactionReceipt transactionReceipt) {
		final Event event = new Event("confirmBuyerEvn", Arrays.<TypeReference<?>>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Bytes32>() {
				}));
		List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
		ArrayList<ConfirmBuyerEvnEventResponse> responses = new ArrayList<ConfirmBuyerEvnEventResponse>(
				valueList.size());
		for (EventValues eventValues : valueList) {
			ConfirmBuyerEvnEventResponse typedResponse = new ConfirmBuyerEvnEventResponse();
			typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
			typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
			typedResponse.bidId = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
			responses.add(typedResponse);
		}
		return responses;
	}

	public Observable<ConfirmBuyerEvnEventResponse> confirmBuyerEvnEventObservable(DefaultBlockParameter startBlock,
			DefaultBlockParameter endBlock) {
		final Event event = new Event("confirmBuyerEvn", Arrays.<TypeReference<?>>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Bytes32>() {
				}));
		EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
		filter.addSingleTopic(EventEncoder.encode(event));
		return web3j.ethLogObservable(filter).map(new Func1<Log, ConfirmBuyerEvnEventResponse>() {
			@Override
			public ConfirmBuyerEvnEventResponse call(Log log) {
				EventValues eventValues = extractEventParameters(event, log);
				ConfirmBuyerEvnEventResponse typedResponse = new ConfirmBuyerEvnEventResponse();
				typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
				typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
				typedResponse.bidId = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
				return typedResponse;
			}
		});
	}

	public List<PaymentEvnEventResponse> getPaymentEvnEvents(TransactionReceipt transactionReceipt) {
		final Event event = new Event("paymentEvn", Arrays.<TypeReference<?>>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Address>() {
				}, new TypeReference<Address>() {
				}));
		List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
		ArrayList<PaymentEvnEventResponse> responses = new ArrayList<PaymentEvnEventResponse>(valueList.size());
		for (EventValues eventValues : valueList) {
			PaymentEvnEventResponse typedResponse = new PaymentEvnEventResponse();
			typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
			typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
			typedResponse.owner = (String) eventValues.getNonIndexedValues().get(2).getValue();
			typedResponse.propertyAddress = (String) eventValues.getNonIndexedValues().get(3).getValue();
			responses.add(typedResponse);
		}
		return responses;
	}

	public Observable<PaymentEvnEventResponse> paymentEvnEventObservable(DefaultBlockParameter startBlock,
			DefaultBlockParameter endBlock) {
		final Event event = new Event("paymentEvn", Arrays.<TypeReference<?>>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Address>() {
				}, new TypeReference<Address>() {
				}));
		EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
		filter.addSingleTopic(EventEncoder.encode(event));
		return web3j.ethLogObservable(filter).map(new Func1<Log, PaymentEvnEventResponse>() {
			@Override
			public PaymentEvnEventResponse call(Log log) {
				EventValues eventValues = extractEventParameters(event, log);
				PaymentEvnEventResponse typedResponse = new PaymentEvnEventResponse();
				typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
				typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
				typedResponse.owner = (String) eventValues.getNonIndexedValues().get(2).getValue();
				typedResponse.propertyAddress = (String) eventValues.getNonIndexedValues().get(3).getValue();
				return typedResponse;
			}
		});
	}

	public RemoteCall<String> getBrokerDetails() {
		Function function = new Function("getBrokerDetails", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}));
		return executeRemoteCallSingleValueReturn(function, String.class);
	}

	public RemoteCall<Tuple3<String, String, String>> getOwnerDetails() {
		final Function function = new Function("getOwnerDetails", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}, new TypeReference<Address>() {
				}, new TypeReference<Address>() {
				}));
		return new RemoteCall<Tuple3<String, String, String>>(new Callable<Tuple3<String, String, String>>() {
			@Override
			public Tuple3<String, String, String> call() throws Exception {
				List<Type> results = executeCallMultipleValueReturn(function);
				;
				return new Tuple3<String, String, String>((String) results.get(0).getValue(),
						(String) results.get(1).getValue(), (String) results.get(2).getValue());
			}
		});
	}

	public RemoteCall<Tuple3<String, BigInteger, byte[]>> getConfirmBuyerDetails() {
		final Function function = new Function("getConfirmBuyerDetails", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Bytes32>() {
				}));
		return new RemoteCall<Tuple3<String, BigInteger, byte[]>>(new Callable<Tuple3<String, BigInteger, byte[]>>() {
			@Override
			public Tuple3<String, BigInteger, byte[]> call() throws Exception {
				List<Type> results = executeCallMultipleValueReturn(function);
				;
				return new Tuple3<String, BigInteger, byte[]>((String) results.get(0).getValue(),
						(BigInteger) results.get(1).getValue(), (byte[]) results.get(2).getValue());
			}
		});
	}

	public RemoteCall<TransactionReceipt> bid(String buyerAddress, BigInteger price, byte[] bidId) {
		Function function = new Function("bid",
				Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(buyerAddress),
						new org.web3j.abi.datatypes.generated.Uint256(price),
						new org.web3j.abi.datatypes.generated.Bytes32(bidId)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<TransactionReceipt> addBroker(String _brokerAddress) {
		Function function = new Function("addBroker",
				Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_brokerAddress)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<Tuple3<List<Address>, List<Uint256>, List<Bytes32>>> getBidDetails() {
		final Function function = new Function("getBidDetails", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {
				}, new TypeReference<DynamicArray<Uint256>>() {
				}, new TypeReference<DynamicArray<Bytes32>>() {
				}));
		return new RemoteCall<Tuple3<List<Address>, List<Uint256>, List<Bytes32>>>(
				new Callable<Tuple3<List<Address>, List<Uint256>, List<Bytes32>>>() {
					@Override
					public Tuple3<List<Address>, List<Uint256>, List<Bytes32>> call() throws Exception {
						List<Type> results = executeCallMultipleValueReturn(function);
						;
						return new Tuple3<List<Address>, List<Uint256>, List<Bytes32>>(
								(List<Address>) results.get(0).getValue(), (List<Uint256>) results.get(1).getValue(),
								(List<Bytes32>) results.get(2).getValue());
					}
				});
	}

	public RemoteCall<Tuple2<String, BigInteger>> getBuyerDetails() {
		final Function function = new Function("getBuyerDetails", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}, new TypeReference<Uint256>() {
				}));
		return new RemoteCall<Tuple2<String, BigInteger>>(new Callable<Tuple2<String, BigInteger>>() {
			@Override
			public Tuple2<String, BigInteger> call() throws Exception {
				List<Type> results = executeCallMultipleValueReturn(function);
				;
				return new Tuple2<String, BigInteger>((String) results.get(0).getValue(),
						(BigInteger) results.get(1).getValue());
			}
		});
	}

	public RemoteCall<TransactionReceipt> confirmBuyer(byte[] bidId) {
		Function function = new Function("confirmBuyer",
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(bidId)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<TransactionReceipt> deposit(BigInteger weiValue) {
		Function function = new Function("deposit", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function, weiValue);
	}

	public RemoteCall<Tuple5<List<Bytes32>, List<Bytes32>, BigInteger, String, BigInteger>> getPropertyDetails() {
		final Function function = new Function("getPropertyDetails", Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bytes32>>() {
				}, new TypeReference<DynamicArray<Bytes32>>() {
				}, new TypeReference<Uint256>() {
				}, new TypeReference<Address>() {
				}, new TypeReference<Uint8>() {
				}));
		return new RemoteCall<Tuple5<List<Bytes32>, List<Bytes32>, BigInteger, String, BigInteger>>(
				new Callable<Tuple5<List<Bytes32>, List<Bytes32>, BigInteger, String, BigInteger>>() {
					@Override
					public Tuple5<List<Bytes32>, List<Bytes32>, BigInteger, String, BigInteger> call()
							throws Exception {
						List<Type> results = executeCallMultipleValueReturn(function);
						;
						return new Tuple5<List<Bytes32>, List<Bytes32>, BigInteger, String, BigInteger>(
								(List<Bytes32>) results.get(0).getValue(), (List<Bytes32>) results.get(1).getValue(),
								(BigInteger) results.get(2).getValue(), (String) results.get(3).getValue(),
								(BigInteger) results.get(4).getValue());
					}
				});
	}

	public static RemoteCall<PropertyDeal> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit, List<byte[]> _description, List<byte[]> _fileHash, BigInteger _price,
			BigInteger _status, String _ownerAddress, String _propertyAddress) {
		String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(
				new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
						org.web3j.abi.Utils.typeMap(_description, org.web3j.abi.datatypes.generated.Bytes32.class)),
				new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
						org.web3j.abi.Utils.typeMap(_fileHash, org.web3j.abi.datatypes.generated.Bytes32.class)),
				new org.web3j.abi.datatypes.generated.Uint256(_price),
				new org.web3j.abi.datatypes.generated.Uint8(_status),
				new org.web3j.abi.datatypes.Address(_ownerAddress),
				new org.web3j.abi.datatypes.Address(_propertyAddress)));
		return deployRemoteCall(PropertyDeal.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
	}

	public static RemoteCall<PropertyDeal> deploy(Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit, List<byte[]> _description, List<byte[]> _fileHash,
			BigInteger _price, BigInteger _status, String _ownerAddress, String _propertyAddress) {
		String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(
				new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
						org.web3j.abi.Utils.typeMap(_description, org.web3j.abi.datatypes.generated.Bytes32.class)),
				new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
						org.web3j.abi.Utils.typeMap(_fileHash, org.web3j.abi.datatypes.generated.Bytes32.class)),
				new org.web3j.abi.datatypes.generated.Uint256(_price),
				new org.web3j.abi.datatypes.generated.Uint8(_status),
				new org.web3j.abi.datatypes.Address(_ownerAddress),
				new org.web3j.abi.datatypes.Address(_propertyAddress)));
		return deployRemoteCall(PropertyDeal.class, web3j, transactionManager, gasPrice, gasLimit, BINARY,
				encodedConstructor);
	}

	public static PropertyDeal load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		return new PropertyDeal(contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	public static PropertyDeal load(String contractAddress, Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit) {
		return new PropertyDeal(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
	}

	public static class BidEvnEventResponse {
		public String from;

		public BigInteger value;
	}

	public static class AddBrokerEvnEventResponse {
		public String brokerAddress;
	}

	public static class ConfirmBuyerEvnEventResponse {
		public String from;

		public BigInteger value;

		public byte[] bidId;
	}

	public static class PaymentEvnEventResponse {
		public String from;

		public BigInteger value;

		public String owner;

		public String propertyAddress;
	}
}
