package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ProductDTO {
	@NotNull(message="productId can't be Empty!")

	private Integer productId;
	@Pattern(regexp="^[A-Za-z]*$",message = "Product Name must contain only  alphabet")
	@NotBlank(message="Product can't be Empty!")
   private String productName;
	@NotNull(message="prize can't be Empty!")

   private Integer prize;
   

public String getProductName() {
	return productName;
}

public void setProductName(String productName) {
	this.productName = productName;
}

public Integer getProductId() {
	return productId;
}

public void setProductId(Integer productId) {
	this.productId = productId;
}

public Integer getPrize() {
	return prize;
}

public void setPrize(Integer prize) {
	this.prize = prize;
}

   
}
