package com.hadev.blog.board.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "boards")
@Getter
@Setter
public class Board {
    @Id
    private String _id;
    private String boardId;
    private String boardName;

    @Override
    public String toString() {
        return "_id : " + _id + ", boardId : " + boardId + ", boardName : "+ boardName;
    }
}
