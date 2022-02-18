package com.hadev.blog.board.repository;

import com.hadev.blog.board.entity.Board;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoardRepository extends MongoRepository<Board, String> {
    public Board findByBoardId(String boardId);
    public Board findByBoardName(String boardName);
}
