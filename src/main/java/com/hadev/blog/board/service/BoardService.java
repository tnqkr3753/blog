package com.hadev.blog.board.service;

import com.hadev.blog.board.entity.Board;

import java.util.List;

public interface BoardService {
    public List<Board> findAll();
    public Board findByBoardId(String boardId);
    public Board findByBoardName(String boardName);
}
