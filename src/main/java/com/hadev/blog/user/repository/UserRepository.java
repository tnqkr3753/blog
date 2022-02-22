package com.hadev.blog.user.repository;

import com.hadev.blog.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, String> {
    public Optional<UserEntity> findByUserId(String userId);
    public Optional<UserEntity> findByUserIdAndUserEmail(String userId, String userEmail);
}
