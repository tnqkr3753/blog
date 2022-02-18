package com.hadev.blog.board.service;

import com.hadev.blog.board.entity.Board;
import com.hadev.blog.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "boardService")
public class BoardServiceImpl implements BoardService {

    @Resource(name = "boardRepository")
    private BoardRepository boardRepository;

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
//        List<Board> boards = new ArrayList<Board>();
//        boardRepository.findAll().forEach(b -> boards.add(b));
//        return boards;
    }

    @Override
    public Board findByBoardId(String boardId) {
        return boardRepository.findByBoardId(boardId);
    }

    @Override
    public Board findByBoardName(String boardName) {
        return boardRepository.findByBoardName(boardName);
    }
}
