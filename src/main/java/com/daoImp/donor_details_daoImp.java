package com.daoImp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.daoInt.donor_details_daoInt;

import com.entity.createkeypairs;

import com.entity.propertyDetails;
import com.entity.response;
import com.entity.sellerContractMapping;
import com.entity.sign_up;

@Repository
@Transactional
public class donor_details_daoImp implements donor_details_daoInt, Serializable {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public boolean donorSignUp(sign_up sign_up, createkeypairs createkeypairs) {
		try {
			System.out.println(sign_up.role + "..............role");
			getSession().save(sign_up);
			getSession().save(createkeypairs);
			return true;
		} catch (Exception ex) {
			System.out.println("login ........." + ex);
		}
		return false;
	}

	public response getWalletDetails(String user_id) {
		response res = new response();
		try {
			Query query = getSession().createQuery("from createkeypairs  where user_id=:user_id");
			query.setParameter("user_id", user_id);
			List results = query.getResultList();
			res.setResult(results);
			return res;
		} catch (Exception ex) {
			res.setMessage("error " + ex);
			res.setResult(null);
		}
		return res;
	}

	public sign_up login(sign_up sign_up) {

		sign_up user = new sign_up();
		try {
			System.out.println(sign_up.role + ".............role");
			Query query = getSession()
					.createQuery("from sign_up  where username=:username and password=:password and role=:role");
			query.setParameter("username", sign_up.username);
			query.setParameter("password", sign_up.password);
			query.setParameter("role", sign_up.role);
			List results = query.getResultList();
			return (sign_up) results.get(0);
		} catch (Exception ex) {
			System.out.println("login ........." + ex);

			return user;
		}
	}

	public List<sign_up> getBrokerList() {
		List<sign_up> user = new ArrayList<sign_up>();
		try {
			Query query = getSession().createQuery("from sign_up  where  role=:role");
			query.setParameter("role", 1);
			user = query.getResultList();
			return user;
		} catch (Exception ex) {
			System.out.println("login ........." + ex);

			return user;
		}
	}

	public sign_up userById(String username) {
		sign_up user = new sign_up();
		try {
			Query query = getSession().createQuery("from sign_up  where username=:username");
			query.setParameter("username", username);

			List results = query.getResultList();
			if (results.size() == 0) {
				return user;
			}
			return (sign_up) results.get(0);
		} catch (Exception ex) {
			System.out.println("login ........." + ex);
			return user;
		}
	}

	public List<propertyDetails> getUserContactDetails(String address) {
		List<propertyDetails> propertyDetails = new ArrayList<propertyDetails>();
		try {
			Query query = getSession().createQuery("from propertyDetails  where sellerAddress=:address");
			query.setParameter("address", address);
			propertyDetails = query.getResultList();
			return propertyDetails;
		} catch (Exception ex) {
			return propertyDetails;
		}
	}

	public List<propertyDetails> getBrokerContactDetails(String address) {
		List<propertyDetails> propertyDetails = new ArrayList<propertyDetails>();
		try {
			Query query = getSession().createQuery("from propertyDetails  where brokerAddress=:address");
			query.setParameter("address", address);
			propertyDetails = query.getResultList();
			return propertyDetails;
		} catch (Exception ex) {
			return propertyDetails;
		}
	}

	public createkeypairs getById(String id) {
		// Session session = this.sessionFactory.getCurrentSession();
		createkeypairs p = (createkeypairs) getSession().get(createkeypairs.class, id);
		// logger.info("Person loaded successfully, Person details="+p);
		return p;
	}

	///////////////

	public boolean addPropertyDetails(propertyDetails propertyDetails) {
		try {
			getSession().save(propertyDetails);
			return true;
		} catch (Exception ex) {
			System.out.println("login ........." + ex);
		}
		return false;
	}

	public int addBrokerContractMapping(propertyDetails propertyDetails) {
		int result = 0;
		try {
			Query query = getSession().createQuery(
					"update propertyDetails set brokerAddress=:brokerAddress where  contractAddress=:contractAddress");
			query.setParameter("contractAddress", propertyDetails.contractAddress);
			query.setParameter("brokerAddress", propertyDetails.brokerAddress);
			result = query.executeUpdate();
			return result;
		} catch (Exception ex) {
			System.out.println("login ........." + ex);
		}
		return result;
	}

	public List<sign_up> getSellerList() {
		List<sign_up> user = new ArrayList<sign_up>();
		try {

			Query query = getSession().createQuery("from sign_up  where  role=:role");
			query.setParameter("role", 2);
			user = query.getResultList();
			return user;
		} catch (Exception ex) {
			System.out.println("login ........." + ex);
		}
		return user;
	}

	public boolean addSellerToContract(sellerContractMapping sellerContractMapping) {
		try {
			sellerContractMapping.Id = UUID.randomUUID().toString();
			getSession().save(sellerContractMapping);
			return true;
		} catch (Exception ex) {
			System.out.println("login ........." + ex);
		}
		return false;
	}

	public List<propertyDetails> getBuyerContactDetails(String address) {
		System.out.println("address...." + address);
		List<propertyDetails> propertyDetails = new ArrayList<propertyDetails>();
		try {
			Query query = getSession().createQuery(
					"FROM propertyDetails e INNER JOIN sellerContractMapping t ON e.contractAddress=t.contractAddress where  t.sellerAddress=:address");
			query.setParameter("address", address);

			System.out.println("################################################################....");
			propertyDetails = query.list();
			System.out.println(
					"################################################################...." + propertyDetails.size());
			return propertyDetails;
		} catch (Exception ex) {
			return propertyDetails;
		}
	}

}
