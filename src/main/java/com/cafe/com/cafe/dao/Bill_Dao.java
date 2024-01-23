package com.cafe.com.cafe.dao;

import com.cafe.com.cafe.Entites.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Bill_Dao extends JpaRepository<Bill, Integer> {

    // this is moth created in video - 9
    List<Bill> getAllBills();

    List<Bill> getBillByUserName(@Param("username") String username);
    
}
