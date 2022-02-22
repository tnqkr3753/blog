package com.hadev.blog.work.entity;

import com.hadev.blog.user.entity.UserEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "work")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long workId;
    @Column(nullable = false)
    private String workName;
    @Column(nullable = false)
    private String workType;
    @Column(nullable = false)
    private String workStatus;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    private String projectId;
    private LocalDateTime workStartDate;
    private LocalDateTime workEndDate;
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp registDate;
    //@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp modifyDate;
}
