package com.hadev.blog.post.repository;

import com.hadev.blog.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, String> {
}
