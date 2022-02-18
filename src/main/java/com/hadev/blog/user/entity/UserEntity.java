package com.hadev.blog.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    private String userId;
    @Column
    private String userEmail;
    @Column
    private String userPassword;
    @Column
    private Timestamp registDate;
    @Column
    private Timestamp modifyDate;

}
