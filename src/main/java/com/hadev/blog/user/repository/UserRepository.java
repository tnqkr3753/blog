package com.hadev.blog.user.repository;

import com.hadev.blog.user.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, String>, UserCustomRepository {
    public Optional<UserEntity> findByUserId(String userId);
    public Optional<UserEntity> findByUserIdAndUserEmail(String userId, String userEmail);

    @Query("select u from UserEntity u join fetch u.works")
    public List<UserEntity> findAllJoinFetch();

    @EntityGraph(attributePaths = {"works"})
    @Query("select u from UserEntity u")
    public List<UserEntity> findAllEntityGraph();
}
