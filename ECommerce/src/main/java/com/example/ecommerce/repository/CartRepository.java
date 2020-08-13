package com.example.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;


import com.example.ecommerce.entity.CartItems;
import com.example.ecommerce.entity.Product;

@Repository
public interface CartRepository extends JpaRepository<CartItems, Integer> {

	
	@Query("Select D from CartItems D where D.productId=:Id")
	 Optional<CartItems> duplicateProduct(@Param("Id")Integer productId);
	
	@Query("Select D from CartItems D order by D.Price")
	 List<CartItems>  sortedProduct();
	
	@Modifying
	@Transactional
	@Query("Update CartItems C set C.quantity=:quantity where C.cartId=:productId")
	int updateQuantity(@Param("quantity")Integer quantity, @Param("productId")Integer productId);

	@Modifying
	@Transactional
	@Query("Delete CartItems C  where C.cartId=:productId")
	int deleteProduct(Integer productId);
	
	
}
