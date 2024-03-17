package com.cafe.com.cafe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.cafe.com.cafe.Entites.Category;
import com.cafe.com.cafe.Entites.Product;
import com.cafe.com.cafe.wrapper.Product_Wrapper;

import jakarta.transaction.Transactional;

public interface Product_Dao extends JpaRepository<Product, Integer> {
   
   // this method is creatde in video 6
   List<Product_Wrapper> getAllProduct();
   
   // video-7
   @Modifying
   @Transactional
   Integer updateProductStatus(@Param("status") String status,@Param("id") Integer id);

   // video-7
   List<Product_Wrapper> getProductByCategory(@Param(value = "id") Integer id);

   // video-7
   Product_Wrapper getProductById(@Param(value = "id") Integer id);

   List<Product> findByCategory(Category category);
}
