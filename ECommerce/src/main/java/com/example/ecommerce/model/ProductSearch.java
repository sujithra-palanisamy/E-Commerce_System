package com.example.ecommerce.model;

import org.springframework.stereotype.Service;


@Service
public class ProductSearch {

	private int productId;
	
	private String productName;

	@Override
	public String toString() {
		return "ProductSearch [productId=" + productId + ", productName=" + productName + "]";
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	
	
}
