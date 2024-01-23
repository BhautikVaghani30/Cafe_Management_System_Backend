package com.cafe.com.cafe.rest_controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.com.cafe.Entites.Bill;
import com.cafe.com.cafe.constants.Cafe_Constants;
import com.cafe.com.cafe.rest_Interfaces.Bill_interface;
import com.cafe.com.cafe.service_Interfaces.Bill_Service_interface;
import com.cafe.com.cafe.utils.CafeUtils;
// this class created in video-9
@RestController
public class Bill_controllers implements Bill_interface {

    // video-9
    @Autowired
    Bill_Service_interface billService;

    // this method help to generate bill Report
    // video-8
    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        try {
            return billService.generateReport(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Cafe_Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // this method is used to fetch bill for user as well as admin
    // video-9
    @Override
    public ResponseEntity<List<Bill>> getBills() {
        try {
            return billService.getBills();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // this method is used to get pdf or download pdf
    // video-9
    @Override
    public ResponseEntity<byte[]> getpdf(Map<String, Object> requestMap) {
        try {
            return billService.getpdf(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    // this method is used to delete bill
    // video-9
    @Override
    public ResponseEntity<String> deleteBill(Integer id) {
        try {
            return this.billService.deleteBill(id);
        } catch (Exception e) {
          e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Cafe_Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    

}
