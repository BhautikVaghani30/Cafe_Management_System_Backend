package com.cafe.com.cafe.services;

import com.cafe.com.cafe.dao.Bill_Dao;
import com.cafe.com.cafe.dao.Category_Dao;
import com.cafe.com.cafe.dao.Product_Dao;
import com.cafe.com.cafe.service_Interfaces.Dashboard_Service_interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

// this created in video - 10
@Service
public class Dashboard_Service implements Dashboard_Service_interface {

    @Autowired
    Category_Dao categoryDao;

    @Autowired
    Product_Dao productDao;

    @Autowired
    Bill_Dao billDao;

    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        try {
            Map<String, Object> map = new HashMap<>();
            // map each table to a count
            map.put("category", categoryDao.count());
            map.put("product", productDao.count());
            map.put("bill", billDao.count());
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
