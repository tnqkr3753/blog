package com.hadev.blog;

import com.hadev.blog.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {
	@Autowired
	private BoardRepository boardRepository;

	@Test
	void contextLoads() {
		System.out.println(boardRepository.findByBoardId("BBS_000001"));
	}

}
