package com.example.demo.service;

import java.util.ArrayList;  

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Address;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.entity.Users;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.PoductIdException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UsersRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UsersRepository usersRepository;
	@Override
	public void creatCustomerRecord(CustomerDTO customerDTO) {
		Customer customer=new Customer();
		customer.setFirstName(customerDTO.getFirstName());
		customer.setLastName(customerDTO.getLastName());
		customer.setEmail(customerDTO.getEmail());
		customer.setCustomerType(customerDTO.getCustomerType());
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		
		Address address=new Address();
		address.setStreetNumber(customerDTO.getAddressDTO().getStreetNumber());
		address.setCity(customerDTO.getAddressDTO().getCity());
		address.setState(customerDTO.getAddressDTO().getState());
		customer.setAddress(address);
		
		List <Product> product =new ArrayList<>();
		
		for(int i=0;i<customerDTO.getProductDTO().size();i++) {
			Product p= new Product();
			
			Optional<Product>optionalProductDTO=productRepository.findById(customerDTO.getProductDTO().get(i).getProductId());
			if(optionalProductDTO.isPresent()) {
				throw new PoductIdException("Create Operation failed \n No Product already exists with id: "+customerDTO.getProductDTO().get(i).getProductId());
			}
			p.setProductId(customerDTO.getProductDTO().get(i).getProductId());
			p.setProductName(customerDTO.getProductDTO().get(i).getProductName());
			p.setPrize(customerDTO.getProductDTO().get(i).getPrize());
			product.add(p);
		}
		
		
		customer.setProduct(product);
         
		customerRepository.save(customer);
		
	}

	@Override
	public List<Customer> fetechAllCustomers() {
	    List<Customer> customers= customerRepository.findAll();
	    List<Customer> customerDTOList=new  ArrayList<>();
	   customerDTOList= customers.stream().map(customer->{
		   
	    	Customer customerDTO =new Customer();
	    	
	    	customerDTO.setEmail(customer.getEmail());
	    	customerDTO.setFirstName(customer.getFirstName());
	    	customerDTO.setLastName(customer.getLastName());
	    	customerDTO.setCustomerType(customer.getCustomerType());
	    	customerDTO.setCustomerId(customer.getCustomerId());
	    	customerDTO.setDateOfBirth(customer.getDateOfBirth());
	    	customerDTO.setAddress(customer.getAddress());
	    	customerDTO.setProduct(customer.getProduct());
	    	return customerDTO;
	    	
	    }).collect(Collectors.toList());
	    
		return customerDTOList;
	}

	@Override
	public void deleteACustomer(Integer customerId) {
		
		Optional<Customer>optionalCustomerDTO=customerRepository.findById(customerId);
		if(optionalCustomerDTO.isPresent())
		customerRepository.deleteById(customerId);
		else
			throw new CustomerNotFoundException("Delete Operation failed \n No customer Found with id: "+customerId);
		
	}

	@Override
	public CustomerDTO fetechcustomerById(Integer customerId) {
		Customer customer=null;
		Optional<Customer>optionalCustomerDTO=customerRepository.findById(customerId);
		if(optionalCustomerDTO.isPresent()) {
			customer=optionalCustomerDTO.get();
	        CustomerDTO customerDTO =new CustomerDTO();
	    	customerDTO.setEmail(customer.getEmail());
	    	customerDTO.setFirstName(customer.getFirstName());
	    	customerDTO.setLastName(customer.getLastName());
	    	customerDTO.setCustomerType(customer.getCustomerType());
	    	customerDTO.setCustomerId(customer.getCustomerId());
	    	customerDTO.setDateOfBirth(customer.getDateOfBirth());
	    	
	    	AddressDTO addressDTO=new AddressDTO();
			addressDTO.setStreetNumber(customer.getAddress().getStreetNumber());
			addressDTO.setCity(customer.getAddress().getCity());
			addressDTO.setState(customer.getAddress().getState());
			customerDTO.setAddressDTO(addressDTO);
			
			List <ProductDTO> productDTO =new ArrayList<>();
			
			for(int i=0;i<customer.getProduct().size();i++) {
				ProductDTO p= new ProductDTO();
				p.setProductName(customer.getProduct().get(i).getProductName());
				p.setProductId(customer.getProduct().get(i).getProductId());
				p.setPrize(customer.getProduct().get(i).getPrize());
				productDTO.add(p);
			}
			customerDTO.setProductDTO(productDTO);
			
			
	    	
	    	return customerDTO;
		}
		else {
			throw new CustomerNotFoundException("Get operation Failed \n No Customer Found with Customer Id:"+ customerId);
		}
	}

	@Override
	public void updateById(Integer customerId,CustomerDTO customerDTO) {
		
		Optional<Customer>optionalCustomerDTO=customerRepository.findById(customerId);
		if(optionalCustomerDTO.isPresent()) {
			Customer customer=optionalCustomerDTO.get();;
			
			customer.setFirstName(customerDTO.getFirstName());
			customer.setLastName(customerDTO.getLastName());
			customer.setEmail(customerDTO.getEmail());
			customer.setCustomerType(customerDTO.getCustomerType());
			customer.setDateOfBirth(customerDTO.getDateOfBirth());
			Address address=new Address();
			address.setStreetNumber(customerDTO.getAddressDTO().getStreetNumber());
			address.setCity(customerDTO.getAddressDTO().getCity());
			address.setState(customerDTO.getAddressDTO().getState());
			customer.setAddress(address);
			List <Product> product =new ArrayList<>();
			
			for(int i=0;i<customerDTO.getProductDTO().size();i++) {
				Product p= new Product();
				
				Optional<Product>optionalProductDTO=productRepository.findById(customerDTO.getProductDTO().get(i).getProductId());
				if(optionalProductDTO.isPresent()) {
					throw new PoductIdException("Create Operation failed \n No Product already exists with id: "+customerDTO.getProductDTO().get(i).getProductId());
				}
				p.setProductId(customerDTO.getProductDTO().get(i).getProductId());
				p.setProductName(customerDTO.getProductDTO().get(i).getProductName());
				p.setPrize(customerDTO.getProductDTO().get(i).getPrize());
				product.add(p);
			}
			customer.setProduct(product);
			
			
			customerRepository.save(customer);
		}
	}
     
	@Override
	public void deleteAProduct(Integer productId,Integer customerId) {
		Optional<Customer>optionalCustomerDTO=customerRepository.findById(customerId);
		if(optionalCustomerDTO.isPresent()) {
		Optional<Product>optionalProductDTO=productRepository.findById(productId);
		if(optionalProductDTO.isPresent()) {
			productRepository.deleteById(productId);	
		}
		
		else
			throw new CustomerNotFoundException("Delete Operation failed \n No product Found with id: "+productId);
	}
		else
			throw new CustomerNotFoundException("Delete Operation failed \n No customer Found with id: "+customerId);
	}

	@Override
    public List<Users> readUsers(){
        return new ArrayList<>(usersRepository.findAll());
    }

	@Override
	public void updateByProductId(Integer customerId, ProductDTO productDTO,Integer productId) {
		
		Optional<Customer>optionalCustomerDTO=customerRepository.findById(customerId);
		if(optionalCustomerDTO.isPresent()) {
		Optional<Product>optionalProductDTO=productRepository.findById(productId);
		if(optionalProductDTO.isPresent()) {
            Product product=optionalProductDTO.get();
			product.setProductName(productDTO.getProductName());
			product.setPrize(productDTO.getPrize());
           productRepository.save(product);
			
		}
		
		else
			throw new CustomerNotFoundException("Update Operation failed \n No product Found with id: "+productId);
	}
		else
			throw new CustomerNotFoundException("Update Operation failed \n No customer Found with id: "+customerId);
	}
	@Override
	public void updateCustomerPartially(CustomerDTO customerDTO, int customerID){
		Customer customer  = customerRepository.findById(customerID).orElseThrow(()->new CustomerNotFoundException("Customer with ID "+customerID+" not Found"));
		    if(customerDTO.getFirstName()!= null)
		        customer.setFirstName(customerDTO.getFirstName());
		    if(customerDTO.getLastName()!= null)
    	        customer.setLastName(customerDTO.getLastName());
    	    if(customerDTO.getCustomerType()!= null)
   	            customer.setCustomerType(customerDTO.getCustomerType());
    	    if(customerDTO.getDateOfBirth()!=null)
    	    	customer.setDateOfBirth(customerDTO.getDateOfBirth());
    	    if(customerDTO.getEmail()!= null)
    	    	customer.setEmail(customerDTO.getEmail());
    	    if(customerDTO.getAddressDTO()!=null) {
    	    	
    	    	Address address = new Address();
    	    	if(customerDTO.getAddressDTO().getStreetNumber() != null)
    	   	    address.setStreetNumber(customerDTO.getAddressDTO().getStreetNumber());
    	   	    if( customerDTO.getAddressDTO().getCity()!=null)
    	    	address.setCity(customerDTO.getAddressDTO().getCity());
    	   	    if( customerDTO.getAddressDTO().getState()!=null)
     	    	address.setState(customerDTO.getAddressDTO().getState());
    	   	    customer.setAddress(address);
    	    }
    	   	 if(customerDTO.getProductDTO()!=null) {   
    	   	 List <Product> product =new ArrayList<>();
 			
 			for(int i=0;i<customerDTO.getProductDTO().size();i++) {
 				Product p= new Product();
 				if(customerDTO.getProductDTO().get(i).getProductName()!=null)
 				p.setProductName(customerDTO.getProductDTO().get(i).getProductName());
 				if(customerDTO.getProductDTO().get(i).getProductId()!=null)
 				p.setProductId(customerDTO.getProductDTO().get(i).getProductId());
 				if(customerDTO.getProductDTO().get(i).getPrize()!=null)
 				p.setPrize(customerDTO.getProductDTO().get(i).getPrize());
 				product.add(p);
 			}
 			customer.setProduct(product);
    	   }   
			 customerRepository.save(customer);
	}

	@Override
	public void updateProductPartially(Integer customerId, ProductDTO productDTO, Integer productId) {
		
		Optional<Customer>optionalCustomerDTO=customerRepository.findById(customerId);
		if(optionalCustomerDTO.isPresent()) {
		Optional<Product>optionalProductDTO=productRepository.findById(productId);
		if(optionalProductDTO.isPresent()) {
            Product product=optionalProductDTO.get();
            if(productDTO.getProductName()!=null)
			product.setProductName(productDTO.getProductName());
            if(productDTO.getPrize()!=null)
			product.setPrize(productDTO.getPrize());
           productRepository.save(product);
			
		}
		
		else
			throw new CustomerNotFoundException("Update Operation failed \n No product Found with id: "+productId);
	}
		else
			throw new CustomerNotFoundException("Update Operation failed \n No customer Found with id: "+customerId);
	}

	

	@Override
	public void creatProductRecord(ProductDTO productDTO, Integer customerId) {
		Optional<Customer>optionalCustomerDTO=customerRepository.findById(customerId);
		if(optionalCustomerDTO.isPresent()) {
			Customer customer=optionalCustomerDTO.get();
			Product product=new Product();
			
	    Optional<Product>optionalProductDTO=productRepository.findById(productDTO.getProductId());	
	    if(optionalProductDTO.isPresent())
	    throw new PoductIdException("Create Operation failed \n No Product already exists with id: "+productDTO.getProductId());	
	    else {
			product.setProductId(productDTO.getProductId());
			product.setProductName(productDTO.getProductName());
			product.setPrize(productDTO.getPrize());
			List <Product> p =customer.getProduct();
			p.add(product);
			customer.setProduct(p);
			customerRepository.save(customer);
	    }
		}
		else
		{
			throw new CustomerNotFoundException("Create Operation failed \n No customer Found with id: "+customerId);
		}
		
	}

	
	
	}


