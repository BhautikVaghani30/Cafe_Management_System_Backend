package com.cafe.com.cafe.service_Interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cafe.com.cafe.Entites.Bill;

// this class created in video-9
public interface Bill_Service_interface {
    
    // produce bill of user video -9
    ResponseEntity<String> generateReport(Map<String, Object> requestMap);
    
    // retrieves all bills api video -10
    ResponseEntity<List<Bill>> getBills();
    
    // creates the pdf document for the bill api video -10
    ResponseEntity<byte[]> getpdf(Map<String, Object> requestMap);
    
    // delete user bill video - 10
    ResponseEntity<String> deleteBill(Integer id);



}
