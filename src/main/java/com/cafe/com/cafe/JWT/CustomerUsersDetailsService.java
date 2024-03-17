package com.cafe.com.cafe.JWT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cafe.com.cafe.repositories.User_Dao;

import java.util.ArrayList;
import java.util.Objects;

// this class created in video-2
@Slf4j
@Service
public class CustomerUsersDetailsService implements UserDetailsService {
    // v-2
    @Autowired
    User_Dao userDao;

    // v-2
    private com.cafe.com.cafe.Entites.User userDetail;

    // v-2
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername() {}", username);

        userDetail = userDao.findByEmailId(username);

        if (!Objects.isNull(userDetail)) {
            return new User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
    // v-2
    public com.cafe.com.cafe.Entites.User getUserDetail() {
        return userDetail;
    }
}
