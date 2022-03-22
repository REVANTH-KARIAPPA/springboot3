package com.example.demo.entity;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	@Id
   private Integer productId;
   private String productName;
   private Integer prize;
   public Product() {
	
}
   
public Product(Integer productId, String productName,Integer prize) {
	super();
	this.productId = productId;
	this.productName = productName;
	this.prize=prize;
}

public Integer getProductId() {
	return productId;
}
public void setProductId(Integer productId) {
	this.productId = productId;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}


public Integer getPrize() {
	return prize;
}

public void setPrize(Integer prize) {
	this.prize = prize;
}


   
}
