package com.cafe.com.cafe.services;

import com.cafe.com.cafe.Entites.User;
import com.cafe.com.cafe.JWT.CustomerUsersDetailsService;
import com.cafe.com.cafe.JWT.JwtFilter;
import com.cafe.com.cafe.JWT.JwtUtil;
import com.cafe.com.cafe.constants.Cafe_Constants;
import com.cafe.com.cafe.service_Interfaces.User_Service_Interface;
import com.cafe.com.cafe.dao.User_Dao;
import com.cafe.com.cafe.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class User_Service implements User_Service_Interface {
     // this class Autowired in video-1
    @Autowired
    User_Dao userDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    // this method is created for signup in video-1 start
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup: {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                // if user with the given email is not in the db, save it to the db
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return CafeUtils.getResponseEntity(Cafe_Constants.SUCCESSFULLY_REGISTERED, HttpStatus.OK);
                } else {
                    // account already exists
                    return CafeUtils.getResponseEntity(Cafe_Constants.DUPLICATE_ACCOUNT, HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponseEntity(Cafe_Constants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Cafe_Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // checks if sign up information is valid
    private boolean validateSignUpMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password")) {
            return true;
        }
        return false;
    }

    // set the values for the signup
    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }
    // video-1 end

    // video-2 start
    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );

            if (auth.isAuthenticated()) {
                if (customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
                    return new ResponseEntity<String>("{\"token\":\"" +
                            jwtUtil.generateToken(customerUsersDetailsService.getUserDetail().getEmail(),
                                    customerUsersDetailsService.getUserDetail().getRole()) + "\"}", HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<String>("{\"message\":\""+"Please wait for admin approval."+"\"}", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception ex) {
            log.error("{}", ex);
        }
        return CafeUtils.getResponseEntity(Cafe_Constants.INVALID_CREDENTIALS, HttpStatus.BAD_REQUEST);
    }
    // video-2 start

}
