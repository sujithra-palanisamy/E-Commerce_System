package com.example.ecommerce.service;

import java.util.ArrayList;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.CartItems;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;

@Service
public class ProductSearchandAdd {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	public Optional<Product> findById(Integer productId){
	return productRepository.findById(productId);
	}

	public List<Optional<Product>> findByName(String productName) {
		return productRepository.productName(productName);
	}

	public String addProductToCart(Integer productId) {
		Optional<Product> product = productRepository.findById(productId);
		Optional<CartItems> duplicate = cartRepository.duplicateProduct(productId);
		CartItems cartItems = new CartItems();
		String status=new String();
		if(product.isPresent()) {
			Product itemtobeAdded = product.get();
			cartItems.setProductName(itemtobeAdded.getProductName());
			cartItems.setPrice(itemtobeAdded.getPrice());
			if(duplicate.isPresent()) {
			Integer quantity = duplicate.get().getQuantity()+1;
			int repo = cartRepository.updateQuantity(quantity,duplicate.get().getCartId());
			status="Updated Quantity of the Product";
			}else {
				cartItems.setQuantity(1);
			cartItems.setProductId(productId);
			CartItems saved = cartRepository.save(cartItems);
			status = "New Product added in cart";
			}
	}	
		return status;
	}

	public void deleteProductFromCart(Integer productId) {
		int sucess= cartRepository.deleteProduct(productId);
	}

	public List<CartItems> viewCart() {
		 List<CartItems> cartProducts = cartRepository.findAll();
		 return cartProducts;
	}

	public List<CartItems> viewCartSorted() {
		List<CartItems> sortedCart = cartRepository.sortedProduct();
		return sortedCart;
	}

	public List<Product> viewAllProducts() {
		List<Product> allProducts = productRepository.findAll();
		return allProducts;
	}

	public void deleteAllFromCart() {

		cartRepository.deleteAll();
	}
}
