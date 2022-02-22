package com.hadev.blog.user.service;

import com.hadev.blog.user.entity.UserEntity;
import com.hadev.blog.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserService {

    @Resource(name = "userRepository")
    private UserRepository userRepository;


//    public UserService(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }

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
