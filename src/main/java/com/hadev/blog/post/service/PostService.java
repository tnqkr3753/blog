package com.hadev.blog.post.service;

import com.hadev.blog.post.entity.PostEntity;
import com.hadev.blog.post.repository.PostRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("postService")
public class PostService {

    @Resource(name = "postRepository")
    private PostRepository postRepository;

    public List<PostEntity> findAll(){
        return postRepository.findAll(Sort.by(Sort.Direction.DESC,"registDate"));
    }

    public PostEntity findById(String id){
        return postRepository.findById(id).orElse(new PostEntity());
    }
}
