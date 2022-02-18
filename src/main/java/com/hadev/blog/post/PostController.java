package com.hadev.blog.post;

import com.hadev.blog.post.entity.PostEntity;
import com.hadev.blog.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/post")
public class PostController {

    private final PostService postService;

    @GetMapping(value = "")
    public ResponseEntity<List<PostEntity>> findAll(){
        return new ResponseEntity<List<PostEntity>>(postService.findAll(),HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostEntity> findById(@PathVariable(name = "id")String id){
        return new ResponseEntity<PostEntity>(postService.findById(id),HttpStatus.OK);

    }
    @PostMapping(value = "/{id}")
    public ResponseEntity<PostEntity> post(@RequestBody PostEntity ent){
        return new ResponseEntity<PostEntity>(postService.saveById(ent),HttpStatus.OK);
    }
}
