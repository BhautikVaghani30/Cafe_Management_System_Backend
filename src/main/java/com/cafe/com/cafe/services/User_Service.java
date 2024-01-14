package com.cafe.com.cafe.services;

import com.cafe.com.cafe.Entites.User;
import com.cafe.com.cafe.JWT.CustomerUsersDetailsService;
import com.cafe.com.cafe.JWT.JwtFilter;
import com.cafe.com.cafe.JWT.JwtUtil;
import com.cafe.com.cafe.constants.Cafe_Constants;
import com.cafe.com.cafe.service_Interfaces.User_Service_Interface;
import com.cafe.com.cafe.dao.User_Dao;
import com.cafe.com.cafe.utils.CafeUtils;

import com.cafe.com.cafe.utils.EmailUtils;
import com.cafe.com.cafe.wrapper.User_Wrapper;
import com.google.common.base.Strings;

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
    EmailUtils emailUtil;

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
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));

            if (auth.isAuthenticated()) {
                if (customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
                    return new ResponseEntity<String>("{\"token\":\"" +
                            jwtUtil.generateToken(customerUsersDetailsService.getUserDetail().getEmail(),
                                    customerUsersDetailsService.getUserDetail().getRole())
                            + "\"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("{\"message\":\"" + "Please wait for admin approval." + "\"}",
                            HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception ex) {
            log.error("{}", ex);
        }
        return CafeUtils.getResponseEntity(Cafe_Constants.INVALID_CREDENTIALS, HttpStatus.BAD_REQUEST);
    }
    // video-2 start

    @Override
    public ResponseEntity<List<User_Wrapper>> getAllUser() {
        try {
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<>(userDao.getAllUser(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // update user password vidio-4
    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<User> optional = userDao.findById(Integer.parseInt(requestMap.get("id")));
                if (!optional.isEmpty()) {
                    userDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    sendMailToAllAdmin(requestMap.get("status"), optional.get().getEmail(), userDao.getAllAdmin());
                    System.out.println("email function called");
                    return CafeUtils.getResponseEntity("User Status Updated Successfully", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity("User id Doesn't not exist", HttpStatus.OK);
                }
            } else {
                return CafeUtils.getResponseEntity(Cafe_Constants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Cafe_Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // send email to all admin user video-4
    private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
        allAdmin.remove(jwtFilter.getCurrentUser());
        if (status != null && status.equalsIgnoreCase("true")) {
            emailUtil.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Approved",
                    "hello all admin user \nwelcome to cafe management system \ni would like to give information about user \nUSER: - "
                            + user + " \nis approved by \nADMIN: - " + jwtFilter.getCurrentUser()
                            + "\nVaghani Bhautik\n",
                    allAdmin);
            System.out.println("send apruval");
        } else {
            emailUtil.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Approved",
                    "hello all admin user \nwelcome to cafe management system \ni would like to give information about user \nUSER: - "
                            + user + " \nis disable by \nADMIN: - " + jwtFilter.getCurrentUser()
                            + "\nVaghani Bhautik\n",
                    allAdmin);
            System.out.println("send apruval");
        }
    }

    // video-5
    // checktoken
    @Override
    public ResponseEntity<String> checkToken() {
        return CafeUtils.getResponseEntity(Cafe_Constants.TRUE, HttpStatus.OK);
    }

    // changePassword video-5
    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            // find the user by their email to change their password
            User userObject = userDao.findByEmail(jwtFilter.getCurrentUser());
            if (!userObject.equals(null)) {
                // entered correct old password --> can change to new password
                if (userObject.getPassword().equals(requestMap.get("oldPassword"))) {
                    userObject.setPassword(requestMap.get("newPassword"));
                    userDao.save(userObject); // save the data to the db
                    return CafeUtils.getResponseEntity(Cafe_Constants.PASSWORD_CHANGED, HttpStatus.OK);
                }
                return CafeUtils.getResponseEntity(Cafe_Constants.INCORRECT_OLD_PASSWORD, HttpStatus.BAD_REQUEST);
            }
            return CafeUtils.getResponseEntity(Cafe_Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Cafe_Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // forgot password video-5
    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try {
            User userObject = userDao.findByEmail(requestMap.get("email"));
            if (!Objects.isNull(userObject) && !Strings.isNullOrEmpty(userObject.getEmail())) {
                // send password change email to the user's email
                emailUtil.forgotMail(userObject.getEmail(), "Reset your Lofi Cafe Password", userObject.getPassword());
            }
            return CafeUtils.getResponseEntity(Cafe_Constants.CHECK_EMAIL, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Cafe_Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
