package com.hadev.blog.user.service;

import com.hadev.blog.user.entity.UserEntity;
import com.hadev.blog.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
public class UserService {

    @Resource(name = "userRepository")
    private UserRepository userRepository;


    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public UserEntity findById(String id) {
        return userRepository.findById(id).orElseGet(UserEntity::new);
    }
    public UserEntity save(UserEntity ent){
        return userRepository.save(ent);
    }

    public List<String> findAllWorkName(){
        return userRepository.findAll().stream()
                .map(userEntity ->
                    userEntity.getWorks().get(0).getWorkName()
                )
                .collect(Collectors.toList());
    }

    /**
     * Join Fetch를 이용한 N+1 해결
     * @return List<String>
     */
    public List<String> findAllWorkNameJoinFetch(){
        return userRepository.findAllJoinFetch().stream()
                .map(userEntity -> userEntity.getWorks().get(0).getWorkName())
                .collect(Collectors.toList());
    }

    /**
     * EntityGraph를 이용한 N+1 해결
     * @return List<String>
     */
    public List<String> findAllWorkNameEntityGraph(){
        return userRepository.findAllEntityGraph().stream()
                .map(userEntity -> userEntity.getWorks().get(0).getWorkName())
                .collect(Collectors.toList());
    }
}
