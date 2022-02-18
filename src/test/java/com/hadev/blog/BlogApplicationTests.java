package com.hadev.blog;

import com.hadev.blog.board.repository.BoardRepository;
import com.hadev.blog.post.repository.PostRepository;
import com.hadev.blog.user.entity.UserEntity;
import com.hadev.blog.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	private UserEntity initUser;

	@Test
	void contextLoads() {

	}
}
