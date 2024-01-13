package com.cafe.com.cafe.rest_controllers;


import com.cafe.com.cafe.constants.Cafe_Constants;
import java.util.*;
import com.cafe.com.cafe.rest_Interfaces.User_Interface;
import com.cafe.com.cafe.wrapper.*;
import com.cafe.com.cafe.service_Interfaces.User_Service_Interface;
import com.cafe.com.cafe.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User_controller implements User_Interface {

    // video-1 start
    @Autowired
    User_Service_Interface userService;

    

    // video-2 start
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Cafe_Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // video-2 end

    // video-3 start
    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return userService.login(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Cafe_Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // video-3 end

    // video-4 start
    @Override
    public ResponseEntity<List<User_Wrapper>> getAllUser() {
       try {
            return userService.getAllUser();
       } catch (Exception e) {
        e.printStackTrace();
       }
       return new ResponseEntity<List<User_Wrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            return userService.update(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Cafe_Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // video-4 end
    

   
}
