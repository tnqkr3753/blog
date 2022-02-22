package com.hadev.blog.work.repository;

import com.hadev.blog.work.entity.WorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("workRepository")
public interface WorkRepository extends JpaRepository<WorkEntity, String> {

    public List<WorkEntity> findAllByUser_UserId(String userId);

}
