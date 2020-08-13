package com.example.ecommerce.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>  {
	
	Optional<Product> findById(Integer productId);
	
	@Query("Select P from Product P where P.productName like %:productName%" )
	List<Optional<Product>> productName(@Param("productName")String productName);
}
