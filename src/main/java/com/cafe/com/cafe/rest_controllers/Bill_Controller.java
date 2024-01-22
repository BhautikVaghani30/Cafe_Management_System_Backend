package com.cafe.com.cafe.rest_controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.com.cafe.rest_Interfaces.Bill_Interface;

@RestController
public class Bill_Controller implements Bill_Interface{

    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateReport'");
    }
    
}
