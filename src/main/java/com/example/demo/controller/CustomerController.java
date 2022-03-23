package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    @PostMapping("/create")
    public ResponseEntity<String> createACustomerRecord(@Valid @RequestBody CustomerDTO customerDTO) {
    	customerService.creatCustomerRecord(customerDTO);
    	return new ResponseEntity<>("Customer Record Save Successfully",HttpStatus.OK);
    }
    @GetMapping("/all")
    public List<Customer> fetchingCustomer(){
    	return customerService.fetechAllCustomers();
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerId) {
    	customerService.deleteACustomer(customerId);
    	return new ResponseEntity<>("Customer Record deleted Successfully",HttpStatus.OK);
    }
    @DeleteMapping("/{customerId}/delete/{productId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer productId,@PathVariable Integer customerId){
    	customerService.deleteAProduct(productId,customerId);
    	return new ResponseEntity<>("Product deleted Successfully",HttpStatus.OK);
    	
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> fetchCustomerById(@PathVariable Integer customerId){
    	CustomerDTO customerDTO= customerService.fetechcustomerById(customerId);
    	return new ResponseEntity<>(customerDTO,HttpStatus.OK);
    }
    @PutMapping("/{customerId}")
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerDTO customerDTO,@PathVariable Integer customerId) {
    	customerService.updateById(customerId,customerDTO);
    	return new ResponseEntity<>("Customer Record updated Successfully",HttpStatus.OK);
    }
    
    @PatchMapping("/{customerId}")
    public ResponseEntity<String>patchCustomer(@RequestBody CustomerDTO customerDTO,@PathVariable Integer customerId){
    	
    	customerService.updateCustomerPartially(customerDTO,customerId);
    	return new ResponseEntity<>("Customer Record updated Successfully",HttpStatus.OK);
    	
    }
    
  @PatchMapping("/{customerId}/patch/{productId}")
   public 	ResponseEntity<String>patchProduct(@RequestBody ProductDTO productDTO,@PathVariable Integer customerId,@PathVariable Integer productId){
   	customerService.updateProductPartially(customerId,productDTO,productId);
   	return new ResponseEntity<>("Customer Record Patched Successfully",HttpStatus.OK);
   }
    @PutMapping("/{customerId}/update/{productId}")
    public 	ResponseEntity<String>updateProduct(@RequestBody ProductDTO productDTO,@PathVariable Integer customerId,@PathVariable Integer productId){
    	customerService.updateByProductId(customerId,productDTO,productId);
    	return new ResponseEntity<>("Customer Record update Successfully",HttpStatus.OK);
    }
    @PostMapping("/{customerId}/create")
    public ResponseEntity<String> createAProductRecord(@RequestBody ProductDTO productDTO,@PathVariable Integer customerId) {
    	customerService.creatProductRecord(productDTO, customerId);
    	return new ResponseEntity<>("Product Record Save Successfully",HttpStatus.OK);
    }
  
}
