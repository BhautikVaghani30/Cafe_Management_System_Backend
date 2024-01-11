package com.cafe.com.cafe.rest_controllers;

import com.cafe.com.cafe.constants.Cafe_Constants;

import com.cafe.com.cafe.rest_Interfaces.User_Interface;
import com.cafe.com.cafe.service_Interfaces.User_Service_Interface;
import com.cafe.com.cafe.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class User_controller implements User_Interface {

    // video-1 start
    @Autowired
    User_Service_Interface userService;

    // video-1 start
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Cafe_Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // video-1 end

    // video-2 start
    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return userService.login(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Cafe_Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // video-2 end

   
}
