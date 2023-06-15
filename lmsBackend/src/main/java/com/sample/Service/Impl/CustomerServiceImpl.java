package com.sample.Service.Impl;//business logic

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sample.Exception.CustomerAlreadyRegisteredException;
import com.sample.Exception.CustomerNotFoundException;
import com.sample.Model.Customer;
import com.sample.Repository.CustomerRepository;
import com.sample.Services.iCustomerService;


@Service
@Primary
public class CustomerServiceImpl implements iCustomerService {
	
	private static final String SECRET_KEY = "my_super_secret_key";
	private static final String SALT = "ssshhhhhhhhhhh!!!!";


	private CustomerRepository customerDao;
	
	public CustomerServiceImpl(CustomerRepository customerRepo)
	{
		this.customerDao=customerRepo;
	}
	//private Logger logger = Logger.getLogger(getClass());

	@Override
	public Customer addCustomer(Customer c) {
		Customer customer = customerDao.checkCustomer(c.getEmail(), c.getAdhaar(), c.getPan(), c.getPhone());
		if (customer != null) {
			throw new CustomerAlreadyRegisteredException("Customer Already Registered: " + customer.getId());
		}
		
		String encrypted=encrypt(c.getPassword());
		System.out.println("encrypted password:"+encrypted);
		c.setPassword(encrypted);
		return this.customerDao.save(c);
	}

	@Override
	public Integer doLogin(String email, String password) {
		Integer customerId = null;
		try {
		System.out.println("password by front end:"+password);
		String decryption=encrypt(password);
		//customerId=customerDao.findCustomerByEmailAndPassword(email, password);
			customerId = customerDao.findCustomerByEmailAndPassword(email, decryption);
			//logger.info("Customer: " + customerId + " Logged In Successfully");
			return customerId;
		} catch (Exception e) {
			throw new CustomerNotFoundException("Customer Not Found: " + customerId);
		}
	}
   @Override
	public Customer updateCustomer(Customer c) {
		Customer customer = customerDao.findById(c.getId());
			//	.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + c.getId()));
		BeanUtils.copyProperties(c, customer);
		return customerDao.save(customer);
	}

	@Override
	public List<Customer> getCustomers(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return customerDao.findAll(pageable).toList();
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Customer customer = customerDao.findById(customerId);
			//	.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
		//logger.info("Customer Found: " + customerId);
		return customer;
	}
	public  String decrypt(String strToDecrypt) {
		try {
			byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			System.out.println("line 97");
		      IvParameterSpec ivspec = new IvParameterSpec(iv);
		      System.out.println("line 99");
		      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		      System.out.println("line 101");
		      KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
		      System.out.println("line 103");
		      SecretKey tmp = factory.generateSecret(spec);
		      System.out.println("line 105");
		      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
		      System.out.println("line 107");
		      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		      System.out.println("line 109");
		      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
		      System.out.println("line 111");
		      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	public  String encrypt(String strToEncrypt) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	
	

}
