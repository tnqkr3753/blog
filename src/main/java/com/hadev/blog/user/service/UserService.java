package com.hadev.blog.user.service;

import com.hadev.blog.user.entity.UserEntity;
import com.hadev.blog.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public UserEntity findById(String id) {
        return userRepository.findById(id).orElseGet(UserEntity::new);
    }
    public UserEntity save(UserEntity ent){
        return userRepository.save(ent);
    }
}
