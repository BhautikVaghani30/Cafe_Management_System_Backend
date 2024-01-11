package com.cafe.com.cafe.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.cafe.com.cafe.Entites.User;

public interface User_Dao extends JpaRepository<User, Integer> {

    // video-1 start
    User findByEmailId(@Param("email") String email);
    // video-2 end
    

}
