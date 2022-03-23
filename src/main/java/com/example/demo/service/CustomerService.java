package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Users;

public interface CustomerService {

	public void creatCustomerRecord(CustomerDTO customerDTO);
	public List<Customer> fetechAllCustomers();
	public void deleteACustomer(Integer customerId);
	public CustomerDTO fetechcustomerById(Integer customerId);
	public void updateById(Integer customerId, CustomerDTO customerDTO);
	public void deleteAProduct(Integer productId,Integer customerId);
	public List<Users> readUsers();
	public void updateByProductId(Integer customerId, ProductDTO productDTO,Integer productId);
	public void updateCustomerPartially(CustomerDTO customerDTO, int customerID);
	public void updateProductPartially(Integer customerId, ProductDTO productDTO, Integer productId);
	public void creatProductRecord(ProductDTO productDTO, Integer customerId);

}
