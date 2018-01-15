package com.ipfs;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blockchain.landOwnershipContractImp;
import com.blockchain.masterConstractImp;
import com.entity.buyerDetails;
import com.entity.propertyDetails;
import com.entity.response;
import com.entity.sellerContractMapping;
import com.entity.sign_up;
import com.serviceInt.donor_details_Int;

@RestController
public class contraller {

	masterConstractImp smart = new masterConstractImp();

	com.blockchain.getKeyPair getKeyPair = new com.blockchain.getKeyPair();
	landOwnershipContractImp smart2 = new landOwnershipContractImp();

	@Autowired
	donor_details_Int donor_details_Int;

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ResponseEntity<Object> signUp(@RequestBody sign_up sign_up) {
		response res = new response();
		try {
			System.out.println("signUp....................................");
			res = donor_details_Int.donorSignUp(sign_up);
			if (res.getMessage() != null) {
				return new ResponseEntity<Object>(res, HttpStatus.UNAUTHORIZED);
			}
			System.out.println("done....................................");

			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			return new ResponseEntity<Object>(Ex, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> Login(@RequestBody sign_up sign_up) {
		response res = new response();
		try {
			System.out.println(sign_up.role + ".............role");
			sign_up _sign_up = donor_details_Int.login(sign_up);
			if (_sign_up.Id == null) {
				res.setMessage("invalid credentials" + _sign_up.Id);
				return new ResponseEntity<Object>(res, HttpStatus.UNAUTHORIZED);
			}
			res.setResult(_sign_up);
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception Ex) {
			res.setResult(null);
			res.setMessage("Error");
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/landRegistryContractCreation", method = RequestMethod.GET)
	public ResponseEntity<Object> landRegistryContractCreation() {
		response res = new response();
		try {
			res = smart2.landOwnershipContractSmartContract();

			if (res.getMessage() != null) {
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/propertyContractCreation", method = RequestMethod.POST)
	public ResponseEntity<Object> deploySmartContract(@RequestBody propertyDetails _propertyDetails) {
		response res = new response();
		_propertyDetails.Id = UUID.randomUUID().toString();
		_propertyDetails.status = BigInteger.valueOf(1);
		try {
			_propertyDetails.propertyAddress = getKeyPair.getCredentials().getAddress();
			res = smart.createPropertySmartContract(_propertyDetails);
			if (res.getMessage() != null) {
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			_propertyDetails.contractAddress = res.getResult().toString();
			if (!donor_details_Int.addPropertyDetails(_propertyDetails)) {
				res.message = "unable to add details to database";
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getPropertyDetails/{address}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPropertyDetails(@PathVariable("address") String Address) {
		response res = new response();
		try {
			res = smart.getPropertyDetails();
			if (res.getMessage() != null) {
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);

		}
	}

	@RequestMapping(value = "/addBroker", method = RequestMethod.POST)
	public ResponseEntity<Object> addBroker(@RequestBody propertyDetails propertyDetails) {
		response res = new response();
		try {
			System.out.println("##########################################");
			System.out.println("propertyDetails.contractAddress ...." + propertyDetails.contractAddress);
			System.out.println("propertyDetails.brokerAddress ...." + propertyDetails.brokerAddress);
			smart.contractAddress = propertyDetails.contractAddress;
			res = smart.SetBroker(propertyDetails.brokerAddress);
			if (res.getMessage() != null) {

				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			int i = donor_details_Int.addBrokerContractMapping(propertyDetails);
			if (i == 0) {
				res.setMessage("database not updated");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);

		}
	}

	@RequestMapping(value = "/setBid/{contractAddress}", method = RequestMethod.POST)
	public ResponseEntity<Object> setBid(@PathVariable String contractAddress, @RequestBody buyerDetails buyerDetails) {
		response res = new response();
		try {
			smart.contractAddress = contractAddress;
			System.out.println("######address #### \n" + buyerDetails.address);
			res = smart.SetBid(buyerDetails);
			if (res.getMessage() != null) {
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getBidDetails/{contractAddress}", method = RequestMethod.GET)
	public ResponseEntity<Object> getBidDetails(@PathVariable("contractAddress") String contractAddress) {
		response res = new response();
		try {
			smart.contractAddress = contractAddress;
			res = smart.getBidDetails();
			if (res.getMessage() != null) {
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/confirmBuyer/{contractAddress}", method = RequestMethod.POST)
	public ResponseEntity<Object> confirmBuyer(@RequestBody buyerDetails buyerDetails,
			@PathVariable("contractAddress") String contractAddress) {
		response res = new response();
		try {
			smart.contractAddress = contractAddress;
			res = smart.confirmBuyer(buyerDetails.bidId);
			if (res.getMessage() != null) {
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getConfirmBuyerDetails/{address}", method = RequestMethod.GET)
	public ResponseEntity<Object> getConfirmBuyerDetails(@PathVariable("address") String address) {
		response res = new response();
		try {
			smart.contractAddress = address;
			res = smart.getConfirmBuyerDetails();
			if (res.getMessage() != null) {
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);

		}
	}

	@RequestMapping(value = "/deposit/{ethers}", method = RequestMethod.GET)
	public ResponseEntity<Object> deposit(@PathVariable("ethers") BigInteger _value) {
		response res = new response();
		try {
			res = smart.deposit(_value);
			if (res.getMessage() != null) {
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);

		}
	}

	@RequestMapping(value = "/getBuyerDetails/{address}", method = RequestMethod.GET)
	public ResponseEntity<Object> getBuyerDetails(@PathVariable("address") String address) {
		response res = new response();
		try {
			smart.contractAddress = address;
			res = smart.getBuyerDetails();
			if (res.getMessage() != null) {
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);

		}
	}

	@RequestMapping(value = "/getOwnerDetails/{address}", method = RequestMethod.GET)
	public ResponseEntity<Object> getOwnerDetails(@PathVariable("address") String address) {
		response res = new response();
		try {
			smart.contractAddress = address;
			res = smart.getOwnerDetails();
			if (res.getMessage() != null) {
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getPropertyOwnershipDetails/{propertyAddress}", method = RequestMethod.GET)
	public ResponseEntity<Object> getPropertyOwnershipDetails(@PathVariable("propertyAddress") String propertyAddress) {
		response res = new response();
		try {
			res = smart2.getPropertyOwnershipDetails(propertyAddress);
			if (res.getMessage() == null) {
				res.setMessage("Unable to add details");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getBrokerList", method = RequestMethod.GET)
	public ResponseEntity<Object> getPropertyOwnershipDetails() {
		response res = new response();
		try {
			List<sign_up> brokerList = donor_details_Int.getBrokerList();
			if (brokerList.size() == 0) {
				res.setMessage("No broker added");
				return new ResponseEntity<Object>(res, HttpStatus.OK);
			}
			// res.setResult(" details added successfully");
			res.setResult(brokerList);
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getUserContactDetails/{address}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUserContactDetails(@PathVariable("address") String address) {
		response res = new response();
		try {
			List<propertyDetails> brokerList = donor_details_Int.getUserContactDetails(address);
			if (brokerList.size() == 0) {
				res.setMessage("No contaract  added");
				return new ResponseEntity<Object>(res, HttpStatus.OK);
			}
			res.setResult(brokerList);
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getBrokerContactDetails/{address}", method = RequestMethod.GET)
	public ResponseEntity<Object> getBrokerContactDetails(@PathVariable("address") String address) {
		response res = new response();
		try {
			List<propertyDetails> brokerList = donor_details_Int.getBrokerContactDetails(address);
			if (brokerList.size() == 0) {
				res.setMessage("No contaract  added");
				return new ResponseEntity<Object>(res, HttpStatus.OK);
			}
			res.setResult(brokerList);
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getSellerList", method = RequestMethod.GET)
	public ResponseEntity<Object> getSellerList() {
		response res = new response();
		try {
			List<sign_up> brokerList = donor_details_Int.getSellerList();
			if (brokerList.size() == 0) {
				res.setMessage("No contaract  added");
				return new ResponseEntity<Object>(res, HttpStatus.OK);
			}
			res.setResult(brokerList);
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/addSellerToContract", method = RequestMethod.POST)
	public ResponseEntity<Object> addSellerToContract(@RequestBody sellerContractMapping sellerContractMapping) {
		response res = new response();
		try {
			boolean added = donor_details_Int.addSellerToContract(sellerContractMapping);
			if (!added) {
				res.setMessage("No contaract  added");
				return new ResponseEntity<Object>(res, HttpStatus.OK);
			}
			res.setResult("added successfully");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getBuyerContactDetails/{address}", method = RequestMethod.GET)
	public ResponseEntity<Object> getBuyerContactDetails(@PathVariable("address") String address) {
		response res = new response();
		try {
			List<propertyDetails> propertyDetails = donor_details_Int.getBuyerContactDetails(address);
			if (propertyDetails.size() == 0) {
				res.setMessage("No contaract  added");
				return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
			}
			res.setResult(propertyDetails);
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		} catch (Exception ex) {
			res.setResult(null);
			res.setMessage("Error" + ex);
			return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
		}
	}
}
