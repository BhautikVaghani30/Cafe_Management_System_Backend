package com.cafe.com.cafe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.com.cafe.Entites.Product;
import com.cafe.com.cafe.wrapper.Product_Wrapper;

public interface Product_Dao extends JpaRepository<Product, Integer> {
   
   // this method is creatde in video 6
   List<Product_Wrapper> getAllProduct();
}
