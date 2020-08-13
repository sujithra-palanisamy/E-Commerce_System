package com.example.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Product {

	@Id
	private Integer productId;
	
	private String productName;
	
	private Long price;
	
	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
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
	
}
