package com.cafe.com.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.com.cafe.Entites.Bill;

public interface Bill_Dao extends JpaRepository<Bill,Integer> {
    
}
