package com.example.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.CartItems;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.model.AddCart;
import com.example.ecommerce.model.ProductSearch;
import com.example.ecommerce.service.ProductSearchandAdd;

import antlr.StringUtils;


@RestController
public class EcommerceProducts {
	
	@Autowired
	private ProductSearchandAdd productSearchandAdd;
	
	@RequestMapping(value="/viewAllProducts")
	public List<Product> viewAllProducts() {
		List<Product> addedProducts = productSearchandAdd.viewAllProducts();
		return addedProducts;
	}
	
	@RequestMapping(value="/productSearch", method=RequestMethod.GET)
	public List<Optional<Product>> productSearch(@RequestBody ProductSearch productSearch) {
		List<Optional<Product>> response = new ArrayList<>();
		if(Objects.nonNull(productSearch.getProductId())) {
			 Optional<Product> product = productSearchandAdd.findById(productSearch.getProductId());
			 if(product.isPresent()) {
			 response.add(product);
			 }
		}
		if(Objects.nonNull(productSearch.getProductName())) {
			 response = productSearchandAdd.findByName(productSearch.getProductName());
		}
		return response;
	}
	
	@RequestMapping(value="/addProductToCart", method=RequestMethod.POST)
	public String addProductToCart(@RequestBody AddCart addCart) {
		String status = new String();
		if(Objects.nonNull(addCart.getProductId())) {
			 status = productSearchandAdd.addProductToCart(addCart.getProductId());
		}
		return status;
	}
	
	@RequestMapping(value="/deleteProductFromCart")
	public void deleteProductFromCart(@RequestBody AddCart addCart) {
		if(Objects.nonNull(addCart.getProductId())) {
			productSearchandAdd.deleteProductFromCart(addCart.getProductId());
		}
	}
	
	@RequestMapping(value="/deleteAllFromCart")
	public void deleteAllFromCart() {
			productSearchandAdd.deleteAllFromCart();
	}
	
	@RequestMapping(value="/viewCart")
	public List<CartItems> viewCart() {
		List<CartItems> addedProducts = productSearchandAdd.viewCart();
		return addedProducts;
	}
	
	@RequestMapping(value="/viewCartSorted")
	public List<CartItems> viewCartSorted() {
		List<CartItems> sortedCart = productSearchandAdd.viewCartSorted();
		return sortedCart;
	}
}
