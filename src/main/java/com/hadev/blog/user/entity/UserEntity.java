package com.hadev.blog.user.entity;

import lombok.*;

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
@Builder
@ToString
public class UserEntity {
    @Id
    private String userId;
    private String userEmail;
    private String userPassword;
    private String name;
    private int age;
    private String address;
    private Timestamp registDate;
    private Timestamp modifyDate;
}
