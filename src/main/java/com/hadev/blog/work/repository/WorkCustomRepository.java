package com.hadev.blog.work.repository;

import com.hadev.blog.work.entity.WorkEntity;

import java.util.List;

public interface WorkCustomRepository {
    public List<WorkEntity> findAllByPeriod();
    public List<WorkEntity> findAllByUserId(String userId);
}
