package com.takehomeproject.dao;

import com.takehomeproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {

    Optional <User> findByEmail(String email);

}
