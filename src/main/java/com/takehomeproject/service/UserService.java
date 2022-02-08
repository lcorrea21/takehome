package com.takehomeproject.service;

import com.takehomeproject.dao.UserDAO;
import com.takehomeproject.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserDAO userDAO;
    PasswordEncoder passwordEncoder;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
        this.passwordEncoder= new BCryptPasswordEncoder();
    }

    public User save(User user){
        String encodePassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return this.userDAO.save(user);
    }

    public User update(User user){
        Optional<User> userFound = userDAO.findById(user.getId());
        if(userFound.isPresent()){
            User updateUser = userFound.get();
            updateUser.setFirstname(user.getFirstname());
            updateUser.setLastname(user.getLastname());
            updateUser.setEmail(user.getEmail());
            updateUser.setPassword(user.getPassword());

            return this.save(updateUser);
        } else {
            return this.save(user);
        }
    }
}
