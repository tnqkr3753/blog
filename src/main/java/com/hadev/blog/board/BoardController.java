package com.hadev.blog.board;

import com.hadev.blog.board.entity.Board;
import com.hadev.blog.board.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("board")
public class BoardController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "boardService")
    private BoardService boardService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Board>> getAllBoards(){
        List<Board> boards = boardService.findAll();
        return new ResponseEntity<List<Board>>(boards, HttpStatus.OK);
    }
    @GetMapping(value = "/{boardId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Board> getBoard(@PathVariable("boardId")String boardId){
        return new ResponseEntity<Board>(boardService.findByBoardId(boardId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{boardId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HashMap<String, Object>> deleteBoard(@PathVariable("boardId")String boardId){
        return new ResponseEntity<HashMap<String, Object>>(new HashMap<String, Object>(){{
            put("result",1);
        }}, HttpStatus.OK);
    }
}
