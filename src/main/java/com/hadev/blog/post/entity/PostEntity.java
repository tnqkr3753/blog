package com.hadev.blog.post.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    @Column
    private String postTitle;
    @Column
    private String postContents;
    @Column
    private boolean secretTf;
    @Column
    private String register;
    @Column
    private Timestamp registDate;
    @Column
    private String modifier;
    @Column
    private Timestamp modifyDate;

}
