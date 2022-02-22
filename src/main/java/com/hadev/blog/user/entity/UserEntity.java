package com.hadev.blog.user.entity;

import com.hadev.blog.work.entity.WorkEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "works")
public class UserEntity {
    @Id
    private String userId;
    private String userEmail;
    private String userPassword;
    private String name;
    private int age;
    private String address;
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp registDate;
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp modifyDate;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<WorkEntity> works = new ArrayList<WorkEntity>();

}
